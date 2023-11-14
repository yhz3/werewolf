package interface_adapter.vote_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class VoteOutViewModel extends ViewModel {

    private VoteOutState state = new VoteOutState();

    public VoteOutViewModel() {
        super("vote out player");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public VoteOutState getState() {
        return this.state;
    }
}
