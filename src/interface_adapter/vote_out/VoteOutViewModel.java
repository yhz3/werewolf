package interface_adapter.vote_out;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class VoteOutViewModel extends ViewModel {
    private String playerVotedOut;
    private String playerRole;
    private String story;
    private String error;

    public VoteOutViewModel(String viewName) {
        super(viewName);
    }

    public void setPlayerVotedOut(String playerVotedOut) {
        this.playerVotedOut = playerVotedOut;
    }

    public void setPlayerRole(String playerRole) {
        this.playerRole = playerRole;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
