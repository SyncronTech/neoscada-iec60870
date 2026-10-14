/*******************************************************************************
 * Copyright (c) 2014 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Syncron Tech Oy - enhancements and fixes
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.asdu.message;

import org.eclipse.neoscada.protocol.iec60870.ProtocolOptions;
import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.*;

import io.netty.buffer.ByteBuf;

@ASDU(id = 46, name = "C_DC_NA_1", informationStructure = InformationStructure.SINGLE)
public class DoubleCommand extends AbstractDoubleCommand implements ValueCommandMessage {

	public DoubleCommand(final ASDUHeader header, final InformationObjectAddress informationObjectAddress,
			final DoublePoint state, final byte type, final boolean execute)
	{
		super(header, informationObjectAddress, CommandValue.ofDoublePoint(state), false, type, execute);
	}

	public DoubleCommand(final ASDUHeader header, final InformationObjectAddress informationObjectAddress,
			final DoublePoint state)
	{
		this(header, informationObjectAddress, state, (byte)0, true);
	}

	@Override
	public DoubleCommand mirror(final Cause cause, final boolean positive)
	{
		return new DoubleCommand(this.header.clone(cause, positive), this.informationObjectAddress,
				getValue().getValue(), getType(), isExecute());
	}

	public static DoubleCommand parse(final ProtocolOptions options, final byte length, final ASDUHeader header,
			final ByteBuf data)
	{
		final InformationObjectAddress address = InformationObjectAddress.parse(options, data);

		final byte b = data.readByte();

		final DoublePoint state = DoublePoint.values()[b & 0b00000011];
		final byte type = (byte)((b & 0b011111100) >> 2);
		final boolean execute = !((b & 0b100000000) > 0);

		return new DoubleCommand(header, address, state, type, execute);
	}

}
