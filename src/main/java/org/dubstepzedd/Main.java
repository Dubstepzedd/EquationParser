package org.dubstepzedd;

import org.dubstepzedd.context.InterpreterContext;
import org.dubstepzedd.expressions.AbstractExpression;

public class Main {
    public static void main(String[] args) {
        InterpreterContext interpreterContext = new InterpreterContext();
        interpreterContext.setIdentifier("PI", (float) Math.PI);
        ExpressionParser parser = new ExpressionParser();
        AbstractExpression expr = parser.parse("sin(PI/2)*2 + cos(1-1) + 5*7");
        System.out.println(expr.interpret(interpreterContext));

    }
}
