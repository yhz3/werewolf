package interface_adapter.new_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NewGameViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public NewGameViewModel(){super("Started new Game");}

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, "New game started.");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
