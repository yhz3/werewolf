package interface_adapter.kill_villager;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class KillVillagerViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Kill Villager View";
    public static final String BUTTON_LABEL = "Kill Villager";
    public static final String VILLAGER_LABEL = "Enter Villager Name";

    // So the ViewModel has the current Villagers left in the game
    private String[] villagers;

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

    public void setVillagers(String[] villagers) {
        this.villagers = villagers;
    }

    public String[] getVillagers() {
        return villagers;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
