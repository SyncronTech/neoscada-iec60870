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
package org.eclipse.scada.configuration.component.generator.interceptor;

import org.eclipse.scada.configuration.component.ItemInterceptor;
import org.eclipse.scada.configuration.component.RestInterceptor;
import org.eclipse.scada.configuration.component.lib.ItemInterceptorHandler;
import org.eclipse.scada.configuration.generator.GeneratorContext.MasterContext;
import org.eclipse.scada.configuration.world.osgi.ApplicationModule;
import org.eclipse.scada.configuration.world.osgi.Item;
import org.eclipse.scada.configuration.world.osgi.OsgiFactory;
import org.eclipse.scada.configuration.world.osgi.RestExporter;

public class RestItemInterceptorHandler implements ItemInterceptorHandler
{
    @Override
    public boolean interceptItem ( final Item item, final ItemInterceptor interceptorElement, final MasterContext masterContext )
    {
        final RestInterceptor interceptor = (RestInterceptor)interceptorElement;

        for ( final ApplicationModule a : masterContext.getImplementation ().getModules () )
        {
            if ( a instanceof RestExporter )
            {
                if ( ( (RestExporter)a ).getContextId ().equals ( interceptor.getContextId () ) )
                {
                    // found context -> insert
                    ( (RestExporter)a ).getItems ().add ( item );
                    return true;
                }
            }
        }

        // did not find context -> create context

        final RestExporter exporter = OsgiFactory.eINSTANCE.createRestExporter ();
        exporter.setContextId ( interceptor.getContextId () );
        masterContext.getImplementation ().getModules ().add ( exporter );
        exporter.getItems ().add ( item );

        return true;
    }

}
