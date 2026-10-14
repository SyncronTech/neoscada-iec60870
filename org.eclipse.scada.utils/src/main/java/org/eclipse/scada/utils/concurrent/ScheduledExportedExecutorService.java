/*******************************************************************************
 * Copyright (c) 2006, 2015 TH4 SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     TH4 SYSTEMS GmbH - initial API and implementation
 *     IBH SYSTEMS GmbH - add constructor, add default builder method
 *     Syncron Tech Oy - enhancements and fixes
 *******************************************************************************/
package org.eclipse.scada.utils.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ScheduledExportedExecutorService extends ScheduledThreadPoolExecutor {

	public static ScheduledExportedExecutorService newSingleThreadExportedScheduledExecutor(final String name)
	{
		return new ScheduledExportedExecutorService(name);
	}

	/**
	 * Create a scheduled executor service with a maximum of 1 thread
	 *
	 * @param name
	 *            the base name of the threads
	 */
	public ScheduledExportedExecutorService(final String name)
	{
		this(name, 1);
	}

	public ScheduledExportedExecutorService(final String name, final int corePoolSize)
	{
		super(corePoolSize, new NamedThreadFactory(name));
	}
}
