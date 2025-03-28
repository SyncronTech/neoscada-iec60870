/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.asdu.types;

import org.eclipse.neoscada.protocol.iec60870.ProtocolOptions;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ASDUAddress
{
    public static final ASDUAddress BROADCAST = new ASDUAddress ( 0xFFFF );

    private final int address;

    private ASDUAddress ( final int address )
    {
        this.address = address;
    }

    public int getAddress ()
    {
        return this.address;
    }

    public void encode ( final ProtocolOptions options, final ByteBuf out )
    {
        options.getAdsuAddressType ().write ( this.address, out );
    }

    public boolean isBroadcast ()
    {
        return this.address == 0xFFFF;
    }

    public static ASDUAddress parse ( final ProtocolOptions options, final ByteBuf data )
    {
        return new ASDUAddress ( options.getAdsuAddressType ().read ( data ) );
    }

    @Override
    public int hashCode ()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.address;
        return result;
    }

    @Override
    public boolean equals ( final Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass () != obj.getClass () )
        {
            return false;
        }
        final ASDUAddress other = (ASDUAddress)obj;
        if ( this.address != other.address )
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        final int[] add = toArray ();
        return isBroadcast () ? "[BCAST]" : String.format ( "[%d-%d # %d]", add[0], add[1], this.address );
    }

    public int[] toArray ()
    {
        final ByteBuf buf = Unpooled.buffer ( 2 );
        buf.writeShortLE ( this.address );
        return new int[] { buf.getUnsignedByte ( 0 ), buf.getUnsignedByte ( 1 ) };
    }

    public static ASDUAddress fromArray ( final int[] data )
    {
        if ( data.length > 2 )
        {
            throw new IllegalArgumentException ( "Address may only have a maximum of 2 segments" );
        }

        int address = 0;
        for ( final int i : data )
        {
            address = address << 8 | i;
        }
        return valueOf ( address );
    }

    public static ASDUAddress fromString ( final String value )
    {
        int address = 0;
        for ( final String tok : value.split ( "-" ) )
        {
            address = address << 8 | Integer.parseInt ( tok );
        }
        return valueOf ( address );
    }

    public static ASDUAddress valueOf ( final int address )
    {
        if ( address < 0 )
        {
            throw new IllegalArgumentException ( "'adddress' must not be negative" );
        }
        if ( address > 0xFFFF )
        {
            throw new IllegalArgumentException ( "'adddress' must be below or equal to 0xFFFF" );
        }

        // TODO: pre-fill cache

        return new ASDUAddress ( address );
    }
}
