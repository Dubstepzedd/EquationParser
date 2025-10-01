package org.dubstepzedd;

import org.dubstepzedd.expressions.AbstractExpression;

public class Main {
    public static void main(String[] args) throws Exception {
        ExpressionParser parser = new ExpressionParser();
        AbstractExpression expr = parser.parse("104a*10");
        System.out.println(expr.interpret(null));
    }
}
