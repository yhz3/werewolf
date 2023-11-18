package interface_adapter.reveal_role;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RevealRoleViewModel extends ViewModel {

    private String role;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RevealRoleViewModel(){super("Reveal role");}

    public void setRole(String role){this.role = role;}

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.role);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }


}
