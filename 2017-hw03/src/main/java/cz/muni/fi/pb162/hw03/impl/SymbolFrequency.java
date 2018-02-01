package cz.muni.fi.pb162.hw03.impl;


/**
 * @author Jakub Polacek
 */
public class SymbolFrequency implements Comparable<SymbolFrequency> {

    private int frequency;
    private char symbol;

    /**
     * Symbol frequency constructor
     *
     * @param symbol    char
     * @param frequency number of times repeated
     */
    public SymbolFrequency(char symbol, int frequency) {

        this.frequency = frequency;
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return symbol | frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof SymbolFrequency) {
                return (frequency == ((SymbolFrequency) o).frequency) && ((SymbolFrequency) o).symbol == symbol;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return frequency + "x'" + symbol + '\'';
    }

    public int getFrequency() {
        return frequency;
    }

    public char getCharacter() {
        return symbol;
    }

    @Override
    public int compareTo(SymbolFrequency symbol) {

        if (symbol == null) {
            return 1;
        }


        if (frequency == symbol.frequency) {
            return Character.compare(getCharacter(), symbol.getCharacter());
        }
        return frequency - symbol.frequency;
    }

}
