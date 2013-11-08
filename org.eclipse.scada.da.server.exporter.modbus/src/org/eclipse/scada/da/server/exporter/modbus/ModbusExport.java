/*******************************************************************************
 * Copyright (c) 2013 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.scada.da.server.exporter.modbus;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSession;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.eclipse.scada.ca.ConfigurationDataHelper;
import org.eclipse.scada.da.server.exporter.modbus.common.HiveSource;
import org.eclipse.scada.da.server.exporter.modbus.io.DoubleType;
import org.eclipse.scada.da.server.exporter.modbus.io.MemoryBlock;
import org.eclipse.scada.da.server.exporter.modbus.io.ShortType;
import org.eclipse.scada.da.server.exporter.modbus.io.SourceDefinition;
import org.eclipse.scada.da.server.exporter.modbus.io.SourceType;
import org.eclipse.scada.protocol.modbus.codec.ModbusSlaveProtocolFilter;
import org.eclipse.scada.protocol.modbus.codec.ModbusTcpDecoder;
import org.eclipse.scada.protocol.modbus.codec.ModbusTcpEncoder;
import org.eclipse.scada.protocol.modbus.message.BaseMessage;
import org.eclipse.scada.protocol.modbus.message.ErrorResponse;
import org.eclipse.scada.protocol.modbus.message.ReadRequest;
import org.eclipse.scada.protocol.modbus.message.ReadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModbusExport
{
    private final static Logger logger = LoggerFactory.getLogger ( ModbusExport.class );

    private MemoryBlock block;

    private final ScheduledExecutorService executor;

    private Properties properties;

    private final HiveSource hiveSource;

    private final IoProcessor<NioSession> processor;

    private SocketAcceptor acceptor;

    private SocketAddress currentAddress;

    private int slaveId;

    public ModbusExport ( final ScheduledExecutorService executor, final IoProcessor<NioSession> processor, final HiveSource hiveSource )
    {
        this.executor = executor;
        this.hiveSource = hiveSource;
        this.processor = processor;

        this.acceptor = createAcceptor ();
    }

    private SocketAcceptor createAcceptor ()
    {
        final NioSocketAcceptor acceptor = new NioSocketAcceptor ( this.processor );

        acceptor.setReuseAddress ( true );
        acceptor.setBacklog ( Integer.getInteger ( "org.eclipse.scada.da.server.exporter.modbus.acceptor.backlog", 5 ) ); //$NON-NLS-1$

        final ModbusTcpEncoder encoder = new ModbusTcpEncoder ();
        final ModbusTcpDecoder decoder = new ModbusTcpDecoder ();
        acceptor.getFilterChain ().addLast ( "modbusPdu", new ProtocolCodecFilter ( encoder, decoder ) ); //$NON-NLS-1$
        acceptor.getFilterChain ().addLast ( "modbus", new ModbusSlaveProtocolFilter () ); //$NON-NLS-1$

        acceptor.setHandler ( new IoHandlerAdapter () {
            @Override
            public void exceptionCaught ( final IoSession session, final Throwable cause ) throws Exception
            {
                session.close ( true );
            };

            @Override
            public void sessionOpened ( final IoSession session ) throws Exception
            {
                logger.info ( "Session opened: {}", session );
            };

            @Override
            public void sessionClosed ( final IoSession session ) throws Exception
            {
                logger.info ( "Session closed: {}", session );
            };

            @Override
            public void messageReceived ( final IoSession session, final Object message ) throws Exception
            {
                handleMessageReceived ( session, message );
            };
        } );

        return acceptor;
    }

    public void update ( final Map<String, String> parameters ) throws Exception
    {
        final ConfigurationDataHelper cfg = new ConfigurationDataHelper ( parameters );
        setPort ( cfg.getInteger ( "port", 502 ) ); //$NON-NLS-1$
        setSlaveId ( cfg.getInteger ( "slaveId", 1 ) ); //$NON-NLS-1$
        setProperties ( cfg.getPrefixedProperties ( "hive." ) ); //$NON-NLS-1$
        configureDefinitions ( cfg );
    }

    private void setSlaveId ( final int slaveId )
    {
        logger.debug ( "Setting slave id: {}", slaveId );
        this.slaveId = slaveId;
    }

    private void setPort ( final int port ) throws IOException
    {
        final SocketAddress address = new InetSocketAddress ( port );

        if ( this.currentAddress == null || !this.currentAddress.equals ( address ) )
        {
            logger.info ( "Rebinding interface - {} to {}", this.currentAddress, address ); //$NON-NLS-1$
            if ( this.currentAddress != null )
            {
                this.acceptor.unbind ( this.currentAddress );
            }
            this.currentAddress = address;
            this.acceptor.bind ( address );
        }
    }

    private void configureDefinitions ( final ConfigurationDataHelper cfg )
    {
        final List<SourceDefinition> defs = new LinkedList<> ();

        for ( final Map.Entry<String, String> entry : cfg.getPrefixed ( "item." ).entrySet () )
        {
            final String itemId = entry.getKey ();
            final String[] args = entry.getValue ().split ( ":" );
            logger.info ( "Adding - itemId: {}, arguments: {}", itemId, args );
            defs.add ( convert ( itemId, args ) );
        }

        this.block.setConfiguration ( defs );
    }

    private SourceDefinition convert ( final String itemId, final String[] args )
    {
        final int offset = Integer.parseInt ( args[0] );
        final SourceType type;

        switch ( args[1].toUpperCase () )
        {
            case "DOUBLE":
                type = new DoubleType ( getFactor ( args ) );
                break;
            case "INT16":
            case "SHORT":
                type = new ShortType ( getFactor ( args ) );
                break;
            default:
                throw new IllegalArgumentException ( String.format ( "Type '%s' is unknown.", args[1] ) );
        }

        return new SourceDefinition ( itemId, offset, type );
    }

    private Double getFactor ( final String[] args )
    {
        if ( args.length > 2 )
        {
            return Double.parseDouble ( args[2] );
        }
        return null;
    }

    private void setProperties ( final Properties properties )
    {
        if ( this.block == null )
        {
            logger.debug ( "Create new block" ); //$NON-NLS-1$
            this.block = new MemoryBlock ( this.executor, this.hiveSource, properties );
        }
        else if ( !this.properties.equals ( properties ) )
        {
            // we do need to create a new hive session
            logger.debug ( "Re-create block" ); //$NON-NLS-1$
            this.block.dispose ();
            this.block = new MemoryBlock ( this.executor, this.hiveSource, properties );
        }
        this.properties = properties;
    }

    protected void handleMessageReceived ( final IoSession session, final Object message )
    {
        logger.trace ( "New message - message: {}, session: {}", message, session );

        if ( ! ( message instanceof BaseMessage ) )
        {
            return;
        }

        final BaseMessage baseMessage = (BaseMessage)message;
        if ( baseMessage.getUnitIdentifier () != this.slaveId )
        {
            logger.trace ( "Invalid unit id - use: {}, them: {}", this.slaveId, baseMessage.getUnitIdentifier () );
            return;
        }

        if ( message instanceof ReadRequest )
        {
            handleRead ( session, (ReadRequest)message );
        }
    }

    private void handleRead ( final IoSession session, final ReadRequest message )
    {
        switch ( message.getFunctionCode () )
        {
            case 3:
                readHoldingData ( session, message );
                break;
            default:
                logger.info ( "Function code {} is not implemented", message.getFunctionCode () );
                sendReply ( session, makeError ( message, 0x01 ) );
                break;
        }
    }

    protected void readHoldingData ( final IoSession session, final ReadRequest message )
    {
        final int byteOffset = message.getStartAddress () * 2;
        final int byteLength = message.getQuantity () * 2;

        logger.debug ( "Reading - byteOffset: {}, byteLength: {}", byteOffset, byteLength );

        if ( message.getQuantity () < 0 || message.getQuantity () >= 0x7D )
        {
            logger.info ( "Invalid quanity" );
            sendReply ( session, makeError ( message, 0x02 ) );
            return;
        }

        final IoBuffer data = this.block.readData ( byteOffset, byteLength );
        if ( data == null )
        {
            logger.info ( "No data" );
            sendReply ( session, makeError ( message, 0x04 ) );
        }
        else
        {
            // reply data
            sendReply ( session, makeData ( message, data ) );
        }
    }

    protected Object makeData ( final BaseMessage message, final IoBuffer data )
    {
        data.flip ();

        logger.trace ( "Create data message - data: {}", data );
        return new ReadResponse ( message.getTransactionId (), message.getUnitIdentifier (), message.getFunctionCode (), data );
    }

    protected Object makeError ( final BaseMessage message, final int exceptionCode )
    {
        byte functionCode = message.getFunctionCode ();
        functionCode |= (byte)0x80;
        return new ErrorResponse ( message.getTransactionId (), message.getUnitIdentifier (), functionCode, (byte)exceptionCode );
    }

    protected void sendReply ( final IoSession session, final Object message )
    {
        logger.trace ( "Send reply - message: {}, session: {}", message, session );
        session.write ( message );
    }

    public void dispose ()
    {
        if ( this.acceptor != null )
        {
            this.acceptor.dispose ();
            this.acceptor = null;
        }
        if ( this.block != null )
        {
            this.block.dispose ();
            this.block = null;
        }
    }

}
