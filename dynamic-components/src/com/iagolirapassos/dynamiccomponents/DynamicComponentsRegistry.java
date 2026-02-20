package com.iagolirapassos.dynamiccomponents;

import com.google.appinventor.components.runtime.AndroidViewComponent;
import java.util.HashMap;
import java.util.Map;

public class DynamicComponentsRegistry {
    private static DynamicComponentsRegistry instance;
    private HashMap<Integer, AndroidViewComponent> dynamicComponents = new HashMap<>();
    private HashMap<Integer, Long> lastClickTime = new HashMap<>();
    
    private DynamicComponentsRegistry() {}
    
    public static synchronized DynamicComponentsRegistry getInstance() {
        if (instance == null) {
            instance = new DynamicComponentsRegistry();
        }
        return instance;
    }
    
    public void registerComponent(int id, AndroidViewComponent component) {
        dynamicComponents.put(id, component);
    }
    
    public AndroidViewComponent getComponent(int id) {
        return dynamicComponents.get(id);
    }
    
    public void removeComponent(int id) {
        dynamicComponents.remove(id);
    }
    
    public boolean containsComponent(int id) {
        return dynamicComponents.containsKey(id);
    }
    
    public Map<Integer, AndroidViewComponent> getAllComponents() {
        return dynamicComponents;
    }
    
    public void updateLastClickTime(int id) {
        lastClickTime.put(id, System.currentTimeMillis());
    }
    
    public long getLastClickTime(int id) {
        return lastClickTime.containsKey(id) ? lastClickTime.get(id) : 0;
    }
    
    public void clear() {
        dynamicComponents.clear();
        lastClickTime.clear();
    }
}