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
package org.eclipse.scada.configuration.component.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ChildCreationExtenderManager;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.scada.configuration.component.ComponentFactory;
import org.eclipse.scada.configuration.component.ComponentPackage;
import org.eclipse.scada.configuration.component.util.ComponentAdapterFactory;
import org.eclipse.scada.configuration.world.osgi.MarkerGroup;
import org.eclipse.scada.configuration.world.osgi.MasterServer;
import org.eclipse.scada.configuration.world.osgi.OsgiPackage;
import org.eclipse.scada.configuration.world.osgi.SummaryGroup;
import org.eclipse.scada.configuration.world.osgi.util.OsgiSwitch;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentItemProviderAdapterFactory extends ComponentAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender
{
    /**
     * This keeps track of the root adapter factory that delegates to this adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComposedAdapterFactory parentAdapterFactory;

    /**
     * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IChangeNotifier changeNotifier = new ChangeNotifier ();

    /**
     * This helps manage the child creation extenders.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager ( ComponentEditPlugin.INSTANCE, ComponentPackage.eNS_URI );

    /**
     * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Collection<Object> supportedTypes = new ArrayList<Object> ();

    /**
     * This constructs an instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentItemProviderAdapterFactory ()
    {
        supportedTypes.add ( IEditingDomainItemProvider.class );
        supportedTypes.add ( IStructuredItemContentProvider.class );
        supportedTypes.add ( ITreeItemContentProvider.class );
        supportedTypes.add ( IItemLabelProvider.class );
        supportedTypes.add ( IItemPropertySource.class );
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.System} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SystemItemProvider systemItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.System}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSystemAdapter ()
    {
        if ( systemItemProvider == null )
        {
            systemItemProvider = new SystemItemProvider ( this );
        }

        return systemItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.Level} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LevelItemProvider levelItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.Level}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createLevelAdapter ()
    {
        if ( levelItemProvider == null )
        {
            levelItemProvider = new LevelItemProvider ( this );
        }

        return levelItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ConstantValue} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConstantValueItemProvider constantValueItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ConstantValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createConstantValueAdapter ()
    {
        if ( constantValueItemProvider == null )
        {
            constantValueItemProvider = new ConstantValueItemProvider ( this );
        }

        return constantValueItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.MarkerConfiguration} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MarkerConfigurationItemProvider markerConfigurationItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.MarkerConfiguration}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createMarkerConfigurationAdapter ()
    {
        if ( markerConfigurationItemProvider == null )
        {
            markerConfigurationItemProvider = new MarkerConfigurationItemProvider ( this );
        }

        return markerConfigurationItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.PersistentValue} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PersistentValueItemProvider persistentValueItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.PersistentValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createPersistentValueAdapter ()
    {
        if ( persistentValueItemProvider == null )
        {
            persistentValueItemProvider = new PersistentValueItemProvider ( this );
        }

        return persistentValueItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.DriverConnectionAnalyzer} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DriverConnectionAnalyzerItemProvider driverConnectionAnalyzerItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.DriverConnectionAnalyzer}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createDriverConnectionAnalyzerAdapter ()
    {
        if ( driverConnectionAnalyzerItemProvider == null )
        {
            driverConnectionAnalyzerItemProvider = new DriverConnectionAnalyzerItemProvider ( this );
        }

        return driverConnectionAnalyzerItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.MasterImportConnectionAnalyzer} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MasterImportConnectionAnalyzerItemProvider masterImportConnectionAnalyzerItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.MasterImportConnectionAnalyzer}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createMasterImportConnectionAnalyzerAdapter ()
    {
        if ( masterImportConnectionAnalyzerItemProvider == null )
        {
            masterImportConnectionAnalyzerItemProvider = new MasterImportConnectionAnalyzerItemProvider ( this );
        }

        return masterImportConnectionAnalyzerItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.DataMapperAnalyzer} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataMapperAnalyzerItemProvider dataMapperAnalyzerItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.DataMapperAnalyzer}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createDataMapperAnalyzerAdapter ()
    {
        if ( dataMapperAnalyzerItemProvider == null )
        {
            dataMapperAnalyzerItemProvider = new DataMapperAnalyzerItemProvider ( this );
        }

        return dataMapperAnalyzerItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.DataMapperService} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataMapperServiceItemProvider dataMapperServiceItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.DataMapperService}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createDataMapperServiceAdapter ()
    {
        if ( dataMapperServiceItemProvider == null )
        {
            dataMapperServiceItemProvider = new DataMapperServiceItemProvider ( this );
        }

        return dataMapperServiceItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.MappedSourceValue} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MappedSourceValueItemProvider mappedSourceValueItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.MappedSourceValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createMappedSourceValueAdapter ()
    {
        if ( mappedSourceValueItemProvider == null )
        {
            mappedSourceValueItemProvider = new MappedSourceValueItemProvider ( this );
        }

        return mappedSourceValueItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.CalculationComponent} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CalculationComponentItemProvider calculationComponentItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.CalculationComponent}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createCalculationComponentAdapter ()
    {
        if ( calculationComponentItemProvider == null )
        {
            calculationComponentItemProvider = new CalculationComponentItemProvider ( this );
        }

        return calculationComponentItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.InputSpecification} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InputSpecificationItemProvider inputSpecificationItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.InputSpecification}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createInputSpecificationAdapter ()
    {
        if ( inputSpecificationItemProvider == null )
        {
            inputSpecificationItemProvider = new InputSpecificationItemProvider ( this );
        }

        return inputSpecificationItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.OutputSpecification} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OutputSpecificationItemProvider outputSpecificationItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.OutputSpecification}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createOutputSpecificationAdapter ()
    {
        if ( outputSpecificationItemProvider == null )
        {
            outputSpecificationItemProvider = new OutputSpecificationItemProvider ( this );
        }

        return outputSpecificationItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.OutputDefinition} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OutputDefinitionItemProvider outputDefinitionItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.OutputDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createOutputDefinitionAdapter ()
    {
        if ( outputDefinitionItemProvider == null )
        {
            outputDefinitionItemProvider = new OutputDefinitionItemProvider ( this );
        }

        return outputDefinitionItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ItemReferenceInputDefinition} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ItemReferenceInputDefinitionItemProvider itemReferenceInputDefinitionItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ItemReferenceInputDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createItemReferenceInputDefinitionAdapter ()
    {
        if ( itemReferenceInputDefinitionItemProvider == null )
        {
            itemReferenceInputDefinitionItemProvider = new ItemReferenceInputDefinitionItemProvider ( this );
        }

        return itemReferenceInputDefinitionItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ComponentReferenceInputDefinition} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentReferenceInputDefinitionItemProvider componentReferenceInputDefinitionItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ComponentReferenceInputDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createComponentReferenceInputDefinitionAdapter ()
    {
        if ( componentReferenceInputDefinitionItemProvider == null )
        {
            componentReferenceInputDefinitionItemProvider = new ComponentReferenceInputDefinitionItemProvider ( this );
        }

        return componentReferenceInputDefinitionItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.FormulaModule} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FormulaModuleItemProvider formulaModuleItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.FormulaModule}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createFormulaModuleAdapter ()
    {
        if ( formulaModuleItemProvider == null )
        {
            formulaModuleItemProvider = new FormulaModuleItemProvider ( this );
        }

        return formulaModuleItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.AverageModule} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AverageModuleItemProvider averageModuleItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.AverageModule}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createAverageModuleAdapter ()
    {
        if ( averageModuleItemProvider == null )
        {
            averageModuleItemProvider = new AverageModuleItemProvider ( this );
        }

        return averageModuleItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ScriptModule} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScriptModuleItemProvider scriptModuleItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ScriptModule}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createScriptModuleAdapter ()
    {
        if ( scriptModuleItemProvider == null )
        {
            scriptModuleItemProvider = new ScriptModuleItemProvider ( this );
        }

        return scriptModuleItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.Script} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScriptItemProvider scriptItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.Script}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createScriptAdapter ()
    {
        if ( scriptItemProvider == null )
        {
            scriptItemProvider = new ScriptItemProvider ( this );
        }

        return scriptItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.AbsoluteDanglingReference} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbsoluteDanglingReferenceItemProvider absoluteDanglingReferenceItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.AbsoluteDanglingReference}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createAbsoluteDanglingReferenceAdapter ()
    {
        if ( absoluteDanglingReferenceItemProvider == null )
        {
            absoluteDanglingReferenceItemProvider = new AbsoluteDanglingReferenceItemProvider ( this );
        }

        return absoluteDanglingReferenceItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ComponentDanglingReference} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentDanglingReferenceItemProvider componentDanglingReferenceItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ComponentDanglingReference}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createComponentDanglingReferenceAdapter ()
    {
        if ( componentDanglingReferenceItemProvider == null )
        {
            componentDanglingReferenceItemProvider = new ComponentDanglingReferenceItemProvider ( this );
        }

        return componentDanglingReferenceItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.ExternalValue} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExternalValueItemProvider externalValueItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.ExternalValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createExternalValueAdapter ()
    {
        if ( externalValueItemProvider == null )
        {
            externalValueItemProvider = new ExternalValueItemProvider ( this );
        }

        return externalValueItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.SummariesConfiguration} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SummariesConfigurationItemProvider summariesConfigurationItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.SummariesConfiguration}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSummariesConfigurationAdapter ()
    {
        if ( summariesConfigurationItemProvider == null )
        {
            summariesConfigurationItemProvider = new SummariesConfigurationItemProvider ( this );
        }

        return summariesConfigurationItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.eclipse.scada.configuration.component.RestInterceptor} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RestInterceptorItemProvider restInterceptorItemProvider;

    /**
     * This creates an adapter for a {@link org.eclipse.scada.configuration.component.RestInterceptor}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createRestInterceptorAdapter ()
    {
        if ( restInterceptorItemProvider == null )
        {
            restInterceptorItemProvider = new RestInterceptorItemProvider ( this );
        }

        return restInterceptorItemProvider;
    }

    /**
     * This returns the root adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComposeableAdapterFactory getRootAdapterFactory ()
    {
        return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory ();
    }

    /**
     * This sets the composed adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParentAdapterFactory ( ComposedAdapterFactory parentAdapterFactory )
    {
        this.parentAdapterFactory = parentAdapterFactory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isFactoryForType ( Object type )
    {
        return supportedTypes.contains ( type ) || super.isFactoryForType ( type );
    }

    /**
     * This implementation substitutes the factory itself as the key for the adapter.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter adapt ( Notifier notifier, Object type )
    {
        return super.adapt ( notifier, this );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object adapt ( Object object, Object type )
    {
        if ( isFactoryForType ( type ) )
        {
            Object adapter = super.adapt ( object, type );
            if ( ! ( type instanceof Class<?> ) || ( ( (Class<?>)type ).isInstance ( adapter ) ) )
            {
                return adapter;
            }
        }

        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<IChildCreationExtender> getChildCreationExtenders ()
    {
        return childCreationExtenderManager.getChildCreationExtenders ();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Collection<?> getNewChildDescriptors ( Object object, EditingDomain editingDomain )
    {
        return childCreationExtenderManager.getNewChildDescriptors ( object, editingDomain );
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResourceLocator getResourceLocator ()
    {
        return childCreationExtenderManager;
    }

    /**
     * This adds a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void addListener ( INotifyChangedListener notifyChangedListener )
    {
        changeNotifier.addListener ( notifyChangedListener );
    }

    /**
     * This removes a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void removeListener ( INotifyChangedListener notifyChangedListener )
    {
        changeNotifier.removeListener ( notifyChangedListener );
    }

    /**
     * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void fireNotifyChanged ( Notification notification )
    {
        changeNotifier.fireNotifyChanged ( notification );

        if ( parentAdapterFactory != null )
        {
            parentAdapterFactory.fireNotifyChanged ( notification );
        }
    }

    /**
     * This disposes all of the item providers created by this factory. 
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void dispose ()
    {
        if ( systemItemProvider != null )
            systemItemProvider.dispose ();
        if ( levelItemProvider != null )
            levelItemProvider.dispose ();
        if ( constantValueItemProvider != null )
            constantValueItemProvider.dispose ();
        if ( markerConfigurationItemProvider != null )
            markerConfigurationItemProvider.dispose ();
        if ( persistentValueItemProvider != null )
            persistentValueItemProvider.dispose ();
        if ( driverConnectionAnalyzerItemProvider != null )
            driverConnectionAnalyzerItemProvider.dispose ();
        if ( masterImportConnectionAnalyzerItemProvider != null )
            masterImportConnectionAnalyzerItemProvider.dispose ();
        if ( dataMapperAnalyzerItemProvider != null )
            dataMapperAnalyzerItemProvider.dispose ();
        if ( dataMapperServiceItemProvider != null )
            dataMapperServiceItemProvider.dispose ();
        if ( mappedSourceValueItemProvider != null )
            mappedSourceValueItemProvider.dispose ();
        if ( calculationComponentItemProvider != null )
            calculationComponentItemProvider.dispose ();
        if ( inputSpecificationItemProvider != null )
            inputSpecificationItemProvider.dispose ();
        if ( outputSpecificationItemProvider != null )
            outputSpecificationItemProvider.dispose ();
        if ( outputDefinitionItemProvider != null )
            outputDefinitionItemProvider.dispose ();
        if ( itemReferenceInputDefinitionItemProvider != null )
            itemReferenceInputDefinitionItemProvider.dispose ();
        if ( componentReferenceInputDefinitionItemProvider != null )
            componentReferenceInputDefinitionItemProvider.dispose ();
        if ( formulaModuleItemProvider != null )
            formulaModuleItemProvider.dispose ();
        if ( averageModuleItemProvider != null )
            averageModuleItemProvider.dispose ();
        if ( scriptModuleItemProvider != null )
            scriptModuleItemProvider.dispose ();
        if ( scriptItemProvider != null )
            scriptItemProvider.dispose ();
        if ( absoluteDanglingReferenceItemProvider != null )
            absoluteDanglingReferenceItemProvider.dispose ();
        if ( componentDanglingReferenceItemProvider != null )
            componentDanglingReferenceItemProvider.dispose ();
        if ( externalValueItemProvider != null )
            externalValueItemProvider.dispose ();
        if ( summariesConfigurationItemProvider != null )
            summariesConfigurationItemProvider.dispose ();
        if ( restInterceptorItemProvider != null )
            restInterceptorItemProvider.dispose ();
    }

    /**
     * A child creation extender for the {@link OsgiPackage}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class OsgiChildCreationExtender implements IChildCreationExtender
    {
        /**
         * The switch for creating child descriptors specific to each extended class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected static class CreationSwitch extends OsgiSwitch<Object>
        {
            /**
             * The child descriptors being populated.
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            protected List<Object> newChildDescriptors;

            /**
             * The domain in which to create the children.
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            protected EditingDomain editingDomain;

            /**
             * Creates the a switch for populating child descriptors in the given domain.
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            CreationSwitch ( List<Object> newChildDescriptors, EditingDomain editingDomain )
            {
                this.newChildDescriptors = newChildDescriptors;
                this.editingDomain = editingDomain;
            }

            /**
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            @Override
            public Object caseSummaryGroup ( SummaryGroup object )
            {
                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.SUMMARY_GROUP__ITEMS,
                                ComponentFactory.eINSTANCE.createAbsoluteDanglingReference () ) );

                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.SUMMARY_GROUP__ITEMS,
                                ComponentFactory.eINSTANCE.createComponentDanglingReference () ) );

                return null;
            }

            /**
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            @Override
            public Object caseMarkerGroup ( MarkerGroup object )
            {
                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.MARKER_GROUP__ITEMS,
                                ComponentFactory.eINSTANCE.createAbsoluteDanglingReference () ) );

                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.MARKER_GROUP__ITEMS,
                                ComponentFactory.eINSTANCE.createComponentDanglingReference () ) );

                return null;
            }

            /**
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            @Override
            public Object caseMasterServer ( MasterServer object )
            {
                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.MASTER_SERVER__ITEMS,
                                ComponentFactory.eINSTANCE.createAbsoluteDanglingReference () ) );

                newChildDescriptors.add
                        ( createChildParameter
                        ( OsgiPackage.Literals.MASTER_SERVER__ITEMS,
                                ComponentFactory.eINSTANCE.createComponentDanglingReference () ) );

                return null;
            }

            /**
             * <!-- begin-user-doc -->
             * <!-- end-user-doc -->
             * @generated
             */
            protected CommandParameter createChildParameter ( Object feature, Object child )
            {
                return new CommandParameter ( null, feature, child );
            }

        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Collection<Object> getNewChildDescriptors ( Object object, EditingDomain editingDomain )
        {
            ArrayList<Object> result = new ArrayList<Object> ();
            new CreationSwitch ( result, editingDomain ).doSwitch ( (EObject)object );
            return result;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public ResourceLocator getResourceLocator ()
        {
            return ComponentEditPlugin.INSTANCE;
        }
    }

}
