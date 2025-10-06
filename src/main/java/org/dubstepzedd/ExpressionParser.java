package org.dubstepzedd;

import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.unary_operators.NumberExpression;
import org.dubstepzedd.expressions.binary_operators.*;
import org.dubstepzedd.tokenization.Tokenizer;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {

    private Tokenizer tokenizer;
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
        this.tokenizer = new Tokenizer(input);
        return parseExpression(0);
    }

    private AbstractExpression parseExpression(int minPrecedence) {
        AbstractExpression left = parsePrimary();

        while (tokenizer.hasTokens() && OPERATORS.containsKey(tokenizer.peek())) {
            OperatorInfo opInfo = OPERATORS.get(tokenizer.peek());

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

        return parseNumber();
    }

    private AbstractExpression parseNumber() {
        float num = Float.parseFloat(tokenizer.consume());
        return new NumberExpression(num);
    }
}
