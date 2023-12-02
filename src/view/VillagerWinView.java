package view;

import interface_adapter.check_win.CheckWinController;
import interface_adapter.check_win.VillagerWinState;
import interface_adapter.check_win.VillagerWinViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VillagerWinView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Villagers Win";

    private final VillagerWinViewModel villagerWinViewModel;

    private final CheckWinController checkWinController;

    private final JButton newGameButton;

    public VillagerWinView(VillagerWinViewModel villagerWinViewModel, CheckWinController checkWinController){
        this.villagerWinViewModel = villagerWinViewModel;
        this.checkWinController = checkWinController;
        villagerWinViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(VillagerWinViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        newGameButton = new JButton(VillagerWinViewModel.NEW_GAME_BUTTON_LABEL);
        buttons.add(newGameButton);

        newGameButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(newGameButton)) {
                            checkWinController.restartGame();
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void propertyChange(PropertyChangeEvent evt){
        if (evt.getNewValue() instanceof VillagerWinState state) {
            String story = state.getVillagerWinStory().replace("\\n", "\n");
            JTextArea textArea = new JTextArea(15, 30);
            textArea.setText(story);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            textArea.setEditable(false);
        }
    }
}
