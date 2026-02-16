package com.iagolirapassos.dynamiccomponents;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.util.Log;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.AndroidViewComponent;

import java.util.HashMap;
import java.util.Map;

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
    protected HashMap<Integer, AndroidViewComponent> dynamicComponents = new HashMap<>();
    protected HashMap<Integer, Long> lastClickTime = new HashMap<>();
    protected static final long DOUBLE_CLICK_DELAY = 300;

    public DynamicComponentsBase(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        this.context = container.$context();
    }

    @SimpleFunction(description = "Generate a unique ID.")
    public int GenerateUniqueId() {
        return io.dynamiccomponents.helpers.UniqueIdGenerator.generateUniqueId();
    }

    @SimpleFunction(description = "Get a dynamic component by its ID.")
    public AndroidViewComponent GetDynamicComponentById(int componentId) {
        return dynamicComponents.get(componentId);
    }

    @SimpleFunction(description = "Get the layout view of a dynamic component by its ID.")
    public AndroidViewComponent GetDynamicComponentLayoutById(int componentId) {
        AndroidViewComponent component = dynamicComponents.get(componentId);
        if (component != null && component.getView() != null) {
            // Retorna o próprio componente, que já contém a view
            return component;
        }
        return null;
    }

    @SimpleFunction(description = "Get the height of a dynamic component.")
    public int GetDynamicComponentHeight(int componentId) {
        AndroidViewComponent component = dynamicComponents.get(componentId);
        if (component != null && component.getView() != null) {
            return component.getView().getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Get the width of a dynamic component.")
    public int GetDynamicComponentWidth(int componentId) {
        AndroidViewComponent component = dynamicComponents.get(componentId);
        if (component != null && component.getView() != null) {
            return component.getView().getWidth();
        }
        return 0;
    }

    protected LinearLayout getLayoutFromComponent(AndroidViewComponent layoutComponent) {
        View layoutView = layoutComponent.getView();
        LinearLayout layout = null;

        if (layoutView instanceof LinearLayout) {
            layout = (LinearLayout) layoutView;
        } else if (layoutView instanceof android.widget.FrameLayout) {
            android.widget.FrameLayout frameLayout = (android.widget.FrameLayout) layoutView;
            if (frameLayout.getChildCount() > 0) {
                View childView = frameLayout.getChildAt(0);
                if (childView instanceof LinearLayout) {
                    layout = (LinearLayout) childView;
                }
            }
        }
        return layout;
    }

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