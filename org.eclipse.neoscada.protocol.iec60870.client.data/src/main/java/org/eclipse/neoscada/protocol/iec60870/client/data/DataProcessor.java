/*******************************************************************************
 * Copyright (c) 2014, 2016 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *     Red Hat Inc - refactoring
 *     Syncron Tech Oy - enhancements and fixes
 *******************************************************************************/
package org.eclipse.neoscada.protocol.iec60870.client.data;

import java.util.concurrent.Executor;

import org.eclipse.neoscada.protocol.iec60870.asdu.types.ASDUAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.InformationObjectAddress;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.QualifierOfInterrogation;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.Value;

import io.netty.channel.ChannelHandlerContext;

public class DataProcessor extends AbstractDataProcessor {

	private final DataListener	listener;

	private final Executor		executor;

	private boolean				delayStart;

	private Runnable			requestStartDataRunner;

	private Runnable			interrogationRunner;

	public DataProcessor(final Executor executor, final DataListener listener)
	{
		this.executor = executor;
		this.listener = listener;
		this.delayStart = false;
	}

	public DataProcessor(final Executor executor, final DataListener dataListener, final boolean delayStart)
	{
		this.executor = executor;
		this.listener = dataListener;
		this.delayStart = delayStart;
	}

	@Override
	public void activated(final DataModuleContext dataModuleContext, final ChannelHandlerContext ctx)
	{
		// wrap both in closures, so we don't have to store it in a local field
		this.requestStartDataRunner = new Runnable() {

			@Override
			public void run()
			{
				dataModuleContext.requestStartData();
			}
		};
		this.interrogationRunner = new Runnable() {

			@Override
			public void run()
			{
				dataModuleContext.startInterrogation(ASDUAddress.BROADCAST, QualifierOfInterrogation.GLOBAL);
			}
		};
		if (!delayStart) {
			try {
				this.requestStartDataRunner.run();
			}
			finally {
				this.requestStartDataRunner = null;
			}
		}
	}

	@Override
	public void started()
	{
		this.executor.execute(new Runnable() {

			@Override
			public void run()
			{
				DataProcessor.this.listener.started();
			};
		});
		if (this.interrogationRunner != null) {
			try {
				this.interrogationRunner.run();
			}
			finally {
				this.interrogationRunner = null;
			}
		}
	}

	@Override
	public void disconnected()
	{
		this.requestStartDataRunner = null;
		this.interrogationRunner = null;

		this.executor.execute(new Runnable() {

			@Override
			public void run()
			{
				DataProcessor.this.listener.disconnected();
			};
		});
	}

	@Override
	protected void fireEntry(final ASDUAddress asduAddress, final InformationObjectAddress address,
			final Value<?> value)
	{
		this.executor.execute(new Runnable() {

			@Override
			public void run()
			{
				DataProcessor.this.listener.update(asduAddress, address, value);
			}
		});
	}

	@Override
	public void requestStartData()
	{
		if (this.requestStartDataRunner != null) {
			try {
				this.requestStartDataRunner.run();
			}
			finally {
				this.requestStartDataRunner = null;
			}
		}
	}
}
