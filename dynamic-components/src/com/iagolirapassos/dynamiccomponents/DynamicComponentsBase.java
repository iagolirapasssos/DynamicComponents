package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.FrameLayout;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.util.YailList;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "<p>Base class for dynamic components extension.</p>",
        nonVisible = true,
        iconName = "icon.png"
)
public class DynamicComponentsBase extends AndroidNonvisibleComponent {

    protected ComponentContainer container;
    protected Context context;
    protected DynamicComponentsRegistry registry;
    protected DynamicComponentsEventBus eventBus;
    protected static final long DOUBLE_CLICK_DELAY = 300;

    public DynamicComponentsBase(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        this.context = container.$context();
        this.registry = DynamicComponentsRegistry.getInstance();
        this.eventBus = DynamicComponentsEventBus.getInstance();
        
        // Registrar esta instância no event bus
        this.eventBus.registerListener(this);
    }

    @SimpleFunction(description = "Generate a unique ID.")
    public int GenerateUniqueId() {
        return com.iagolirapassos.helpers.UniqueIdGenerator.generateUniqueId();
    }

    @SimpleFunction(description = "Get a dynamic component by its ID.")
    public AndroidViewComponent GetDynamicComponentById(int componentId) {
        return registry.getComponent(componentId);
    }

    @SimpleFunction(description = "Get the layout view of a dynamic component by its ID.")
    public AndroidViewComponent GetDynamicComponentLayoutById(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            return component;
        }
        return null;
    }

    @SimpleFunction(description = "Get the height of a dynamic component.")
    public int GetDynamicComponentHeight(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            return component.getView().getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Get the width of a dynamic component.")
    public int GetDynamicComponentWidth(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            return component.getView().getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Get the background color of a dynamic component.")
    public int GetDynamicComponentBackgroundColor(int componentId) {
        AndroidViewComponent component = registry.getComponent(componentId);
        if (component != null && component.getView() != null) {
            android.graphics.drawable.Drawable background = component.getView().getBackground();
            if (background instanceof android.graphics.drawable.ColorDrawable) {
                return ((android.graphics.drawable.ColorDrawable) background).getColor();
            }
        }
        return 0;
    }

    @SimpleFunction(description = "Get all registered component IDs.")
    public YailList GetAllComponentIds() {
        java.util.List<Object> idList = new java.util.ArrayList<>();
        for (Integer id : registry.getAllComponents().keySet()) {
            idList.add(id);
        }
        return YailList.makeList(idList);
    }

    @SimpleFunction(description = "Check if a component exists.")
    public boolean ComponentExists(int componentId) {
        return registry.containsComponent(componentId);
    }

    @SimpleFunction(description = "Remove a dynamic component by ID.")
    public void RemoveComponentById(int componentId) {
        registry.removeComponent(componentId);
    }

    @SimpleFunction(description = "Clear all registered components.")
    public void ClearAllComponents() {
        registry.clear();
    }

    protected LinearLayout getLayoutFromComponent(AndroidViewComponent layoutComponent) {
        if (layoutComponent == null || layoutComponent.getView() == null) {
            return null;
        }
        
        View layoutView = layoutComponent.getView();
        LinearLayout layout = null;

        if (layoutView instanceof LinearLayout) {
            layout = (LinearLayout) layoutView;
        } else if (layoutView instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) layoutView;
            if (frameLayout.getChildCount() > 0) {
                View childView = frameLayout.getChildAt(0);
                if (childView instanceof LinearLayout) {
                    layout = (LinearLayout) childView;
                }
            }
        }
        return layout;
    }

    protected boolean isDoubleClick(int componentId) {
        long currentTime = System.currentTimeMillis();
        long lastClick = registry.getLastClickTime(componentId);
        registry.updateLastClickTime(componentId);
        return (currentTime - lastClick) <= DOUBLE_CLICK_DELAY;
    }

    // Métodos para disparar eventos através do event bus
    protected void fireClick(int componentId) {
        eventBus.dispatchClick(componentId);
    }

    protected void fireDoubleClick(int componentId) {
        eventBus.dispatchDoubleClick(componentId);
    }

    protected void fireComponentCreated(String componentName, int componentId) {
        eventBus.dispatchComponentCreated(componentName, componentId);
    }

    protected void fireReportError(String errorMessage) {
        eventBus.dispatchReportError(errorMessage);
    }

    protected void fireTextChanged(int textBoxId, String newText) {
        eventBus.dispatchTextChanged(textBoxId, newText);
    }

    // Eventos que serão recebidos por todas as instâncias
    @SimpleEvent(description = "Event triggered when a dynamic component is clicked.")
    public void Click(int componentId) {
        EventDispatcher.dispatchEvent(this, "Click", componentId);
    }

    @SimpleEvent(description = "Event triggered when a dynamic component is double-clicked.")
    public void DoubleClick(int componentId) {
        EventDispatcher.dispatchEvent(this, "DoubleClick", componentId);
    }

    @SimpleEvent(description = "Event triggered when a dynamic component is created.")
    public void ComponentCreated(String componentName, int componentID) {
        EventDispatcher.dispatchEvent(this, "ComponentCreated", componentName, componentID);
    }

    @SimpleEvent(description = "Report an error with a custom message")
    public void ReportError(String errorMessage) {
        EventDispatcher.dispatchEvent(this, "ReportError", errorMessage);
    }
}