package view;

import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;

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
    private final NewGameController newGameController;

    private final JButton newGame;

    public NewGameView(NewGameViewModel newGameViewModel, NewGameController newGameController) {
        this.newGameController = newGameController;
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
                                // Not allowing blank names needs to be sorted out here, not the interactor
                                // Otherwise we have to loop through a list to find blank names, very redundant
                                /* .isBlank() checks  checks if a string contains no characters,
                                    is only whitespace, and is null.*/
                                // First I need to check that the player is not null, or I can't strip it
                                if (player != null) {
                                    // Strip the string (removes trailing/leading whitespaces)
                                    player = player.strip();
                                    // Remove backspace characters (important!)
                                    player = player.replaceAll("\b", "");
                                    // Now I make sure the player isn't a blank name
                                    // Whether it's a duplicate will be checked in the interactor
                                    if (!player.isBlank()) {
                                        players.add(player);
                                    }
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
                // We need to set the error to null or the popup will keep showing, this caused bugs in the past
                state.setError(null);
            } else {
                String message = "Villagers: " + Arrays.toString(state.getVillagers()) +
                        "\nWerewolves: " + Arrays.toString(state.getWerewolves());
                JOptionPane.showMessageDialog(this, message);
            }
        }
    }
}
