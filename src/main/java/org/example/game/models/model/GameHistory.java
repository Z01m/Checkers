package org.example.game.models.model;
import org.example.game.motion.Motion;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GameHistory {

    private List<MotionInfo> motion = new ArrayList<>();
    private final PropertyChangeSupport propertyChangeSupport;

    public static String PROPERTY_MOTIONS = "motions";
    public static final String PROPERTY_MOTION_ADDED = "motionAdded";

    public GameHistory() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public List<MotionInfo> getMotion() {
        return motion;
    }

    public void setMotion(List<MotionInfo> motion) {
        List<MotionInfo> oldMotion = new ArrayList<>(this.motion);
        this.motion = motion;
        propertyChangeSupport.firePropertyChange(PROPERTY_MOTIONS, oldMotion, motion);
    }

    public void addMotion(MotionInfo mtnInfo) {
        List<MotionInfo> oldValue = new ArrayList<>(this.motion);
        this.motion.add(mtnInfo);
        propertyChangeSupport.firePropertyChange(PROPERTY_MOTIONS, oldValue, motion);
        propertyChangeSupport.firePropertyChange(PROPERTY_MOTION_ADDED, oldValue, motion);
    }

    public void addMotionInfo(List<MotionInfo> mtnInfos) {
        if (mtnInfos != null && !mtnInfos.isEmpty()) {
            List<MotionInfo> oldValue = new ArrayList<>(this.motion);
            this.motion.addAll(mtnInfos);
            propertyChangeSupport.firePropertyChange(PROPERTY_MOTIONS, oldValue, this.motion);
        }
    }

    public void clearMotions() {
        if (!motion.isEmpty()) {
            List<MotionInfo> oldValue = new ArrayList<>(this.motion);
            this.motion.clear();
            propertyChangeSupport.firePropertyChange(PROPERTY_MOTIONS, oldValue, this.motion);
        }
    }

    public MotionInfo getLastMotion() {
        return motion.isEmpty() ? null : motion.get(motion.size() - 1);
    }

    public int getMotionCount() {
        return motion.size();
    }

    public boolean isEmpty(){
        return motion.isEmpty();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

}
