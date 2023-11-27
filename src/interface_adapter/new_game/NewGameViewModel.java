package interface_adapter.new_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewGameViewModel extends ViewModel {
    public static final String NEW_GAME_LABEL = "New Game";
    public static final String TITLE_LABEL = "New Game View";
    public static String[] PLAYER_LABELS;
    private NewGameState state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public NewGameViewModel(int numberOfPlayers) {
        super("new game");
        PLAYER_LABELS = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            int playerNumber = i + 1;
            PLAYER_LABELS[i] = "Enter Player " + playerNumber;
        }
        this.state = new NewGameState(numberOfPlayers);
    }


    public void setState(NewGameState state) {
        this.state = state;
    }

    public NewGameState getState() {
        return state;
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
