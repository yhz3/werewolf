package app;

import data_access.*;
import entity.ConversationHistory;
import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.begin_intro.BeginIntroController;
import interface_adapter.begin_intro.BeginIntroPresenter;
import interface_adapter.begin_intro.BeginIntroViewModel;
import interface_adapter.check_win.CheckWinController;
import interface_adapter.check_win.CheckWinPresenter;
import interface_adapter.check_win.VillagerWinViewModel;
import interface_adapter.check_win.WerewolfWinViewModel;
import interface_adapter.kill_villager.KillVillagerController;
import interface_adapter.kill_villager.KillVillagerPresenter;
import interface_adapter.kill_villager.KillVillagerViewModel;
import interface_adapter.new_game.NewGameController;
import interface_adapter.new_game.NewGamePresenter;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.restart_game.RestartGamePresenter;
import interface_adapter.vote_out.VoteOutController;
import interface_adapter.vote_out.VoteOutPresenter;
import interface_adapter.vote_out.VoteOutViewModel;
import use_case.begin_intro.BeginIntroInteractor;
import use_case.check_win.CheckWinInteractor;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.kill_villager.KillVillagerInteractor;
import use_case.new_game.NewGameInteractor;
import use_case.restart_game.RestartGameInteractor;
import use_case.vote_out.VoteOutInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

/*
General Process for Implementing UI

1. Add the View. Follow examples like NewGameView, BeginIntroView. It needs to take in the ViewModel and Controller.
    Make sure to:
    - Add a PropertyChangerListener to the ViewModel, edit the propertyChange method to do something
    - Add titles, input fields (with KeyListeners, labels, buttons (with ActionListeners), set the layout
2. Construct the View in the main program. You will essentially link everything together.
    I have organized it in the way that you just need to:
    a) Add the View Model under "// View Models"
    b) Create and add the View under "// Add the ""
        - Be sure to use a helper method i.e. get<Name>View to organize code. See examples at the end.
3. That's basically it, but there's going to be probably many issues. Some common things you might need to do:
    - Add stuff to the View Models / States that are missing (titles, button labels etc.)
    - Add the NEXT ViewModel to the current Presenter's prepareSuccessview if you want it to automatically switch.
        For example, see NewGamePresenter. This means the current Presenter depends on the next ViewModel.
 */


public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Werewolf Game Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();


        // Objects That will be used commonly
        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject(new Game());
        ChatAPIAccessInterface compressionAPI = new GPT3TurboDataAccessObject();
        ChatAPIAccessInterface chatAPIAccessInterface = new GPT4DataAccessObject();
        ConversationDataAccessObject conversationDataAccessObject = new ConversationDataAccessObject(new ConversationHistory(), compressionAPI);


        // View Models
        NewGameViewModel newGameViewModel = new NewGameViewModel(8);
        BeginIntroViewModel beginIntroViewModel = new BeginIntroViewModel();
        KillVillagerViewModel killVillagerViewModel = new KillVillagerViewModel();
        VoteOutViewModel voteOutViewModel = new VoteOutViewModel();
        VillagerWinViewModel villagerWinViewModel = new VillagerWinViewModel();
        WerewolfWinViewModel werewolfWinViewModel = new WerewolfWinViewModel();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Add the NewGameView
        NewGameView newGameView = getNewGameView(viewManagerModel, newGameViewModel, beginIntroViewModel, gameDataAccessObject);
        views.add(newGameView, newGameView.viewName);

        // Add the BeginIntroView
        BeginIntroView beginIntroView = getIntroView(viewManagerModel, beginIntroViewModel, killVillagerViewModel, gameDataAccessObject, conversationDataAccessObject, chatAPIAccessInterface);
        views.add(beginIntroView, beginIntroView.viewName);

        // Add the VillagerWinView
        VillagerWinView villagerWinView = getVillagerWinView(viewManagerModel, villagerWinViewModel, werewolfWinViewModel, gameDataAccessObject, conversationDataAccessObject, chatAPIAccessInterface, voteOutViewModel, killVillagerViewModel, newGameViewModel);
        views.add(villagerWinView, villagerWinView.viewName);

        // Add the WerewolfWinView
        WerewolfWinView werewolfWinView = getWerewolfWinView(viewManagerModel, villagerWinViewModel, werewolfWinViewModel, gameDataAccessObject, conversationDataAccessObject, chatAPIAccessInterface, voteOutViewModel, killVillagerViewModel, newGameViewModel);
        views.add(werewolfWinView, werewolfWinView.viewName);

        // Add the KillPlayerView
        KillVillagerView killVillagerView = getKillVillagerView(viewManagerModel, killVillagerViewModel, voteOutViewModel, gameDataAccessObject, conversationDataAccessObject, chatAPIAccessInterface, villagerWinViewModel, werewolfWinViewModel, newGameViewModel);
        views.add(killVillagerView, killVillagerView.viewName);

        // Add the VoteOutView
        VoteOutView voteOutView = getVoteOutView(viewManagerModel, voteOutViewModel, killVillagerViewModel, gameDataAccessObject, conversationDataAccessObject, chatAPIAccessInterface, villagerWinViewModel, werewolfWinViewModel, newGameViewModel);
        views.add(voteOutView, voteOutView.viewName);

        viewManagerModel.setActiveView(newGameView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }


    // Dirty Code that Stitches together use NewGameView with BeginIntroViewModel
    private static NewGameView getNewGameView(ViewManagerModel viewManagerModel, NewGameViewModel newGameViewModel, BeginIntroViewModel beginIntroViewModel, GameDataAccessObject gameDataAccessObject) {

        NewGamePresenter newGamePresenter = new NewGamePresenter(newGameViewModel, beginIntroViewModel, viewManagerModel);
        NewGameInteractor newGameInteractor = new NewGameInteractor(gameDataAccessObject, newGamePresenter);
        NewGameController newGameController = new NewGameController(newGameInteractor);

        // Add the view to the view JPanel
        return new NewGameView(newGameViewModel, newGameController);
    }

    // Dirty Code that Stitches together use BeginIntroView with KillVillagerViewModel
    private static BeginIntroView getIntroView(ViewManagerModel viewManagerModel, BeginIntroViewModel beginIntroViewModel, KillVillagerViewModel killVillagerViewModel, GameDataAccessObject gameDataAccessObject, ConversationDataAccessObject conversationDataAccessObject, ChatAPIAccessInterface chatAPIAccessInterface) {
        BeginIntroPresenter beginIntroPresenter = new BeginIntroPresenter(beginIntroViewModel, viewManagerModel, killVillagerViewModel);
        BeginIntroInteractor beginIntroInteractor = new BeginIntroInteractor(conversationDataAccessObject, gameDataAccessObject, chatAPIAccessInterface, beginIntroPresenter);
        BeginIntroController beginIntroController = new BeginIntroController(beginIntroInteractor);

        return new BeginIntroView(beginIntroViewModel, beginIntroController);
    }

    private static VillagerWinView getVillagerWinView(ViewManagerModel viewManagerModel, VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, GameDataAccessObject gameDataAccessObject, ConversationDataAccessObject conversationDataAccessObject, ChatAPIAccessInterface chatAPIAccessInterface, VoteOutViewModel voteOutViewModel, KillVillagerViewModel killVillagerViewModel, NewGameViewModel newGameViewModel){
        CheckWinPresenter checkWinPresenter = new CheckWinPresenter(villagerWinViewModel, werewolfWinViewModel, viewManagerModel, voteOutViewModel, killVillagerViewModel);
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter, conversationDataAccessObject, chatAPIAccessInterface);
        RestartGamePresenter restartGamePresenter = new RestartGamePresenter(newGameViewModel, viewManagerModel);
        RestartGameInteractor restartGameInteractor = new RestartGameInteractor(restartGamePresenter);
        CheckWinController checkWinController = new CheckWinController(checkWinInteractor, restartGameInteractor);

        return new VillagerWinView(villagerWinViewModel, checkWinController);
    }

    private static WerewolfWinView getWerewolfWinView(ViewManagerModel viewManagerModel, VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, GameDataAccessObject gameDataAccessObject, ConversationDataAccessObject conversationDataAccessObject, ChatAPIAccessInterface chatAPIAccessInterface, VoteOutViewModel voteOutViewModel, KillVillagerViewModel killVillagerViewModel, NewGameViewModel newGameViewModel){
        CheckWinPresenter checkWinPresenter = new CheckWinPresenter(villagerWinViewModel, werewolfWinViewModel, viewManagerModel, voteOutViewModel, killVillagerViewModel);
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter, conversationDataAccessObject, chatAPIAccessInterface);
        RestartGamePresenter restartGamePresenter = new RestartGamePresenter(newGameViewModel, viewManagerModel);
        RestartGameInteractor restartGameInteractor = new RestartGameInteractor(restartGamePresenter);
        CheckWinController checkWinController = new CheckWinController(checkWinInteractor, restartGameInteractor);

        return new WerewolfWinView(werewolfWinViewModel, checkWinController);
    }


    // Dirty Code that Stitches together use KillVillagerView with VoteOutPlayerViewModel
    private static KillVillagerView getKillVillagerView(ViewManagerModel viewManagerModel, KillVillagerViewModel killVillagerViewModel, VoteOutViewModel voteOutViewModel, GameDataAccessObject gameDataAccessObject, ConversationDataAccessObject conversationDataAccessObject, ChatAPIAccessInterface chatAPIAccessInterface, VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, NewGameViewModel newGameViewModel) {
        KillVillagerPresenter killVillagerPresenter = new KillVillagerPresenter(killVillagerViewModel, voteOutViewModel, viewManagerModel);
        CheckWinPresenter checkWinPresenter = new CheckWinPresenter(villagerWinViewModel, werewolfWinViewModel, viewManagerModel, voteOutViewModel, killVillagerViewModel);
        KillVillagerInteractor killVillagerInteractor = new KillVillagerInteractor(conversationDataAccessObject, gameDataAccessObject, killVillagerPresenter, chatAPIAccessInterface);
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter, conversationDataAccessObject, chatAPIAccessInterface);
        RestartGamePresenter restartGamePresenter = new RestartGamePresenter(newGameViewModel, viewManagerModel);
        RestartGameInteractor restartGameInteractor = new RestartGameInteractor(restartGamePresenter);
        KillVillagerController killVillagerController = new KillVillagerController(checkWinInteractor, killVillagerInteractor, restartGameInteractor);

        return new KillVillagerView(killVillagerViewModel, killVillagerController);

    }

    private static VoteOutView getVoteOutView(ViewManagerModel viewManagerModel, VoteOutViewModel voteOutViewModel, KillVillagerViewModel killVillagerViewModel, GameDataAccessObject gameDataAccessObject, ConversationDataAccessObject conversationDataAccessObject, ChatAPIAccessInterface chatAPIAccessInterface, VillagerWinViewModel villagerWinViewModel, WerewolfWinViewModel werewolfWinViewModel, NewGameViewModel newGameViewModel){
        VoteOutPresenter voteOutPresenter = new VoteOutPresenter(voteOutViewModel, killVillagerViewModel, viewManagerModel);
        CheckWinPresenter checkWinPresenter = new CheckWinPresenter(villagerWinViewModel, werewolfWinViewModel, viewManagerModel, voteOutViewModel, killVillagerViewModel);
        VoteOutInteractor voteOutInteractor = new VoteOutInteractor(conversationDataAccessObject, gameDataAccessObject, chatAPIAccessInterface, voteOutPresenter);
        CheckWinInteractor checkWinInteractor = new CheckWinInteractor(gameDataAccessObject, checkWinPresenter, conversationDataAccessObject, chatAPIAccessInterface);
        RestartGamePresenter restartGamePresenter = new RestartGamePresenter(newGameViewModel, viewManagerModel);
        RestartGameInteractor restartGameInteractor = new RestartGameInteractor(restartGamePresenter);
        VoteOutController voteOutController = new VoteOutController(checkWinInteractor, voteOutInteractor, restartGameInteractor);

        return new VoteOutView(voteOutViewModel, voteOutController);
    }


}

