package org.dubstepzedd.expressions.unary_operators;

import org.dubstepzedd.context.InterpreterContext;

public class SinusOperator extends UnaryOperator {
    public SinusOperator(float num) {
        super(num);
    }

    @Override
    public float interpret(InterpreterContext context) {
        return (float)Math.sin(this.num);
    }
}
