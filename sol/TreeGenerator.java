package sol;

import com.sun.source.tree.Tree;
import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeGenerator interface!
public class TreeGenerator implements ITreeGenerator<Dataset> {
    // TODO: document this field
    private ITreeNode root;
    private Dataset data;
    private String target;

//    /**
//     * This is a constructor for TreeGenerator
//     * @param r ITreeNode root
//     * @param d  Dataset data
//     * @param t String target
//     */
//    public TreeGenerator(ITreeNode r, Dataset d, String t)
//    {
//        this.root = r;
//        this.data = d;
//        this.target = t;
//    }

    /**
     * This method gets the root of type ITreeNode.
     * @return ITreeNode representing the root.
     */
    public ITreeNode getRoot(){ return this.root;}


    /**
     * This method generates a tree for a given Dataset d.
     * @param d Dataset user wants to generate tree for
     * @return ITreeNode of tree
     */
    public ITreeNode generateTreeHelper(Dataset d){
        if(d.checkTargetAttribute(d.getDataObjects(), this.target) || d.getAttributeList().isEmpty()){
            return new DecisionLeaf(d.getDataObjects().get(0).getAttributeValue(this.target));
        }
        else {
            String attr = d.getAttributeToSplitOn();
            /**
             * split dataset based on attribute into list dataset
             * multiple datasets- call helper on ever subset in for loop
             * returns node every call -- child node of value edge, create value edge
             * in for loop create multiple value edges and then when get out create attribute node
             * return node created
             */
            d.getAttributeList().remove(attr);
            List<String> possOptions = new ArrayList<>();
            List<ValueEdge> valueEdgeList = new ArrayList<>();
            possOptions = d.collectOptions(attr);
            for(String option : possOptions){
                Dataset subData = new Dataset(d.getAttributeList(),d.rowFilter(attr, option),d.getSelectionType());
                ITreeNode child = this.generateTreeHelper(subData);
                ValueEdge val = new ValueEdge(option, child);
                valueEdgeList.add(val);
            }
            return new AttributeNode(valueEdgeList, d.popularOption(d.getDataObjects(), this.target), attr);
        }
    }
    /**
     * Generates the decision tree for a given training dataset.
     *
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        this.data = trainingData;
        this.target = targetAttribute;
        List<String> newAttrList = new ArrayList<>(this.data.getAttributeList());
        newAttrList.remove(targetAttribute);

        Dataset newData = new Dataset(newAttrList, this.data.getDataObjects(), this.data.getSelectionType());

        this.root = this.generateTreeHelper(newData);


    }


    /**
     * Looks up the decision for a datum in the decision tree.
     *
     * @param datum the datum to lookup a decision for
     * @return the decision of the row
     */
    @Override
    public String getDecision(Row datum){
        return this.root.getDecision(datum);
    }
}
