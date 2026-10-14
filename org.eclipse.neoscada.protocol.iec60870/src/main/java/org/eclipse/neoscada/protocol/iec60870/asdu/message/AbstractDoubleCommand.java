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
import org.eclipse.neoscada.protocol.iec60870.asdu.types.CommandValue;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.DoublePoint;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.TypeHelper;

import io.netty.buffer.ByteBuf;

public class AbstractDoubleCommand extends AbstractInformationObjectMessage {

	private final CommandValue<DoublePoint>	value;

	private final boolean					withTimestamp;

	private final byte						type;

	private final boolean					execute;

	public AbstractDoubleCommand(final ASDUHeader header, final InformationObjectAddress informationObjectAddress,
			final CommandValue<DoublePoint> value, final boolean withTimestamp, final byte type, final boolean execute)
	{
		super(header, informationObjectAddress);
		this.value = value;
		this.withTimestamp = withTimestamp;
		this.type = type;
		this.execute = execute;
	}

	public byte getType()
	{
		return this.type;
	}

	public boolean isExecute()
	{
		return this.execute;
	}

	public CommandValue<DoublePoint> getValue()
	{
		return value;
	}

	@Override
	public void encode(final ProtocolOptions options, final ByteBuf out)
	{
		EncodeHelper.encodeHeader(this, options, null, this.header, out);
		byte b = 0;

		b |= (byte)(value.getValue().ordinal() & 0b00000011);
		b |= this.type << 2 & 0b011111100;
		b |= this.execute ? 0 : 0b100000000;

		this.informationObjectAddress.encode(options, out);

		out.writeByte(b);

		if (withTimestamp) {
			TypeHelper.encodeTimestamp(options, out, value.getTimestamp());
		}
	}

}
