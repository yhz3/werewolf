package interface_adapter.kill_villager;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class KillVillagerStoryViewModel extends ViewModel {
    private KillVillagerState state = new KillVillagerState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public KillVillagerStoryViewModel() {
        super("Kill Villager Story");
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public KillVillagerState getState() {
        return this.state;
    }

    public void setState(KillVillagerState state) { this.state = state; }
}
