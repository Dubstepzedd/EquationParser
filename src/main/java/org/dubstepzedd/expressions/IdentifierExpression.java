package org.dubstepzedd.expressions;

import org.dubstepzedd.context.InterpreterContext;

public class IdentifierExpression implements AbstractExpression {

    private final String identifier;
    public IdentifierExpression(final String identifier) {
        this.identifier = identifier;
    }

    @Override
    public float interpret(InterpreterContext context) {
        return context.getValue(identifier);
    }
}
