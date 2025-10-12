package org.dubstepzedd.expressions.binary_operators;

import org.dubstepzedd.expressions.AbstractExpression;

public class BinaryOperatorInfo {
    private final int precedence;
    private final Class<? extends BinaryExpression> operatorClass;

    public BinaryOperatorInfo(int precedence, Class<? extends BinaryExpression> operatorClass) {
        this.precedence = precedence;
        this.operatorClass = operatorClass;
    }

    public BinaryExpression create(AbstractExpression left, AbstractExpression right) {
        try {
            return operatorClass.getConstructor(AbstractExpression.class, AbstractExpression.class)
                    .newInstance(left, right);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create operator", e);
        }
    }

    public final int getPrecedence() {
        return precedence;
    }
}