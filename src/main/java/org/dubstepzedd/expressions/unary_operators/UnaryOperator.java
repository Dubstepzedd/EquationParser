package org.dubstepzedd.expressions.unary_operators;
import org.dubstepzedd.expressions.AbstractExpression;

public abstract class UnaryOperator implements AbstractExpression {

    final float num;
    public UnaryOperator(float num) {
        this.num = num;
    }

}
