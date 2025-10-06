package org.dubstepzedd.tokenization;

public class Token {
    final String type;
    final String value;
    Token(String type, String value) {
        this.type = type;
        this.value = value;
    }
    public String toString() { return type + " : " + value; }

}
