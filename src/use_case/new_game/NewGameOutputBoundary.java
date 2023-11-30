package use_case.new_game;

public interface NewGameOutputBoundary {

    public void prepareFailView(String error);
    public void prepareSuccessView(NewGameOutputData newGameOutputData);
}
