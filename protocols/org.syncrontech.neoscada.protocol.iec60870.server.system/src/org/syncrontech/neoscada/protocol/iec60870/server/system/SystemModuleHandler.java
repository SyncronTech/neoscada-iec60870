package org.syncrontech.neoscada.protocol.iec60870.server.system;

/*******************************************************************************
 * Copyright (c) 2024-2025 Syncron Tech Oy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

import org.eclipse.neoscada.protocol.iec60870.apci.MessageChannel;
import org.eclipse.neoscada.protocol.iec60870.asdu.types.StandardCause;
import org.eclipse.neoscada.protocol.iec60870.io.AbstractModuleHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.syncrontech.neoscada.protocol.iec60870.server.system.asdu.message.ClockSynchronizationCommand;
import org.syncrontech.neoscada.protocol.iec60870.server.system.asdu.message.TestTimeCommand;

import io.netty.channel.ChannelHandlerContext;

public class SystemModuleHandler extends AbstractModuleHandler {

	private final static Logger		logger	= LoggerFactory.getLogger(SystemModuleHandler.class);

	public SystemModuleHandler(final MessageChannel messageChannel)
	{
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{

		if (msg instanceof TestTimeCommand) {

			TestTimeCommand mc = ((TestTimeCommand)msg).mirror(StandardCause.ACTIVATION_CONFIRM, true);
			ctx.writeAndFlush(mc);
		}
		else if (msg instanceof ClockSynchronizationCommand) {
			ClockSynchronizationCommand cmd = (ClockSynchronizationCommand)msg;

			logger.debug("Clock synchronization received: {}", cmd.getTime().toString());
			ClockSynchronizationCommand mc = cmd.mirror(StandardCause.ACTIVATION_CONFIRM, true);
			ctx.writeAndFlush(mc);
		}
		else // otherwise pass the message on to the next handler
			ctx.fireChannelRead(msg);
	}
}
