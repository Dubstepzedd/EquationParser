package org.dubstepzedd.expressions.binary_operators.implementations;

import org.dubstepzedd.context.InterpreterContext;
import org.dubstepzedd.expressions.AbstractExpression;
import org.dubstepzedd.expressions.binary_operators.BinaryExpression;

public class DivisionOperator extends BinaryExpression {
    public DivisionOperator(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public int getPrecedence() {
        return 2;
    }

    @Override
    public float interpret(InterpreterContext context) {
        return left.interpret(context) / right.interpret(context);
    }
}
