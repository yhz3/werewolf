package app;

import data_access.GameDataAccessObject;
import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.begin_intro.BeginIntroViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import use_case.new_game.NewGameInteractor;
import view.NewGameView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Werewolf Game Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Add the NewGameView
        NewGameView newGameView = getNewGameView(viewManagerModel);
        views.add(newGameView, newGameView.viewName);

        viewManagerModel.setActiveView(newGameView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

    // Dirty Code that Stitches together use NewGameUseCase with the ViewModel with 8 players
    private static NewGameView getNewGameView(ViewManagerModel viewManagerModel) {
        NewGameViewModel newGameViewModel = new NewGameViewModel(8);
        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject(new Game());
        BeginIntroViewModel beginIntroViewModel = new BeginIntroViewModel();
        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, beginIntroViewModel, viewManagerModel);
        NewGameInteractor newGameInteractor = new NewGameInteractor(gameDataAccessObject, newGamePresenter);
        NewGameController newGameController = new NewGameController(newGameInteractor);

        // Add the view to the view JPanel
        return new NewGameView(newGameViewModel, newGameController);
    }

}
