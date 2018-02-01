package cz.muni.fi.pb162.hw03.impl.comparator;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.Comparator;

/**
 * @author Jakub Polacek
 */
public class SymbolFrequencyInverseComparator implements Comparator<SymbolFrequency> {

    @Override
    public int compare(SymbolFrequency freqA, SymbolFrequency freqB) {

        return freqB.compareTo(freqA);


    }
}
