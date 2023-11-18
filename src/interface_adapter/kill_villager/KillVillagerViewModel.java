package interface_adapter.kill_villager;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class KillVillagerViewModel extends ViewModel {

    private KillVillagerState state = new KillVillagerState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public KillVillagerViewModel() {
        super("kill villager");
    }

    public void setState(KillVillagerState state) {
        this.state = state;
    }

    public KillVillagerState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
