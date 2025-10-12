package org.dubstepzedd.expressions.unary_operators.operators;

import org.dubstepzedd.context.InterpreterContext;
import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.unary_operators.UnaryExpression;

public class CosinesOperator extends UnaryExpression {
    public CosinesOperator(AbstractExpression expr) {
        super(expr);
    }

    @Override
    public String getSymbol() {
        return "cos";
    }

    @Override
    public float interpret(InterpreterContext context) {
        return (float)Math.cos(expr.interpret(context));
    }
}
