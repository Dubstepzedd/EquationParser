package org.dubstepzedd.context;

import java.util.HashMap;
import java.util.Map;

public class InterpreterContext {

    private final Map<String, Float> identifiers;
    public InterpreterContext() {
        identifiers = new HashMap<String, Float>();
    }

    public void setIdentifier(final String identifier, final float value) {
        identifiers.put(identifier, value);
    }

    public float getValue(final String identifier) {
        if(!identifiers.containsKey(identifier)) {
            throw new RuntimeException("Identifier has to value set.");
        }

        return identifiers.get(identifier);
    }

}
