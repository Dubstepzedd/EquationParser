package org.dubstepzedd.tokenization;

public class Token {
    final TokenType type;
    final String value;
    public Token(String value, TokenType type) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    public String toString() { return type + " : " + value; }

}
