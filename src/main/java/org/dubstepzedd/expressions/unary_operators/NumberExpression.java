package org.dubstepzedd.expressions.unary_operators;

import org.dubstepzedd.context.InterpreterContext;


public class NumberExpression extends UnaryOperator {

    public NumberExpression(float number) {
        super(number);
    }

    @Override
    public float interpret(InterpreterContext context) {
        return this.num;
    }
}
