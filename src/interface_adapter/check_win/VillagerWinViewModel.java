package interface_adapter.check_win;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VillagerWinViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public VillagerWinViewModel(){super("Villagers Win");}

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, "The Villagers have won.");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
