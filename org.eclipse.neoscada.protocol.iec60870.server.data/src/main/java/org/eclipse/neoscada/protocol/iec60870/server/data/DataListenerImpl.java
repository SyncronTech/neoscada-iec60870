/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Red Hat Inc - enhancements
 *     Syncron Tech Oy - enhancements and fixes
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.server.data;

import java.util.List;

import org.eclipse.neoscada.protocol.iec60870.asdu.ASDUHeader;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDUAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.CauseOfTransmission;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.DoublePoint;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationEntry;

public class DataListenerImpl implements DataListener {

	private final DataModuleMessageSource source;

	public DataListenerImpl(final DataModuleMessageSource source)
	{
		this.source = source;
	}

	@Override
	public void dataChangeSinglePoint(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Boolean>> values, boolean withTimestamps)
	{
		this.source.sendSinglePointValues(new ASDUHeader(cause, asduAddress), values, withTimestamps);
	}

	@Override
	public void dataChangeDoublePoint(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<DoublePoint>> values, boolean withTimestamps)
	{
		this.source.sendDoublePointValues(new ASDUHeader(cause, asduAddress), values, withTimestamps);
	}

	@Override
	public void dataChangeShortFloatingPoint(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Float>> values, boolean withTimestamps)
	{
		this.source.sendMeasuredValueShortFloatingPointValues(new ASDUHeader(cause, asduAddress), values,
				withTimestamps);
	}

	@Override
	public void dataChangeScaled(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Short>> values, boolean withTimestamps)
	{
		this.source.sendMeasuredValueScaledValues(new ASDUHeader(cause, asduAddress), values, withTimestamps);
	}

	@Override
	public void dataChangeNormalized(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Double>> values, boolean withTimestamps)
	{
		this.source.sendMeasuredValueNormalizedValues(new ASDUHeader(cause, asduAddress), values, withTimestamps);
	}

	@Override
	public void dataChangeBitstring(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Long>> values, boolean withTimestamps)
	{
		this.source.sendBitstringValues(new ASDUHeader(cause, asduAddress), values, withTimestamps);
	}
}
