package org.dubstepzedd.expressions.binary_operators;

import org.dubstepzedd.context.InterpreterContext;
import org.dubstepzedd.expressions.AbstractExpression;

public class AdditionOperator extends BinaryExpression {

    public AdditionOperator(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public float interpret(InterpreterContext context) {
        return left.interpret(context) + right.interpret(context);
    }
}
