package cz.muni.fi.pb162.hw03.impl.comparator;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

import java.util.Comparator;

/**
 * @author Jakub Polacek
 */
public class SymbolFrequencyLetterInverseComparator implements Comparator<SymbolFrequency> {


    @Override
    public int compare(SymbolFrequency freqA, SymbolFrequency freqB) {


            if (freqA.getFrequency() == freqB.getFrequency()) {

                return Character.compare(freqB.getCharacter(), freqA.getCharacter());
            }
            return freqA.getFrequency() - freqB.getFrequency();


    }
}
