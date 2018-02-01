package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;

/**
 * @author Jakub Polacek
 */
public abstract class AbstractTreeNode implements TreeNode, Comparable<TreeNode> {

    private TreeNode leftChild;
    private TreeNode rightChild;

    /**
     * Constructor fo abstract tree node
     *
     * @param leftChild  left child
     * @param rightChild right child
     */
    public AbstractTreeNode(TreeNode leftChild, TreeNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public abstract int getFrequency();

    @Override
    public abstract Character getCharacter();

    @Override
    public TreeNode getLeftChild() {
        return leftChild;
    }



    @Override
    public TreeNode getRightChild() {
        return rightChild;
    }


    @Override
    public boolean isLeaf() {
        return getRightChild() == null && getLeftChild() == null;
    }


    private int compareLeftChildren(TreeNode treeNode) {

        int compareLeft = 0;
        if (getLeftChild() == null || treeNode.getLeftChild() == null) {

            if (getLeftChild() == null) {
                compareLeft -= 1;
            }
            if (treeNode.getLeftChild() == null) {
                compareLeft += 1;
            }

        } else {
            compareLeft = getLeftChild().compareTo(treeNode.getLeftChild());
        }

        return compareLeft;
    }

    private int compareRightChildren(TreeNode treeNode) {

        int compareRight = 0;
        if (getRightChild() == null || treeNode.getRightChild() == null) {

            if (getRightChild() == null) {
                compareRight -= 1;
            }
            if (treeNode.getRightChild() == null) {
                compareRight += 1;
            }

        } else {
            compareRight = getRightChild().compareTo(treeNode.getRightChild());
        }
        return compareRight;
    }


    @Override
    public int compareTo(TreeNode treeNode) {


        if (treeNode == null) {
            return 1;
        }

        if (treeNode.getFrequency() != getFrequency()) {
            return getFrequency() - treeNode.getFrequency();
        }

        if (isLeaf() && treeNode.isLeaf()) {

            return getCharacter().compareTo(treeNode.getCharacter());


        }

        if (!isLeaf() && !treeNode.isLeaf()) {


            int compareLeft = compareLeftChildren(treeNode);

            if (compareLeft == 0) {
                return compareRightChildren(treeNode);
            }

            return compareLeft;


        }

        if (isLeaf()) {
            return 1;

        }

        return -1;


    }


}

