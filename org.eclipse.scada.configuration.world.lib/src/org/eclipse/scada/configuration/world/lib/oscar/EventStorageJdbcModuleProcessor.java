/*******************************************************************************
 * Copyright (c) 2013 IBH SYSTEMS GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.scada.configuration.world.lib.oscar;

import org.eclipse.scada.configuration.generator.Profiles;
import org.eclipse.scada.configuration.world.osgi.EquinoxApplication;
import org.eclipse.scada.configuration.world.osgi.EventStorageJdbc;
import org.eclipse.scada.configuration.world.osgi.profile.Profile;

public class EventStorageJdbcModuleProcessor extends AbstractEventStorageJdbcModuleProcessor<EventStorageJdbc>
{
    private final EquinoxApplication app;

    public EventStorageJdbcModuleProcessor ( final EquinoxApplication app, final OscarContext ctx )
    {
        super ( app, ctx, EventStorageJdbc.class );
        this.app = app;
    }

    @Override
    protected void process ( final EventStorageJdbc module )
    {
        final Profile profile = Profiles.createOrGetCustomizationProfile ( this.app );

        processCommon ( module );

        Profiles.addStartBundle ( profile, "org.eclipse.scada.ae.server.storage.jdbc" ); //$NON-NLS-1$
        Profiles.addSystemProperty ( profile, "org.eclipse.scada.ae.server.storage.jdbc.maxlength", module.getMaxFieldLength () );
        Profiles.addSystemProperty ( profile, "org.eclipse.scada.ae.server.storage.jdbc.query.fetchSize", module.getQueryFetchSize () );
    }
}
