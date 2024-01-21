package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeNode interface!
public class DecisionLeaf implements ITreeNode {
private String decision;

    /**
     * This is a constructor of Decision Leaf.
     * @param d String decision
     */
    public DecisionLeaf(String d)
{
    this.decision = d;
}

    /**
     * Return decision of leaf in decision tree
     * @param forDatum the datum to lookup a decision for
     * @return string of decisionS
     */
    @Override
    public String getDecision(Row forDatum){
        return this.decision;
    };
}
