package org.dubstepzedd.expressions.binary_operators;


import org.dubstepzedd.expressions.AbstractExpression;

public abstract class BinaryExpression implements AbstractExpression {

    final protected AbstractExpression left;
    final protected AbstractExpression right;

    public BinaryExpression(final AbstractExpression left, final AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    public abstract String getSymbol();

    public abstract int getPrecedence();


}
