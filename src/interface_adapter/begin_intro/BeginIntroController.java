package interface_adapter.begin_intro;

import use_case.begin_intro.BeginIntroInputBoundary;

public class BeginIntroController {
     final BeginIntroInputBoundary beginIntroInteractor;

    public BeginIntroController(BeginIntroInputBoundary beginIntroInteractor) {
        this.beginIntroInteractor = beginIntroInteractor;
    }
    public void execute() {
        this.beginIntroInteractor.execute();
    }
}
