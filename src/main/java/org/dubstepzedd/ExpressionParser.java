package org.dubstepzedd;

import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.NumberExpression;
import org.dubstepzedd.expressions.binary_operators.*;

import java.util.*;

public class ExpressionParser {
    private int pos;
    String[] tokens;

    private static final Map<String, OperatorInfo> OPERATORS = new HashMap<>();

    static {
        registerOperator(AdditionOperator.class);
        registerOperator(SubtractionOperator.class);
        registerOperator(MultiplicationOperator.class);
        registerOperator(DivisionOperator.class);
        registerOperator(ModuloOperator.class);
    }

    private static void registerOperator(Class<? extends BinaryExpression> clazz) {
        try {
            // This isn't optimal, but I don't know a better way of doing this...
            BinaryExpression temp = clazz.getConstructor(AbstractExpression.class, AbstractExpression.class)
                    .newInstance(null, null);
            String symbol = temp.getSymbol();
            int precedence = temp.getPrecedence();
            OPERATORS.put(symbol, new OperatorInfo(precedence, clazz));
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to register operator: " + clazz.getName(), e);
        }
    }

    public AbstractExpression parse(final String input) {
        pos = 0;
        String cleanedInput = input.replaceAll("\\s+", "");
        tokens = tokenize(cleanedInput);
        return parseExpression(0);
    }

    private AbstractExpression parseExpression(int minPrecedence) {
        AbstractExpression left = parsePrimary();

        while (pos < tokens.length && OPERATORS.containsKey(tokens[pos])) {
            OperatorInfo opInfo = OPERATORS.get(tokens[pos]);

            if (opInfo.getPrecedence() < minPrecedence) {
                break;
            }

            consume(); // consume operator
            AbstractExpression right = parseExpression(opInfo.getPrecedence() + 1);

            // Create operator using reflection
            left = opInfo.create(left, right);
        }

        return left;
    }

    private AbstractExpression parsePrimary() {
        if (tokens[pos].equals("(")) {
            consume();
            AbstractExpression inner = parseExpression(0);
            if (!tokens[pos].equals(")")) {
                throw new IllegalStateException("Expected ')'");
            }
            consume();
            return inner;
        }

        return parseNumber();
    }

    private AbstractExpression parseNumber() {
        float num = Float.parseFloat(consume());
        return new NumberExpression(num);
    }

    private String consume() {
        if(pos >= tokens.length) {
            throw new IllegalStateException("Unexpected end of expression");
        }
        return tokens[pos++];
    }

    private String[] tokenize(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (isSpecialChar(c)) {
                if (currentToken.length() > 0) {
                    result.add(currentToken.toString());
                    currentToken.setLength(0);
                }
                result.add(String.valueOf(c));
            }
            else if (Character.isDigit(c) || c == '.') {
                currentToken.append(c);
            }
            else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        if (currentToken.length() > 0) {
            result.add(currentToken.toString());
        }

        return result.toArray(String[]::new);
    }

    private boolean isSpecialChar(char c) {
        return OPERATORS.containsKey(String.valueOf(c)) || c == '(' || c == ')';
    }
}
