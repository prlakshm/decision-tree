package sol;

import java.util.List;
import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
public class AttributeNode implements ITreeNode {
    private List<ValueEdge> outgoingEdges;
    private String defaultVal;
    private String attributeName;

    /**
     * This is a constructor for AttributeNode.
     * @param o List<ValueEdge> o
     * @param d String d
     * @param a String a
     */
    public AttributeNode(List<ValueEdge> o, String d, String a)
    {
        this.outgoingEdges = o;
        this.defaultVal = d;
        this.attributeName = a;
    }




    /**
     * This method gets the decision for a particular row of this attribute.
     * @param forDatum a Row that the user wants to get the decision for
     * @return String of the option decision for the given row.
     */
    public String getDecision(Row forDatum){
        String attributeVal = forDatum.getAttributeValue(this.attributeName);
        for(ValueEdge edge : this.outgoingEdges){
            if(edge.getOption().equals(attributeVal)) {
                return edge.getChild().getDecision(forDatum);
            }
        }
        return this.defaultVal;
    };

    /**
     * This method gets the default value of type String.
     * @return String representing the defaultVal.
     */
    public String getDefaultVal(){ return this.defaultVal;}


    /**
     * This method gets the attributeName of type String.
     * @return String representing the attributeName.
     */
    public String getAttributeName(){ return this.attributeName;}


    /**
     * This method gets the outgoingEdges of type List<ValueEdge>.
     * @return List<ValueEdge> representing the outgoingEdges.
     */
    public List<ValueEdge> getOutgoingEdges(){ return this.outgoingEdges;}
}
