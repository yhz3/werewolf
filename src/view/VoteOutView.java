package view;

import interface_adapter.kill_villager.KillVillagerState;
import interface_adapter.kill_villager.KillVillagerViewModel;
import interface_adapter.vote_out.VoteOutController;
import interface_adapter.vote_out.VoteOutState;
import interface_adapter.vote_out.VoteOutViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VoteOutView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "vote out player";

    private final VoteOutViewModel voteOutViewModel;

    private final VoteOutController voteOutController;

    private final JButton voteOutPlayer;

    public VoteOutView(VoteOutViewModel voteOutViewModel, VoteOutController voteOutController){
        this.voteOutViewModel = voteOutViewModel;
        this.voteOutController = voteOutController;
        voteOutViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(VoteOutViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();

        voteOutPlayer = new JButton(VoteOutViewModel.BUTTON_LABEL);
        buttons.add(voteOutPlayer);

        JTextField playerInputField = new JTextField(15);
        LabelTextPanel playerInfo = new LabelTextPanel(new JLabel(VoteOutViewModel.PLAYER_LABEL), playerInputField);

        playerInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        VoteOutState currentState = voteOutViewModel.getState();
                        String text = playerInputField.getText() + e.getKeyChar();
                        currentState.setPlayerToVoteOut(text);
                        voteOutViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }

        );


        voteOutPlayer.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(voteOutPlayer)) {
                            VoteOutState currentState = voteOutViewModel.getState();
                            voteOutController.voteOutPlayer(currentState.getPlayerToVoteOut());
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        this.add(playerInfo);

        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof VoteOutState state) {
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            } else { //This else block is temporarily retired. If we want a pop up, add it again.
                JTextArea textArea = new JTextArea(15, 30); // Set rows and columns to control size
                textArea.setText(state.getPlayerDeathStory());
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setCaretPosition(0);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(this, scrollPane);
            }
        }
    }

}
