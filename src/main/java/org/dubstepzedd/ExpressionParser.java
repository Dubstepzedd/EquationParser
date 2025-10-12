package org.dubstepzedd;

import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.IdentifierExpression;
import org.dubstepzedd.expressions.NumberExpression;
import org.dubstepzedd.expressions.binary_operators.*;
import org.dubstepzedd.expressions.binary_operators.operators.*;
import org.dubstepzedd.expressions.unary_operators.operators.CosinesOperator;
import org.dubstepzedd.expressions.unary_operators.operators.SinusOperator;
import org.dubstepzedd.expressions.unary_operators.UnaryExpression;
import org.dubstepzedd.expressions.unary_operators.UnaryOperatorInfo;
import org.dubstepzedd.tokenization.Token;
import org.dubstepzedd.tokenization.TokenType;
import org.dubstepzedd.tokenization.Tokenizer;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {

    private Tokenizer tokenizer;
    private static final Map<String, UnaryOperatorInfo> UNARY_OPERATORS = new HashMap<>();
    private static final Map<String, BinaryOperatorInfo> BINARY_OPERATORS = new HashMap<>();

    static {
        registerUnaryOperator(SinusOperator.class);
        registerUnaryOperator(CosinesOperator.class);
    }

    static {
        registerBinaryOperator(AdditionOperator.class);
        registerBinaryOperator(SubtractionOperator.class);
        registerBinaryOperator(MultiplicationOperator.class);
        registerBinaryOperator(DivisionOperator.class);
        registerBinaryOperator(ModuloOperator.class);
    }

    private static void registerUnaryOperator(Class<? extends UnaryExpression> clazz) {
        try {
            // This isn't optimal, but I don't know a better way of doing this...
            UnaryExpression temp = clazz.getConstructor(AbstractExpression.class)
                    .newInstance((AbstractExpression)null);
            String symbol = temp.getSymbol();
            UNARY_OPERATORS.put(symbol, new UnaryOperatorInfo(clazz));
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to register operator: " + clazz.getName(), e);
        }
    }

    private static void registerBinaryOperator(Class<? extends BinaryExpression> clazz) {
        try {
            // This isn't optimal, but I don't know a better way of doing this...
            BinaryExpression temp = clazz.getConstructor(AbstractExpression.class, AbstractExpression.class)
                    .newInstance(null, null);
            String symbol = temp.getSymbol();
            int precedence = temp.getPrecedence();
            BINARY_OPERATORS.put(symbol, new BinaryOperatorInfo(precedence, clazz));
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to register operator: " + clazz.getName(), e);
        }
    }

    public AbstractExpression parse(final String input) {
        this.tokenizer = new Tokenizer(input);
        return parseExpression(0);
    }

    private AbstractExpression parseExpression(int minPrecedence) {
        AbstractExpression left = parsePrimary();

        while (tokenizer.hasTokens() && tokenizer.peek().getType() == TokenType.OPERATOR) {
            BinaryOperatorInfo opInfo = BINARY_OPERATORS.get(tokenizer.peek().getValue());

            if (opInfo.getPrecedence() < minPrecedence) {
                break;
            }

            this.tokenizer.consume(); // consume operator
            AbstractExpression right = parseExpression(opInfo.getPrecedence() + 1);

            // Create operator using reflection
            left = opInfo.create(left, right);
        }

        return left;
    }

    private AbstractExpression parsePrimary() {
        if (tokenizer.peekEqual("(")) {
            this.tokenizer.consume();
            AbstractExpression inner = parseExpression(0);
            if (!tokenizer.peekEqual(")")) {
                throw new IllegalStateException("Expected ')'");
            }
            this.tokenizer.consume();
            return inner;
        }

        return parseNumberOrIdentifier();
    }

    private AbstractExpression parseNumberOrIdentifier() {
        Token token = tokenizer.consume();

        // Check for identifier first and see if it is a unary operator.
        if(token.getType() == TokenType.IDENTIFIER && UNARY_OPERATORS.containsKey(token.getValue())) {
            UnaryOperatorInfo info = UNARY_OPERATORS.get(token.getValue());
            return info.create(parsePrimary());
        }
        else if(token.getType() == TokenType.IDENTIFIER) {
            return new IdentifierExpression(token.getValue());
        }
        else {
            float num = Float.parseFloat(token.getValue());
            return new NumberExpression(num);
        }

    }
}
