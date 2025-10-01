package org.dubstepzedd;

public class Term {

    final private float num;
    public Term(final String term) throws IllegalArgumentException {
        try {
            this.num = Float.parseFloat(term);

        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Term is not a float or integer. Invalid syntax provided.");
        }
    }

    public float getNum() {
        return num;
    }
}
