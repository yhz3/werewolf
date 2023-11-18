package view;

import interface_adapter.kill_villager.KillVillagerController;
import interface_adapter.kill_villager.KillVillagerViewModel;
import interface_adapter.new_game.NewGameViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KillVillagerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "kill villager";
    private final KillVillagerViewModel killVillagerViewModel;
    private final KillVillagerController killVillagerController;

    public KillVillagerView(KillVillagerViewModel killVillagerViewModel, KillVillagerController killVillagerController, KillVillagerViewModel killVillagerViewModel1, KillVillagerController killVillagerController1) {
        this.killVillagerViewModel = killVillagerViewModel1;
        this.killVillagerController = killVillagerController1;
        killVillagerViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(KillVillagerViewModel.TITLE_LABEL);

        this.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
