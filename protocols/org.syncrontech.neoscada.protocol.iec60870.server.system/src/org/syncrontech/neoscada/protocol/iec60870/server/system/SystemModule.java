package org.syncrontech.neoscada.protocol.iec60870.server.system;

/*******************************************************************************
 * Copyright (c) 2024-2025 Syncron Tech Oy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

import org.eclipse.neoscada.protocol.iec60870.apci.MessageChannel;
import org.eclipse.neoscada.protocol.iec60870.asdu.MessageManager;
import org.eclipse.neoscada.protocol.iec60870.server.Server;
import org.eclipse.neoscada.protocol.iec60870.server.ServerModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.syncrontech.neoscada.protocol.iec60870.server.system.asdu.message.ClockSynchronizationCommand;
import org.syncrontech.neoscada.protocol.iec60870.server.system.asdu.message.TestTimeCommand;

import io.netty.channel.socket.SocketChannel;

public class SystemModule implements ServerModule {

	private final static Logger logger = LoggerFactory.getLogger(SystemModule.class);

	// private final DataModel dataModel;
	//
	// private final DataModuleOptions options;

	public SystemModule()
	{
	}

	@Override
	public void initializeServer(final Server server, final MessageManager manager)
	{
		manager.registerClass(ClockSynchronizationCommand.class);
		manager.registerClass(TestTimeCommand.class);
	}

	@Override
	public void dispose()
	{
		// this.dataModel.stop ();
	}

	@Override
	public void initializeChannel(final SocketChannel channel, final MessageChannel messageChannel)
	{
		logger.debug("Init channel: {}", channel);
		// this.dataModel.start ();
		channel.pipeline().addLast(new SystemModuleHandler(messageChannel));
	}
}
