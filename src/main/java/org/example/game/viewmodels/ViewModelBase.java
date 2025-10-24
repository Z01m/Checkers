package org.example.game.viewmodels;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ViewModelBase {
    private final PropertyChangeSupport propertyChangeSupport;
    private List<PropertyChangeListener> listeners = new ArrayList<>();

    public ViewModelBase() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.add(listener);
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.remove(listener);
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void onPropertyChanged(String propertyName) {
        propertyChangeSupport.firePropertyChange(propertyName, null, null);
    }

    protected void onPropertyChanged(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void dispose() {
        for (PropertyChangeListener listener : listeners) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }
        listeners.clear();
    }
}
