package com.bqbbo.lox;

enum TokenType {
    // Single-char tokens
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR, PERCENT,

    // 1-2 char tokens
    BANG, BANG_EQUAL, EQUAL, EQUAL_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,

    // Literals/Types
    IDENTIFIER, STRING, NUMBER,

    // Keyword Types
    TRUE, FALSE, NIL,

    // Keyword Bool Operations
    AND, NAND, OR, XOR, XNOR,

    // Keyword Functions/Classes
    FUN, CLASS, SUPER, THIS,

    // Keyword Control Flow
    IF, FOR, WHILE,

    // Keyword Misc

    VAR, RETURN, PRINT, EOF
}
