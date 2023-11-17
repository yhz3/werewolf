package interface_adapter.check_win;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WerewolfWinViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public WerewolfWinViewModel(){super("Werewolf Win");}

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, "Werewolves have won.");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
