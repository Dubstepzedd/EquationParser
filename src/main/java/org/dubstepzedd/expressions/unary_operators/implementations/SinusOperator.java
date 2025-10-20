package org.dubstepzedd.expressions.unary_operators.implementations;

import org.dubstepzedd.context.InterpreterContext;
import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.unary_operators.UnaryExpression;

public class SinusOperator extends UnaryExpression {
    public SinusOperator(AbstractExpression expr) {
        super(expr);
    }

    @Override
    public String getSymbol() {
        return "sin";
    }

    @Override
    public float interpret(InterpreterContext context) {
        return (float)Math.sin(expr.interpret(context));
    }
}
