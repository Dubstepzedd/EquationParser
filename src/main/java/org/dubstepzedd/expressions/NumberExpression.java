package org.dubstepzedd.expressions;

import org.dubstepzedd.context.InterpreterContext;

public class NumberExpression implements AbstractExpression {

    private final float number;

    public NumberExpression(float number) {
        this.number = number;
    }

    @Override
    public float interpret(InterpreterContext context) {
        return number;
    }
}
