package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw03.BinaryCodec;
import cz.muni.fi.pb162.hw03.Encoding;
import cz.muni.fi.pb162.hw03.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Jakub Polacek
 */
public class BinaryCodecImpl implements BinaryCodec {

    private Encoding encoding;

    /**
     * Binary codec constructor
     *
     * @param encoding encoding used to code and decode
     */
    public BinaryCodecImpl(Encoding encoding) {
        this.encoding = encoding;
    }

    @Override
    public String encode(String originalMessage) {


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < originalMessage.length(); i++) {

            sb.append(encoding.getEncodingString(originalMessage.charAt(i)));
        }

        return sb.toString();

    }


    /**
     * Determine whether to go left or right
     *
     * @param currentNode   current node
     * @param binary decode whether to go left or right
     * @return left or right child of node
     * @throws IllegalArgumentException if message does not work
     */
    private TreeNode determineNextNode(TreeNode currentNode,
                                       char binary)
            throws IllegalArgumentException {

        switch (binary) {
            case '0':
                return currentNode.getLeftChild();
            case '1':
                return currentNode.getRightChild();
            default:
                throw new IllegalArgumentException("You put in this string something that does belong. Sorry.");
        }
    }

    @Override
    public String decode(String binaryMessage) throws IllegalArgumentException, NullPointerException {

        if (binaryMessage == null) {
            throw new NullPointerException("Can't have null input.");
        }

        if (binaryMessage.length() == 0) {
            return "";
        }


        TreeNode currentNode = encoding.getRoot();
        List<Character> decodedChars = new ArrayList<>();
        if (!currentNode.isLeaf()) {

            for (int i = 0; i < binaryMessage.length(); i++) {

                currentNode = determineNextNode(currentNode, binaryMessage.charAt(i));

                if (currentNode.isLeaf()) {
                    decodedChars.add(currentNode.getCharacter());
                    currentNode = encoding.getRoot();
                }

            }


        } else {

            for (int i = 0; i < binaryMessage.length(); i++) {
                decodedChars.add(encoding.getRoot().getCharacter());
            }

        }
        return decodedChars.stream().map(c -> c.toString()).collect(Collectors.joining());


    }


}
