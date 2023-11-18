package interface_adapter.continue_to_day;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DayViewModel extends ViewModel {
    public final String TITLE_LABEL = "Day View";
    public final String PLAYER_VOTED_OUT_LABEL = "Please enter the player to be voted out";
    public final String PLAYER_VOTED_OUT_BUTTON_LABEL = "Vote Out";

    private DayState state = new DayState();

    public void setState(DayState state) {
        this.state = state;
    }

    public DayState getState() {
        return state;
    }

    public DayViewModel() {
        super("day");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
