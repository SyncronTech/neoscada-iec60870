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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.neoscada.protocol.iec60870.ProtocolOptions;
import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDU;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationEntry;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationStructure;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Value;

import io.netty.buffer.ByteBuf;

@ASDU(id = 33, name = "M_BO_TB_1", informationStructure = InformationStructure.SINGLE)
public class BitstringTimeSingle extends AbstractBitstring {

	private BitstringTimeSingle(final ASDUHeader header, final List<InformationEntry<Long>> entries)
	{
		super(header, entries, true);
	}

	public static BitstringTimeSingle parse(final ProtocolOptions options, final byte length, final ASDUHeader header,
			final ByteBuf data)
	{
		return new BitstringTimeSingle(header, parseEntries(options, length, data, true));
	}

	public static BitstringTimeSingle create(final ASDUHeader header, final InformationObjectAddress address,
			final Value<Long> value)
	{
		return createInternal(header, Collections.singletonList(new InformationEntry<>(address, value)));
	}

	public static BitstringTimeSingle create(final ASDUHeader header, final List<InformationEntry<Long>> values)
	{
		if (values.size() > MAX_INFORMATION_ENTRIES) {
			throw new IllegalArgumentException(
					String.format("A maximum of %s values can be transmitted", MAX_INFORMATION_ENTRIES));
		}
		return createInternal(header, new ArrayList<>(values));
	}

	private static BitstringTimeSingle createInternal(final ASDUHeader header,
			final List<InformationEntry<Long>> values)
	{
		return new BitstringTimeSingle(header, values);
	}

}
