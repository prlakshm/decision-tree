package src;

/**
 * A class that generates a tree and looks up a decision.
 *
 * @param <D> the dataset implementation for the tree generator
 */
public interface ITreeGenerator<D extends IDataset> {

    /**
     * Generates the decision tree for a given training dataset.
     *
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    public void generateTree(D trainingData, String targetAttribute);

    /**
     * Looks up the decision for a datum in the decision tree.
     *
     * @param datum the datum to lookup a decision for
     * @return the decision of the row
     */
    public String getDecision(Row datum);
}
