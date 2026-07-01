# AI Message Improver

A Java console application that uses the Groq API to analyze and improve your messages. Type any message and get instant AI-powered feedback — an improved version, grammar corrections, and tone analysis.

## What it does

Type a message (like a WhatsApp text, email, or any informal message), and the app gives you:

- **✨ Improved version** — a polished, grammar-correct rewrite
- **📝 Grammar analysis** — specific mistakes explained clearly
- **🎯 Tone analysis** — whether your message sounds formal/informal, and how to improve it

### Example

**Input:**
```
hey i want to make an order
```

**Output:**
```
IMPROVED: Hello, I would like to place an order.

GRAMMAR:
* "hey" should be replaced with a more formal greeting like "Hello"
* "i" should be capitalized to "I"
* "make an order" can be rephrased to "place an order" for better clarity

TONE: The tone is informal and abrupt. Consider using more polite language
like "I would like to place an order" to convey respect and courtesy.
```

## Tech Stack

- Java 17 (built-in `HttpClient` for API calls — no external libraries needed)
- [Groq API](https://groq.com/) — `llama-3.3-70b-versatile` model

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/shvmmonk/ai-message-improver.git
cd ai-message-improver
```

### 2. Get a Groq API key
Sign up at [console.groq.com](https://console.groq.com/) and create a free API key.

### 3. Set your API key as an environment variable

**Windows:**
- Search "Edit the system environment variables" → Environment Variables → New (under User variables)
- Variable name: `GROQ_API_KEY`
- Variable value: your actual API key
- Restart VS Code/terminal after setting

**Mac/Linux:**
```bash
export GROQ_API_KEY="your_key_here"
```

### 4. Compile and run
```bash
javac src/Main.java
java src.Main
```

## Usage

Once running, type your message and press Enter. Type `exit` to quit.

```
Enter your message that you want to improve: helo how r u
IMPROVED: Hello, how are you?
GRAMMAR:
* "helo" is a misspelling of "Hello"
* "r" and "u" are informal abbreviations, use "are" and "you" instead
TONE: The tone is very casual and informal...

Enter your message that you want to improve: exit
Good Bye
```

## How it works

1. Reads your Groq API key securely from an environment variable (never hardcoded)
2. Takes your message as input via `Scanner`
3. Builds a structured prompt instructing the AI to respond in a specific format (IMPROVED / GRAMMAR / TONE)
4. Sends an HTTP POST request to Groq's API using Java's built-in `HttpClient`
5. Parses the JSON response using `String.indexOf()` and `substring()` to extract just the AI's content
6. Cleans up escaped characters (`\n`, `\"`) for readable console output
7. Loops back for the next message

## Project Structure

```
src/
└── Main.java   # All logic: user input, API call, JSON parsing, output formatting
```

## Key Concepts Used

- **Java HttpClient** — sending HTTP POST requests without external libraries
- **Prompt Engineering** — structuring AI instructions to get consistent, parseable output
- **JSON parsing** — manually extracting fields using `indexOf()` and `substring()`
- **Environment variables** — securely storing API keys outside source code
- **String manipulation** — `.replace()`, `.indexOf()`, `.substring()`

## Known Limitations

- JSON parsing is done manually (string-based) — could break if the AI response contains unusual formatting
- No error handling yet for network failures or invalid API keys
- Single-threaded — waits for API response before accepting next input

## Future Improvements

- Add proper error handling (`try-catch` for network/API failures)
- Use a JSON library (Gson/Jackson via Maven) for more robust parsing
- Add better console formatting with section separators
- Build a Spring Boot web version with a simple HTML/CSS frontend