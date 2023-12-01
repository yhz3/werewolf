package interface_adapter.check_win;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WerewolfWinViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Werewolves have won...";

    public static final String STORY_BUTTON_LABEL = "View Story";

    public static final String NEW_GAME_BUTTON_LABEL = "New Game";

    public WerewolfWinState state = new WerewolfWinState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WerewolfWinViewModel(){super("Werewolf Win");}

    public void setState(WerewolfWinState state){
        this.state = state;
    }

    public WerewolfWinState getState(){
        return state;
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
