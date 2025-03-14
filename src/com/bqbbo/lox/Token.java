package com.bqbbo.lox;

// Intended to store data about a token, including its lexeme and location
class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    // Returns a string representation of a token
    public String toString() {
        return this.type + " " + this.lexeme + " " + this.literal;
    }
}
