package org.dubstepzedd;

import org.dubstepzedd.tokenization.Tokenizer;

public class Main {
    public static void main(String[] args) {
        //ExpressionParser parser = new ExpressionParser();
        //AbstractExpression expr = parser.parse("10 + 5*7");
        //System.out.println(expr.interpret(null));

        Tokenizer tokenizer = new Tokenizer("sin(5)*y-10/(5-1)");
        System.out.println(tokenizer);

    }
}
