package view;

import interface_adapter.check_win.*;
import interface_adapter.new_game.NewGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckWinView extends JPanel implements PropertyChangeListener, ActionListener {

    public final String viewName = "Game Over View";

    private CheckWinController checkWinController;

    private VillagerWinViewModel villagerWinViewModel;

    private WerewolfWinViewModel werewolfWinViewModel;

    private final JButton newGame;

    public CheckWinView(CheckWinController checkWinController, VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel){
        this.checkWinController = checkWinController;
        this.villagerWinViewModel = villagerWinViewModel;
        this.werewolfWinViewModel = werewolfWinViewModel;

        this.villagerWinViewModel.addPropertyChangeListener(this);
        this.werewolfWinViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Game Over");

        JPanel buttons = new JPanel();
        newGame = new JButton("Start new game");
        buttons.add(newGame);

        newGame.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(newGame)){

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof VillagerWinState state){
            String story = state.getVillagerWinStory().replace("\\n", "\n");
            JTextArea textArea = new JTextArea(15, 30);
            textArea.setText(story);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            textArea.setEditable(false);
        } else if (evt.getNewValue() instanceof WerewolfWinState state){
            String story = state.getWerewolfWinStory().replace("\\n", "\n");
            JTextArea textArea = new JTextArea(15, 30);
            textArea.setText(story);
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setCaretPosition(0);
            textArea.setEditable(false);
        }
    }
}
