package org.dubstepzedd.expressions.unary_operators;
import org.dubstepzedd.expressions.AbstractExpression;

public abstract class UnaryExpression implements AbstractExpression {

    protected final AbstractExpression expr;
    public UnaryExpression(final AbstractExpression expr) {
        this.expr = expr;
    }

    public abstract String getSymbol();


}
