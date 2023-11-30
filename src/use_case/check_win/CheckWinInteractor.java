package use_case.check_win;

import entity.Game;
import entity.PromptGenerator;
import use_case.data_access_interface.ChatAPIAccessInterface;
import use_case.data_access_interface.ConversationDataAccessInterface;
import use_case.data_access_interface.GameDataAccessInterface;

public class CheckWinInteractor implements CheckWinInputBoundary{
    private final GameDataAccessInterface gameDataAccessObject;

    private final CheckWinOutputBoundary userPresenter;

    private final ConversationDataAccessInterface conversationDataAccesObject;

    private final ChatAPIAccessInterface gptDataAccessObject;

    public CheckWinInteractor(GameDataAccessInterface gameDataAccessObject, CheckWinOutputBoundary userPresenter, ConversationDataAccessInterface conversationDataAccesObject, ChatAPIAccessInterface gptDataAccessObject){
        this.gameDataAccessObject = gameDataAccessObject;
        this.userPresenter = userPresenter;
        this.conversationDataAccesObject = conversationDataAccesObject;
        this.gptDataAccessObject = gptDataAccessObject;
    }

    public void execute(){
        Game game = gameDataAccessObject.getGame();
        PromptGenerator promptGenerator = conversationDataAccesObject.getPromptGenerator();
        if (game.checkGameOver()){
            if (game.checkVillagerWin()){
                String villagerWinPrompt = promptGenerator.generateVillagerWinPrompt(game.getAliveVillagers());
                String villagerWinStory = gptDataAccessObject.getResponse(villagerWinPrompt);
                promptGenerator.getConversationHistory().addGPTMessage(villagerWinStory);
                gameDataAccessObject.save(game);
                conversationDataAccesObject.save(promptGenerator);
                VillagerWinOutputData output = new VillagerWinOutputData(villagerWinStory);
                userPresenter.prepareVillagerWinView(output);
            } else {
                String werewolfWinPrompt = promptGenerator.generateWerewolfWinPrompt(game.getAliveWerewolves());
                String werewolfWinStory = gptDataAccessObject.getResponse(werewolfWinPrompt);
                promptGenerator.getConversationHistory().addGPTMessage(werewolfWinStory);
                gameDataAccessObject.save(game);
                conversationDataAccesObject.save(promptGenerator);
                WerewolfWinOutputData output = new WerewolfWinOutputData(werewolfWinStory);
                userPresenter.prepareWerewolfWinView(output);
            }
        } else {
            userPresenter.prepareGameContinuesView();
        }
    }
}
