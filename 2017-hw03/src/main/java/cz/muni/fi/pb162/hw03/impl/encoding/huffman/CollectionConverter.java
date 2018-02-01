package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.LeafTreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jakub Polacek
 */
public class CollectionConverter {

    /**
     * Creates leaf nodes from our charset
     *
     * @param charSet set of symbol frequencies
     * @return a set of leaf tree nodes sorted by their frequency
     */
    public static NavigableSet<TreeNode> charSetToLeafNodeSet(Set<SymbolFrequency> charSet) {

        if (charSet == null) {
            throw new IllegalArgumentException("Can't have null input.");
        }

        NavigableSet<TreeNode> treeNodes = new TreeSet<>();
        for (SymbolFrequency symbolFrequency : charSet) {

            if (symbolFrequency != null) {
                treeNodes.add(new LeafTreeNode(symbolFrequency));
            }
        }
        return treeNodes;

    }

    /**
     * I should comment things before I forget what they do.
     * Maps character to its bytecode
     *
     * @param nodeStringMap LeafNode - code pairs
     * @return A nice map that transfers our character into code
     */
    public static Map<Character, String> nodeMapToEncodingMap(Map<TreeNode, String> nodeStringMap) {

        if (nodeStringMap == null) {
            throw new IllegalArgumentException("Can't have null input.");
        }

        Map<Character, String> encodingMap = new HashMap<>();

        for (Map.Entry<TreeNode, String> entry : nodeStringMap.entrySet()) {
            if (entry.getKey() == null) {
                throw new IllegalArgumentException("Can't have nulls in map.");
            }
            encodingMap.put(entry.getKey().getCharacter(), entry.getValue());


        }

        return encodingMap;
    }

}
