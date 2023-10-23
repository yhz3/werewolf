package view;

import interface_adapter.new_game.NewGameViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NewGameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "new game";
    private final NewGameViewModel newGameViewModel;
    // private final JButton newGame;

    public NewGameView(NewGameViewModel newGameViewModel) {
        this.newGameViewModel = newGameViewModel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
