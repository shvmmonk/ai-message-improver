# AI Message Improver

A Java console application that uses the Groq API to improve your messages — checking grammar, suggesting better phrasing, and helping you communicate more clearly.

## What it does

Type a message, and the app sends it to an AI model (via Groq's API) which returns an improved, more polished version of your text.

## Tech Stack

- Java (built-in `HttpClient` for API calls — no external libraries)
- [Groq API](https://groq.com/) (using the `llama-3.3-70b-versatile` model)

## How to Run

1. Clone the repository
   ```bash
   git clone https://github.com/shvmmonk/ai-message-improver.git
   cd ai-message-improver
   ```

2. Get a free API key from [Groq Console](https://console.groq.com/) if you don't already have one.

3. Set your API key as an environment variable named `GROQ_API_KEY`.

   **Windows:**
   - Search for "Edit the system environment variables" → Environment Variables → New (under User variables)
   - Variable name: `GROQ_API_KEY`
   - Variable value: your actual API key

   **Mac/Linux:**
   ```bash
   export GROQ_API_KEY="your_key_here"
   ```

4. Compile and run
   ```bash
   javac src/Main.java
   java src.Main
   ```

## How it works

1. The app reads your Groq API key securely from an environment variable (never hardcoded in the source code)
2. It builds an HTTP POST request with your message, formatted as JSON
3. The request is sent to Groq's chat completions endpoint
4. The AI's response (including the improved message) is returned and printed

## Project Structure

```
src/
└── Main.java   # Builds the API request, sends it, and handles the response
```

## Current Limitations

- The message to improve is currently hardcoded — user input via `Scanner` is planned
- The raw JSON response is printed as-is; proper parsing to extract just the improved text is in progress
- No loop yet — the app runs once per execution

## Future Improvements

- Accept user input for the message to improve
- Parse the JSON response to cleanly display just the improved message
- Add separate suggestions for grammar mistakes and tone adjustments
- Add a loop so multiple messages can be checked in one run
- Eventually convert this into a web app (Spring Boot backend + simple frontend) so it can be used outside the console