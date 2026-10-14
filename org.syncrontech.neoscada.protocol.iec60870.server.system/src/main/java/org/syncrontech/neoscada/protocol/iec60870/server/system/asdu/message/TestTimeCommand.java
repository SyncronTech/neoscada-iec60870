package org.syncrontech.neoscada.protocol.iec60870.server.system.asdu.message;

/*******************************************************************************
 * Copyright (c) 2024-2025 Syncron Tech Oy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

import org.eclipse.neoscada.protocol.iec60870.ProtocolOptions;
import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.AbstractInformationObjectMessage;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.EncodeHelper;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MirrorableMessage;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDU;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Cause;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationStructure;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.TypeHelper;

import io.netty.buffer.ByteBuf;

@ASDU(id = 107, name = "C_TS_TA_1", informationStructure = InformationStructure.SINGLE)

public class TestTimeCommand extends AbstractInformationObjectMessage implements MirrorableMessage<TestTimeCommand> {

	private final int	testCounter;
	private final long	time;

	public TestTimeCommand(final ASDUHeader header, final InformationObjectAddress informationObjectAddress,
			int testCounter, long time)
	{
		super(header, informationObjectAddress);
		this.testCounter = testCounter;
		this.time = time;
	}

	@Override
	public void encode(final ProtocolOptions options, final ByteBuf out)
	{
		EncodeHelper.encodeHeader(this, options, null, this.header, out);

		this.informationObjectAddress.encode(options, out);
		out.writeShort(testCounter);
		TypeHelper.encodeTimestamp(options, out, time);
	}

	public static TestTimeCommand parse(final ProtocolOptions options, final byte length, final ASDUHeader header,
			final ByteBuf data)
	{
		final InformationObjectAddress address = InformationObjectAddress.parse(options, data);

		final int testCounter = data.readUnsignedShort();
		final long time = TypeHelper.parseTimestamp(options, data);

		return new TestTimeCommand(header, address, testCounter, time);
	}

	@Override
	public TestTimeCommand mirror(Cause cause, boolean positive)
	{
		return new TestTimeCommand(this.header.clone(cause, positive), this.informationObjectAddress, testCounter,
				time);
	}

}
