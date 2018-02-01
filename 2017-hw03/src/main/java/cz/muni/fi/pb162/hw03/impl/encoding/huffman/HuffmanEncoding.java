package cz.muni.fi.pb162.hw03.impl.encoding.huffman;

import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.HuffmanAlgorithm;
import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;
import cz.muni.fi.pb162.hw03.impl.encoding.node.InnerTreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author Jakub Polacek
 */
public class HuffmanEncoding implements Encoding, HuffmanAlgorithm {

    private TreeNode root;
    private Map<Character, String> characterStringMap;

    /**
     * Encoding constructor
     *
     * @param table table defining encoding
     */
    public HuffmanEncoding(FrequencyTable table) {
        root = frequencyTableToTree(table.createTable());

        Map<TreeNode, String> treeStringMap = new HashMap<>();
        createCodeTree(treeStringMap, root, "");

        characterStringMap = CollectionConverter.nodeMapToEncodingMap(treeStringMap);
    }

    @Override
    public String getEncodingString(char encodingChar) {

        return characterStringMap.get(encodingChar);
    }

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public TreeNode frequencyTableToTree(Set<SymbolFrequency> characterFrequencies) {


        if (characterFrequencies == null) {
            throw new IllegalArgumentException("Can't have null arguments");
        }

        SortedSet<TreeNode> forest = CollectionConverter.charSetToLeafNodeSet(characterFrequencies);


        if (forest.size() == 0) {
            return null;

        }

        while (forest.size() > 1) {
            TreeNode first = forest.first();
            forest.remove(forest.first());

            TreeNode second = forest.first();
            forest.remove(forest.first());

            TreeNode combineLasts = new InnerTreeNode(first, second);
            forest.add(combineLasts);
        }

        return forest.first();
    }

    @Override
    public void createCodeTree(Map<TreeNode, String> map, TreeNode node, String encodingString) {

        if (node == null) {
            return;
        }
        if (map == null || encodingString == null) {
            throw new IllegalArgumentException("No nulls, please.");
        }

        // if we only have one char in file
        if (node.isLeaf() && encodingString.length() == 0) {
            map.put(node, "0");
            return;
        }


        if (node.isLeaf()) {
            map.put(node, encodingString);
        } else {

            if (node.getLeftChild() != null) {

                createCodeTree(map, node.getLeftChild(), encodingString + "0");
            }

            if (node.getRightChild() != null) {
                createCodeTree(map, node.getRightChild(), encodingString + "1");
            }


        }
    }
}

