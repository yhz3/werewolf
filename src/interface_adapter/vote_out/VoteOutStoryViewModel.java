package interface_adapter.vote_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VoteOutStoryViewModel extends ViewModel {

    // LABEL NAMES HERE
    private VoteOutStoryState state = new VoteOutStoryState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public VoteOutStoryViewModel() {
        super("vote out player story");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public VoteOutStoryState getState() {
        return this.state;
    }

    public void setState(VoteOutStoryState state) { this.state = state; }
}
