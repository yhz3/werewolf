package data_access;

import entity.PromptGenerator;
import use_case.data_access_interface.ConversationDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConversationDataAccessObject implements ConversationDataAccessInterface {

    // This is the csv file
    private final File csvFile;
    // This map is for the headers of our csv file // TODO: decide how to organize this
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    // PromptGenerator entity which we'll build from csv flie
    private PromptGenerator promptGenerator;

    public ConversationDataAccessObject(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);
        // TODO: We create the headers around here

        if (csvFile.length() == 0) {
            save(promptGenerator);
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

    public PromptGenerator getPromptGenerator() { return promptGenerator; }

    @Override
    public void save(PromptGenerator promptGenerator) {

    }
}
