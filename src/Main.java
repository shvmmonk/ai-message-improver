package src;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("GROQ_API_KEY");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Enter your message that you want to improve: ");
            String userMessage = scanner.nextLine();

            if (userMessage.equalsIgnoreCase("exit")) {
                System.out.println("Good Bye ");
                return;
            }

            String prompt = "You are a writing assistant. Analyze this message and respond in EXACTLY this format:\\n" +
                    "IMPROVED: [improved version of the message]\\n" +
                    "GRAMMAR: [provide a list grammar mistakes and correct them]\\n" +
                    "TONE: [describe the tone and suggest improvements]\\n\\n" +
                    "Message: " + userMessage;

            String jsonBody = "{\"model\": \"llama-3.3-70b-versatile\", \"messages\": [{\"role\": \"user\", \"content\": \""
                    + prompt + "\"}]}";


            
            try {
                HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // System.out.println(response.body()); // abhi hume json parsing krni padegi
            String responseBody = response.body();
            int startIndex = responseBody.indexOf("\"content\":\"") + "\"content\":\"".length();
            int endIndex = responseBody.indexOf("\"},\"logprobs\"");
            String fullContent = responseBody.substring(startIndex, endIndex);
            System.out.println(fullContent
                    .replace("\\n", "\n")
                    .replace("\\\"", "\""));
            } catch (Exception e) {
                System.out.println("Something went wrong" + e.getMessage());
                System.out.println("Please check your internet connection and try again :(");
            }
        }
    

    }
}