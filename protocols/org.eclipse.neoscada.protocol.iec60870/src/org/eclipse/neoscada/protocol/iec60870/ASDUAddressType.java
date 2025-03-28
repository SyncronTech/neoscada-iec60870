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
package org.eclipse.neoscada.protocol.iec60870;

import io.netty.buffer.ByteBuf;

public enum ASDUAddressType
{
    SIZE_1
    {
        @Override
        public int read ( final ByteBuf data )
        {
            final short value = data.readUnsignedByte ();
            if ( value == 0xFF )
            {
                // since we use 16bit internally, we have to fill the other bits as well
                return 0xFFFF;
            }
            return value;
        }

        @Override
        public void write ( final int address, final ByteBuf out )
        {
            out.writeByte ( address );
        }
    },
    SIZE_2
    {
        @Override
        public int read ( final ByteBuf data )
        {
            return data.readUnsignedShortLE ();
        }

        @Override
        public void write ( final int address, final ByteBuf out )
        {
            out.writeShortLE ( address );
        }
    };

    public abstract int read ( ByteBuf data );

    public abstract void write ( final int address, final ByteBuf out );
}
