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

import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDUAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.CauseOfTransmission;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.DoublePoint;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationEntry;

import com.google.common.util.concurrent.ListenableFuture;

public class DefaultSubscription implements Subscription {

	private final AbstractBaseDataModel	dataModel;

	private final DataListener			listener;

	public DefaultSubscription(final AbstractBaseDataModel dataModel, final DataListener listener)
	{
		this.dataModel = dataModel;
		this.listener = listener;
	}

	@Override
	public ListenableFuture<Void> dispose()
	{
		return this.dataModel.disposeSubscription(this);
	}

	public void notifyChangeSinglePoint(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Boolean>> values, boolean withTimestamps)
	{
		this.listener.dataChangeSinglePoint(cause, asduAddress, values, withTimestamps);
	}

	public void notifyChangeDoublePoint(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<DoublePoint>> values, boolean withTimestamps)
	{
		this.listener.dataChangeDoublePoint(cause, asduAddress, values, withTimestamps);
	}

	public void notifyChangeShortFloatingPoint(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Float>> values, boolean withTimestamps)
	{
		this.listener.dataChangeShortFloatingPoint(cause, asduAddress, values, withTimestamps);
	}

	public void notifyChangeScaled(final CauseOfTransmission cause, final ASDUAddress asduAddress,
			final List<InformationEntry<Short>> values, boolean withTimestamps)
	{
		this.listener.dataChangeScaled(cause, asduAddress, values, withTimestamps);
	}

	public void notifyChangeNormalized(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Double>> values, boolean withTimestamps)
	{
		this.listener.dataChangeNormalized(cause, asduAddress, values, withTimestamps);
	}

	public void notifyChangeBitstring(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Long>> values, boolean withTimestamps)
	{
		this.listener.dataChangeBitstring(cause, asduAddress, values, withTimestamps);
	}
}
