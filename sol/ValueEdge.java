package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    private String option;
    private ITreeNode child;

    /**
     * This is a constructor for Value edge.
     * @param o String option
     * @param c ITreeNode child
     */
    public ValueEdge(String o, ITreeNode c)
    {
        this.option = o;
        this.child = c;
    }

    /**
     * This method gets the child of type ITreeNode
     * @return ITreeNode representing the child.
     */
    public ITreeNode getChild(){ return this.child;}

    /**
     * This method gets the option of type String.
     * @return String representing option.
     */
    public String getOption(){ return this.option;}

}
