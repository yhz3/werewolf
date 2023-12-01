package interface_adapter.check_win;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VillagerWinViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Villagers have won!";

    public static final String STORY_BUTTON_LABEL = "View Story";

    public static final String NEW_GAME_BUTTON_LABEL = "New Game";

    private VillagerWinState state = new VillagerWinState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public VillagerWinViewModel(){super("Villagers Win");}

    public void setState(VillagerWinState state){
        this.state = state;
    }

    public VillagerWinState getState(){
        return state;
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
