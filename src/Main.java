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
        System.out.print("Enter your message that you want to improve: ");
        String userMessage = scanner.nextLine();
        String prompt = "You are a writing assistant. Improve the grammar and clarity of this message, and only return the improved version, nothing else: " + userMessage;

        String jsonBody = "{\"model\": \"llama-3.3-70b-versatile\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
        


        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
      //  System.out.println(response.body()); // abhi hume json parsing krni padegi
          String responseBody = response.body();
          int startIndex = responseBody.indexOf("\"content\":\"") + "\"content\":\"".length();
          int endIndex = responseBody.indexOf("\"" , startIndex);


          String improvedMessage = responseBody.substring(startIndex, endIndex);
    
          System.out.println(improvedMessage);
    
    }
}