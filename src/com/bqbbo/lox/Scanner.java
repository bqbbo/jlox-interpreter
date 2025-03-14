package com.bqbbo.lox;

import static com.bqbbo.lox.TokenType.EOF;

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

    List<Token> scanTokens() {
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }
}
