/*******************************************************************************
 * Copyright (c) 2016 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *     Syncron Tech Oy - enhancements and fixes
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.server.data.event.builders;

import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedSequence;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedSingle;
import org.eclipse.neoscada.protocol.iec60870.asdu.message.MeasuredValueNormalizedTimeSingle;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.AbstractMessageBuilder;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.MessageBuilder;
import org.eclipse.neoscada.protocol.iec60870.server.data.event.MessageBuilderFactory;

public class MeasuredValueNormalizedBuilder implements MessageBuilderFactory<Double> {

	private final boolean withTimestamps;

	public MeasuredValueNormalizedBuilder(final boolean withTimestamps)
	{
		this.withTimestamps = withTimestamps;
	}

	@Override
	public MessageBuilder<Double, ?> create()
	{
		return new AbstractMessageBuilder<Double, Object>(Double.class, 20, 20, this.withTimestamps ? 10 : -1) {

			@Override
			public Object build()
			{
				validateStart();

				final ASDUHeader header = new ASDUHeader(this.causeOfTransmission, this.asduAddress);

				if (isWithTimestamps()) {
					return MeasuredValueNormalizedTimeSingle.create(header, this.entries);
				}
				else if (isContinuous()) {
					return MeasuredValueNormalizedSequence.create(getStartAddress(), header, getValues());
				}
				else {
					return MeasuredValueNormalizedSingle.create(header, this.entries);
				}
			}
		};
	}

}
