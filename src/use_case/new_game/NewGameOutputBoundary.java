package use_case.new_game;

public interface NewGameOutputBoundary {

    public void prepareFailView();
    public void prepareSuccessView(NewGameOutputData newGameOutputData);
}
