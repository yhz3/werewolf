package interface_adapter.vote_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VoteOutViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Vote out view";
    public static final String BUTTON_LABEL = "Vote out Player";

    public static final String PLAYER_LABEL = "Enter player name:";
    private VoteOutState state = new VoteOutState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public VoteOutViewModel() {
        super("vote out player");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public VoteOutState getState() {
        return this.state;
    }

    public void setState(VoteOutState state) { this.state = state; }

}
