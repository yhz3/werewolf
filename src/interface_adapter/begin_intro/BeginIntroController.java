package interface_adapter.begin_intro;

import use_case.begin_intro.BeginIntroInputBoundary;
import use_case.begin_intro.BeginIntroInputData;

public class BeginIntroController {
     final BeginIntroInputBoundary beginIntroInteractor;

    public BeginIntroController(BeginIntroInputBoundary beginIntroInteractor) {
        this.beginIntroInteractor = beginIntroInteractor;
    }
    public void execute(BeginIntroInputData beginIntroInputData) {
        this.beginIntroInteractor.execute(beginIntroInputData);
    }
}
