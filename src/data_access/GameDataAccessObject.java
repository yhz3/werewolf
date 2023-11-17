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

    // We originally wrote to a text file for object persistence, but this seems to work a lot better since we would
    // not need to keep reading and writing to a text file, which would be quite tedious.
    // By design, a game should be played in once sitting, i.e. the software is booted up and the game is completed
    // before the software is closed, so there is no need for data to be in a format such that it persists even after
    // the software is closed.
    private Game game;
    // Not making this attribute final so that the save() works.

    public GameDataAccessObject(Game game) {
        this.game = game;
    }

    @Override
    public void save(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }


}
