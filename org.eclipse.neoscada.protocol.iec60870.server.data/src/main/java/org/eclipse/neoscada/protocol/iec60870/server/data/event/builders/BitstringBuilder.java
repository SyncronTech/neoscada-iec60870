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
package org.eclipse.neoscada.protocol.iec60870.server.data.event.builders;

import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.BitstringSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.BitstringTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.AbstractMessageBuilder;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.MessageBuilder;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.MessageBuilderFactory;

public class BitstringBuilder implements MessageBuilderFactory<Long> {

	private final boolean withTimestamps;

	public BitstringBuilder(final boolean withTimestamps)
	{
		this.withTimestamps = withTimestamps;
	}

	@Override
	public MessageBuilder<Long, ?> create()
	{
		return new AbstractMessageBuilder<Long, Object>(Long.class, 20, 20, this.withTimestamps ? 10 : -1) {

			@Override
			public Object build()
			{
				validateStart();

				final ASDUHeader header = new ASDUHeader(this.causeOfTransmission, this.asduAddress);

				if (isWithTimestamps()) {
					return BitstringTimeSingle.create(header, this.entries);
				}
				else {
					return BitstringSingle.create(header, this.entries);
				}
			}
		};
	}
}
