package org.dubstepzedd.tokenization;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Tokenizer {

    // Can't use TokenType here as it has to be static.
    private final Pattern pattern = Pattern.compile(
    "(?<NUMBER>\\d+(?:\\.\\d+)?)"
        + "|(?<IDENTIFIER>[A-Za-z_][A-Za-z_0-9]*)"
        + "|(?<OPERATOR>[+\\-*/^])"
        + "|(?<LEFTPAREN>\\()"
        + "|(?<RIGHTPAREN>\\))"
    );
    private final Token[] tokens;
    private int pos;

    public Tokenizer(final String str) {
        this.tokens = tokenize(str);
        this.pos = 0;
    }
    public String consume() {
        if(pos >= tokens.length) {
            throw new IllegalStateException("Unexpected end of expression");
        }
        return tokens[pos++].value;
    }

    public boolean peekEqual(final String str) {
        return tokens[pos].value.equals(str);
    }

    public String peek() {
        return tokens[pos].value;
    }

    public boolean hasTokens() {
        return pos < tokens.length;
    }

    private Token[] tokenize(String input) {
        input = input.replaceAll("\\s+", "");
        Matcher matcher = pattern.matcher(input);
        List<Token> tokens = new ArrayList<>();

        while (matcher.find()) {
            for (TokenType t : TokenType.values()) {
                if (matcher.group(t.name()) != null) {
                    tokens.add(new Token(t.name(), matcher.group()));
                    break; // stop checking once we found the match
                }
            }
        }

        return tokens.toArray(Token[]::new);
    }

    @Override
    public String toString() {
        return Arrays.stream(tokens)
                .map(Token::toString)
                .collect(Collectors.joining("\n"));
    }

}
