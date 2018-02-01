package cz.muni.fi.pb162.hw03.impl.encoding.node;

import cz.muni.fi.pb162.hw03.TreeNode;
import cz.muni.fi.pb162.hw03.impl.SymbolFrequency;

/**
 * @author Jakub Polacek
 */
public class LeafTreeNode extends AbstractTreeNode implements TreeNode {


    private SymbolFrequency symbolFrequency;

    /**
     * Leaf tree node constructor
     *
     * @param symbolFrequency defining symbolFrequency
     */
    public LeafTreeNode(SymbolFrequency symbolFrequency) {
        super(null, null);
        this.symbolFrequency = symbolFrequency;
    }

    @Override
    public int getFrequency() {
        return symbolFrequency.getFrequency();
    }

    @Override
    public Character getCharacter() {
        return symbolFrequency.getCharacter();
    }

    @Override
    public int hashCode() {
        return symbolFrequency.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof LeafTreeNode) {
                return ((LeafTreeNode) o).getCharacter() == getCharacter()
                        && ((LeafTreeNode) o).getFrequency() == getFrequency();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Leaf " + symbolFrequency;
    }


}
