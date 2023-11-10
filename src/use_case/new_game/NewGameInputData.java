package use_case.new_game;
import java.util.ArrayList;

public class NewGameInputData {
    final private ArrayList<String> userNames;

    public NewGameInputData(ArrayList<String> userNames){
        this.userNames = userNames;
    }

    public ArrayList<String> getUserNames(){
        return userNames;
    }
}
