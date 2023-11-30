package view;

import interface_adapter.check_win.CheckWinController;
import interface_adapter.check_win.CheckWinPresenter;
import interface_adapter.check_win.WerewolfWinState;
import interface_adapter.check_win.WerewolfWinViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WerewolfWinView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Werewolf Win";

    private final WerewolfWinViewModel werewolfWinViewModel;

    private final CheckWinController checkWinController;

    private final JButton endGame;

    public WerewolfWinView(WerewolfWinViewModel werewolfWinViewModel, CheckWinController checkWinController){
        this.werewolfWinViewModel = werewolfWinViewModel;
        this.checkWinController = checkWinController;

        this.werewolfWinViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(WerewolfWinViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        endGame = new JButton(WerewolfWinViewModel.BUTTON_LABEL);
        buttons.add(endGame);

        endGame.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(endGame)){
                            WerewolfWinState state = werewolfWinViewModel.getState();
                            String story = state.getWerewolfWinStory().replace("\\n", "\n");
                            JTextArea textArea = new JTextArea(15, 30);
                            textArea.setText(story);
                            textArea.setWrapStyleWord(true);
                            textArea.setLineWrap(true);
                            textArea.setCaretPosition(0);
                            textArea.setEditable(false);

                            JScrollPane scrollPane = new JScrollPane(textArea);
                            JOptionPane.showMessageDialog(textArea, scrollPane);
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
        if (evt.getNewValue() instanceof WerewolfWinState state) {
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
