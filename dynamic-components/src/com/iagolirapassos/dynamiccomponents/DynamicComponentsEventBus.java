package com.iagolirapassos.dynamiccomponents;

import java.util.ArrayList;
import java.util.List;

public class DynamicComponentsEventBus {
    private static DynamicComponentsEventBus instance;
    private List<DynamicComponentsBase> listeners = new ArrayList<>();
    
    private DynamicComponentsEventBus() {}
    
    public static synchronized DynamicComponentsEventBus getInstance() {
        if (instance == null) {
            instance = new DynamicComponentsEventBus();
        }
        return instance;
    }
    
    public void registerListener(DynamicComponentsBase listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    
    public void unregisterListener(DynamicComponentsBase listener) {
        listeners.remove(listener);
    }
    
    public void dispatchClick(int componentId) {
        for (DynamicComponentsBase listener : listeners) {
            listener.Click(componentId);
        }
    }
    
    public void dispatchDoubleClick(int componentId) {
        for (DynamicComponentsBase listener : listeners) {
            listener.DoubleClick(componentId);
        }
    }
    
    public void dispatchComponentCreated(String componentName, int componentId) {
        for (DynamicComponentsBase listener : listeners) {
            listener.ComponentCreated(componentName, componentId);
        }
    }
    
    public void dispatchReportError(String errorMessage) {
        for (DynamicComponentsBase listener : listeners) {
            listener.ReportError(errorMessage);
        }
    }
    
    public void dispatchTextChanged(int textBoxId, String newText) {
        for (DynamicComponentsBase listener : listeners) {
            if (listener instanceof DynamicComponentsCore) {
                ((DynamicComponentsCore) listener).TextChangedEvent(textBoxId, newText);
            }
        }
    }
}