package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;

/**
 * @author Jakub Polacek
 */
public class InnerTreeNode extends AbstractTreeNode implements TreeNode {


    /**
     * Inner tree node constructor
     *
     * @param leftChild  when u wanna go left
     * @param rightChild when u wanna be right
     */
    public InnerTreeNode(TreeNode leftChild, TreeNode rightChild) {
        super(leftChild, rightChild);
    }

    @Override
    public Character getCharacter() {
        return EMPTY_CHAR;
    }

    @Override
    public int getFrequency() {
        return getLeftChild().getFrequency() + getRightChild().getFrequency();
    }

    @Override
    public int hashCode() {
        return getFrequency() | getLeftChild().hashCode() | getRightChild().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof InnerTreeNode) {
                return ((InnerTreeNode) o).getRightChild().equals(getRightChild())
                        && ((InnerTreeNode) o).getLeftChild().equals(getLeftChild());
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getLeftChild().getCharacter()
                + "-(" + Integer.toString(getFrequency())
                + ")-" + getRightChild().getCharacter();
    }


}
