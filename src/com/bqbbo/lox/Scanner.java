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
            case '*':
                addToken(TokenType.STAR);
                break;
            case '%':
                addToken(TokenType.PERCENT);
                break;

            default:
                Lox.error(line, "Unexpected character \"" + c + "\"");
                break;
        }
    }

    // Returns the current value of 'current', then increments
    private char advance() {
        return sourceCode.charAt(current++);
    }

    // Token appending helper methods
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = sourceCode.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
