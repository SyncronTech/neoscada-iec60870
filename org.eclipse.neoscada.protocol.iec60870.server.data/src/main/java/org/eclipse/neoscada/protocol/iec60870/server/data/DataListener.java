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

public interface DataListener {

	public void dataChangeSinglePoint(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Boolean>> values, boolean withTimestamps);

	public void dataChangeDoublePoint(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<DoublePoint>> values, boolean withTimestamps);

	public void dataChangeShortFloatingPoint(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Float>> values, boolean withTimestamps);

	public void dataChangeScaled(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Short>> values, boolean withTimestamps);

	public void dataChangeNormalized(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Double>> values, boolean withTimestamps);

	public void dataChangeBitstring(CauseOfTransmission cause, ASDUAddress asduAddress,
			List<InformationEntry<Long>> values, boolean withTimestamps);
}
