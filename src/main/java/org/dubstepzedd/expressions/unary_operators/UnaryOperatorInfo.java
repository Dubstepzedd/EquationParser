package org.dubstepzedd.expressions.unary_operators;

import org.dubstepzedd.expressions.AbstractExpression;

public class UnaryOperatorInfo {

    private final Class<? extends UnaryExpression> operatorClass;

    public UnaryOperatorInfo(Class<? extends UnaryExpression> operatorClass) {
        this.operatorClass = operatorClass;
    }

    public UnaryExpression create(AbstractExpression expr) {
        try {
            return operatorClass.getConstructor(AbstractExpression.class)
                    .newInstance(expr);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create operator", e);
        }
    }

}
