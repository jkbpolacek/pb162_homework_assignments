package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jakub Polacek
 */
public class FrequencyTable {

    private String input;
    private Map<Character, Integer> charMap;

    /**
     * Frequency table constructor
     *
     * @param input string from which to calculate char frequencies
     * @throws NullPointerException if no input is given
     */
    public FrequencyTable(String input) throws NullPointerException {

        if (input == null) {
            throw new NullPointerException("Null argument in constructor is not allowed");
        }
        this.input = input;

        charMap = new HashMap<>();


        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            charMap.putIfAbsent(c, 0);
            charMap.put(c, charMap.get(c) + 1);
        }
    }

    /**
     * Creates "table" of symbol frequencies for further coding creation
     *
     * @return set of symbol frequencies
     */
    public Set<SymbolFrequency> createTable() {


        Set<SymbolFrequency> result = new HashSet<>();

        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            result.add(new SymbolFrequency(entry.getKey(), entry.getValue()));
        }

        return result;

    }
}
