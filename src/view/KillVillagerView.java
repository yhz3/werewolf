package view;

import interface_adapter.kill_villager.KillVillagerController;
import interface_adapter.kill_villager.KillVillagerState;
import interface_adapter.kill_villager.KillVillagerViewModel;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class KillVillagerView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "kill villager";
    private final KillVillagerViewModel killVillagerViewModel;
    private final KillVillagerController killVillagerController;
    private final JButton killVillager;

    public KillVillagerView(KillVillagerViewModel killVillagerViewModel, KillVillagerController killVillagerController) {
        this.killVillagerViewModel = killVillagerViewModel;
        this.killVillagerController = killVillagerController;
        killVillagerViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(KillVillagerViewModel.TITLE_LABEL);

        JPanel buttons = new JPanel();
        killVillager = new JButton(KillVillagerViewModel.BUTTON_LABEL);
        buttons.add(killVillager);

        JTextField villagerInputField = new JTextField(15);
        LabelTextPanel villagerInfo = new LabelTextPanel(new JLabel(KillVillagerViewModel.VILLAGER_LABEL), villagerInputField);

        villagerInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        KillVillagerState currentState = killVillagerViewModel.getState();
                        String text = villagerInputField.getText() + e.getKeyChar();
                        currentState.setVillager(text);
                        killVillagerViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        killVillager.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(killVillager)) {
                            KillVillagerState currentState = killVillagerViewModel.getState();

                            killVillagerController.killVillager(currentState.getVillager());
                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        this.add(villagerInfo);

        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof KillVillagerState state) {
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            } else {
                // GPT output uses \\n to mean new line.
                String story = state.getPlayerDeathStory().replace("\\n", "\n");
                JTextArea textArea = new JTextArea(15, 30);
                textArea.setText(story);
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
