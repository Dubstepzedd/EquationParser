package org.dubstepzedd.expressions;
import org.dubstepzedd.context.InterpreterContext;

public class NumberExpression implements AbstractExpression {

    private final float num;
    public NumberExpression(float num) {
        this.num = num;
    }

    @Override
    public float interpret(InterpreterContext context) {
        return this.num;
    }
}
