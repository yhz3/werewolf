package view;

import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import use_case.begin_intro.BeginIntroInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

public class NewGameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "new game";
    private final NewGameViewModel newGameViewModel;
    private final JTextField[] playerInputFields;

    private final JButton newGame;

    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController) {
        playerInputFields = new JTextField[NewGameViewModel.PLAYER_LABELS.length];

        for (int i = 0; i < playerInputFields.length; i++) {
            playerInputFields[i] = new JTextField(15);
        }

        this.newGameViewModel = newGameViewModel;
        newGameViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(NewGameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel[] playerInfos = new LabelTextPanel[playerInputFields.length];

        for (int i = 0; i < playerInputFields.length; i++) {
            playerInfos[i] = new LabelTextPanel(
                    new JLabel(NewGameViewModel.PLAYER_LABELS[i]), playerInputFields[i]);
        }


        JPanel buttons = new JPanel();
        newGame = new JButton(NewGameViewModel.NEW_GAME_LABEL);
        buttons.add(newGame);

        newGame.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(newGame)) {
                            NewGameState currentState = newGameViewModel.getState();

                            ArrayList<String> players = new ArrayList<String>();
                            for (int i = 0; i < playerInputFields.length; i++) {
                                String player = currentState.getPlayer(i);
                                if (player != null) {
                                    players.add(player);
                                }
                            }
                            newGameController.execute(players);
                        }
                    }
                }
        );

        for (int i = 0; i < playerInputFields.length; i++) {
            addKeyListenerToPlayerField(playerInputFields[i], i);
        }


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        for (int i = 0; i < playerInputFields.length; i++) {
            this.add(playerInfos[i]);
        }

        this.add(buttons);
    }

    private void addKeyListenerToPlayerField(JTextField textField, int playerNumber) {
        textField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        NewGameState currentState = newGameViewModel.getState();
                        String text = textField.getText() + e.getKeyChar();
                        currentState.setPlayer(playerNumber, text);
                        newGameViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof NewGameState state) {
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, state.getError());
            } else {
                String message = "Villagers: " + Arrays.toString(state.getVillagers()) +
                        "\nWerewolves: " + Arrays.toString(state.getWerewolves());
                JOptionPane.showMessageDialog(this, message);
            }
        }
    }
}
