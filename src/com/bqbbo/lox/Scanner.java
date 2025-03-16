package com.bqbbo.lox;

import java.util.ArrayList;
import java.util.List;

class Scanner {
    private final String sourceCode;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    // Scans 'sourceCode' and returns a list of tokens
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    // Checks if all source code has been scanned
    private boolean isAtEnd() {
        return current >= sourceCode.length();
    }

    // Scans individual tokens and appends them to 'tokens'
    private void scanToken() {
        char c = advance();

        switch (c) {

            case '(':
                addToken(TokenType.LEFT_PAREN);
                break;
            case ')':
                addToken(TokenType.RIGHT_PAREN);
                break;
            case '{':
                addToken(TokenType.LEFT_BRACE);
                break;
            case '}':
                addToken(TokenType.RIGHT_BRACE);
                break;
            case ',':
                addToken(TokenType.COMMA);
                break;
            case '.':
                addToken(TokenType.DOT);
                break;
            case '-':
                addToken(TokenType.MINUS);
                break;
            case '+':
                addToken(TokenType.PLUS);
                break;
            case ';':
                addToken(TokenType.SEMICOLON);
                break;
            case '%':
                addToken(TokenType.PERCENT);
                break;

            case '!':
                addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
                break;
            case '*':
                addToken(match('*') ? TokenType.STAR_STAR : TokenType.STAR);
                break;
            case '=':
                addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                break;

            case '/':
                if (match('/')) {
                    // Single Line Comment
                    while (peek() != '\n' && !isAtEnd()) {
                        advance();
                    }
                } else if (match('*')) {
                    // Multi-Line Comment
                    while (!isAtEnd()) {
                        if (peek() == '*' && peek(1) == '/') {
                            advance(2);
                            break;
                        }

                        if (peek() == '\n') {
                            line++;
                        }

                        advance();
                    }

                } else {
                    addToken(TokenType.SLASH);
                }
                break;

            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;

            case '"':
                string();
                break;

            default:
                if (isDigit(c)) {
                    number();
                } else {
                    Lox.error(line, "Unexpected character \"" + c + "\"");
                }

                break;
        }
    }

    // Handles string lexemes
    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                line++;
            }

            advance();
        }

        if (isAtEnd()) {
            Lox.error(line, "Unterminated string");
            return;
        }

        advance(); // Consume the ending " character

        String stringLiteral = sourceCode.substring(start + 1, current - 1);
        addToken(TokenType.STRING, stringLiteral);
    }

    // Handles int/float lexemes
    private void number() {
        while (isDigit(peek())) {
            advance();
        }

        if (peek() == '.' && isDigit(peek(1))) {
            advance(); // Consume "."

            while (isDigit(peek())) {
                advance();
            }
        }

        Double numberLiteral = Double.parseDouble(sourceCode.substring(start, current));
        addToken(TokenType.NUMBER, numberLiteral);
    }

    // Return 'true' if the character after 'c' is the expected character
    private boolean match(char expectedChar) {
        if (isAtEnd()) {
            return false;
        }
        if (sourceCode.charAt(current) != expectedChar) {
            return false;
        }

        current++;
        return true;
    }

    // Returns the character after 'c': Default method for character at 'current'
    private char peek() {
        return peek(0);
    }

    // Allows lookahead to be greater than 'current'
    private char peek(int lookahead) {
        if (current + lookahead >= sourceCode.length()) {
            return '\0';
        }

        return sourceCode.charAt(current + lookahead);
    }

    // Returns the current value of 'current', then increments
    private char advance() {
        return advance(1);
    }

    // Allow for multiple advances at once
    private char advance(int advanceInt) {
        char currentChar = sourceCode.charAt(current);
        current += advanceInt;

        return currentChar;
    }

    // Returns if char 'c' is a decimal digit
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    // Returns if char 'c' is acceptable for identifiers
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_');
    }

    // 'token' appending helper methods
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = sourceCode.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
