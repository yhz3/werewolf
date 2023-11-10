package data_access;

import entity.Game;
import entity.PromptGenerator;
import use_case.data_access_interface.GameDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

// TODO: this class must implement all data access interfaces we create going forward
public class GameDataAccessObject implements GameDataAccessInterface {

    // This is the csv file
    private final File csvFile;
    // This map is for the headers of our csv file // TODO: decide how to organize this
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    // Game entity which we'll build from csv file
    private Game game;

    public GameDataAccessObject(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);
        // TODO: We create the headers around here

        if (csvFile.length() == 0) {
            save(game);
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    // TODO: Decide how we'll read in the game
                }
            }
        }
    }

    @Override
    public void save(Game game) {

    }

    public Game getGame() {
        return game;
    }


}
