package data_access;

import use_case.data_access_interface.ChatAPIAccessInterface;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class  GPT3TurboDataAccessObject implements ChatAPIAccessInterface {
    public String getResponse(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String APIKEY = "insert key here";
        String model = "gpt-3.5-turbo-1106";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + APIKEY);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            System.out.println(prompt);
            System.out.println("Ensure there are no \\n in the prompt");
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("        \"content\": ") + 20;
        int end = response.indexOf("      },", start) - 1;

        String output = response.substring(start, end);

        // Replace things that will cause issues in json parsing
        output = output.replace("\\n", " ");
        output = output.replace("\"", "");
        output = output.replace("\\", "");

        return output;

    }
}
