package com.example.JavaToEnglish;

import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

@Service
public class CallLlmService {

    // Reads the API key from an environment variable


    // Extracts the message content from the JSON response
    public String extractMessageFromJSONResponse(String response) {
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray choices = jsonResponse.getJSONArray("choices");
        if (choices.length() > 0) {
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
            return message.getString("content").trim();
        }
        return "No response from model.";
    }

    // Calls the OpenAI API to get a summary of the Java file content
    public String callLlm(String fileContent,String gptVersion) {
       String API_KEY = "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";
        System.out.println(API_KEY);
        String url = "https://api.openai.com/v1/chat/completions";
        String model = gptVersion;
        String prompt = "Can you summarize this code";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            JSONObject body = new JSONObject();
            body.put("model", model);
            JSONArray messages = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", prompt + " : " + fileContent);
            messages.put(message);
            body.put("messages", messages);

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body.toString());
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

            // Calls the method to extract the message
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
