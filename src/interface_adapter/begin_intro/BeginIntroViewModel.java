package interface_adapter.begin_intro;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BeginIntroViewModel extends ViewModel {
    private String introStory;
    public static final String TITLE_LABEL = "Intro View";
    public static final String BUTTON_LABEL = "Generate Story";


    public BeginIntroViewModel() {
        super("intro");
    }

    public void setIntroStory(String introStory) {
        this.introStory = introStory;
    }

    public String getIntroStory() {
        return introStory;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, introStory);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
