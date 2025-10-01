package org.dubstepzedd.expressions;

import org.dubstepzedd.context.InterpreterContext;

public interface AbstractExpression {

    float interpret(InterpreterContext context);

}
