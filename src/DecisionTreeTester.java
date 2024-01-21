package src;

import sol.Dataset;
import sol.TreeGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class containing methods to help test decision tree prediction functionality
 * @param <G>
 * @param <D>
 */
public class DecisionTreeTester<G extends ITreeGenerator<D>, D extends IDataset> {
    private static final String DATA_BASE = "data/";
    private static final String LIKE_TO_EAT = "likeToEat";

    // mushrooms dataset
    private static final String IS_POISONOUS = "isPoisonous";
    private static final String MUSHROOMS_BASE = DATA_BASE + "mushrooms/";
    private static final String MUSHROOMS_TRAINING = MUSHROOMS_BASE + "training.csv";
    private static final String MUSHROOMS_TESTING = MUSHROOMS_BASE + "testing.csv";

    // villains dataset
    private static final String IS_VILLAIN = "isVillain";
    private static final String VILLAINS_BASE = DATA_BASE + "villains/";
    private static final String VILLAINS_TRAINING = VILLAINS_BASE + "training.csv";
    private static final String VILLAINS_TESTING = VILLAINS_BASE + "testing.csv";

    // candidates dataset
    private static final String CANDIDATES_BASE = DATA_BASE + "candidates/";
    private static final String CANDIDATES_TRAINING_GENDER_EQUAL =
            CANDIDATES_BASE + "training-gender-equal.csv";
    private static final String CANDIDATES_TRAINING_GENDER_UNEQUAL =
            CANDIDATES_BASE + "training-gender-unequal.csv";
    private static final String CANDIDATES_TRAINING_GENDER_CORRELATED =
            CANDIDATES_BASE + "training-gender-correlated.csv";
    private static final String CANDIDATES_TESTING = CANDIDATES_BASE + "testing.csv";

    // songs dataset
    private static final String IS_POPULAR = "isPopular";
    private static final String SONG_BASE = DATA_BASE + "songs/";
    private static final String SONG_TRAINING = SONG_BASE + "training.csv";
    private static final String SONG_TESTING = SONG_BASE + "testing.csv";

    private Class<G> generatorClass;
    private Class<D> datasetClass;
    private ITreeGenerator<D> generator;

    /**
     * A constructor for the decision tree tester
     * @param datasetClass   the DataTable class
     * @param generatorClass the TreeGenerator class
     */
    public DecisionTreeTester(Class<G> generatorClass, Class<D> datasetClass)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        this.generatorClass = generatorClass;
        this.datasetClass = datasetClass;
        Constructor<G> generatorConstructor = this.generatorClass.getConstructor();
        generatorConstructor.setAccessible(true);
        this.generator = generatorConstructor.newInstance();
    }

    /**
     * Calculates the average decision tree accuracy based on provided file paths to datasets
     * @param trainingDataPath file path to the training dataset CSV
     * @param testingDataPath file path to the testing dataset CSV
     * @param targetAttribute attribute for the decision tree to predict on
     * @param numIterations number of iterations to calculate the average accuracy with
     * @return - average decision tree accuracy
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public double getAverageDecisionTreeAccuracy(String trainingDataPath, String testingDataPath,
                                                 String targetAttribute, int numIterations)
            throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        D trainingData = makeDataset(trainingDataPath, this.datasetClass);
        D testingData = makeDataset(testingDataPath, this.datasetClass);
        return this.getAverageDecisionTreeAccuracy(trainingData, testingData, targetAttribute,
                numIterations);
    }

    /**
     * Calculates the average decision tree accuracy based on provided datasets
     * @param trainingData the training dataset object
     * @param testingData the testing dataset object
     * @param targetAttribute attribute for the decision tree to predict on
     * @param numIterations number of iterations to calculate the average accuracy with
     * @return - average decision tree accuracy
     */
    public double getAverageDecisionTreeAccuracy(D trainingData, D testingData,
                                                 String targetAttribute, int numIterations) {

        double[] accuracies = new double[numIterations];
        for (int i = 0; i < numIterations; i++) {
            accuracies[i] = this.getDecisionTreeAccuracy(trainingData, testingData, targetAttribute);
        }
        return this.getMean(accuracies);
    }

    /**
     * Calculates the accuracy of a trained decision tree
     * @param trainingData the training dataset object
     * @param testingData the testing dataset object
     * @param targetAttribute attribute for the decision tree to predict on
     * @return - accuracy of a trained decision tree
     */
    public double getDecisionTreeAccuracy(D trainingData, D testingData, String targetAttribute) {
        this.generator.generateTree(trainingData, targetAttribute);
        return this.getDecisionTreeAccuracy(testingData, targetAttribute);
    }

    /**
     * A helper method for calculating the accuracy of a trained decision tree
     * @param testingData the testing dataset object
     * @param targetAttribute attribute for the decision tree to predict on
     * @return - accuracy of a decision tree
     */
    public double getDecisionTreeAccuracy(D testingData, String targetAttribute) {
        double numCorrectClassifications = 0;
        for (Row datum : testingData.getDataObjects()) {
            String prediction = this.generator.getDecision(datum);
            if (prediction.equals(datum.getAttributeValue(targetAttribute))) {
                numCorrectClassifications += 1;
            }
        }
        return numCorrectClassifications / testingData.size();
    }

    /**
     * Calculates the mean of an array of doubles
     * @param arr array of doubles
     * @return - average of the array
     */
    private double getMean(double[] arr) {
        double sum = 0;
        for (double d : arr) {
            sum += d;
        }
        return sum / arr.length;
    }

    /**
     * Generates a dataset object given a filepath and dataset class
     * @param dataPath filepath to the CSV file
     * @param datasetClass class for a Dataset object
     * @param <D>
     * @return - the new dataset object
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <D extends IDataset> D makeDataset(String dataPath, Class<D> datasetClass)
            throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Row> dataList = DecisionTreeCSVParser.parse(dataPath);

        Constructor<D> constructor = datasetClass.getConstructor(List.class, List.class, AttributeSelection.class);
        constructor.setAccessible(true);
        return constructor.newInstance(getAttributesFromData(dataList), dataList, AttributeSelection.RANDOM);
    }

    /**
     * Produces a list of attributes present in a given dataset
     * @param data a list of rows where one row represents one data object
     * @return - arraylist of attributes
     */
    private static List<String> getAttributesFromData(List<Row> data) {
        Set<String> attributeSet = new HashSet<>();
        for (Row datum : data) {
            attributeSet.addAll(datum.getAttributes());
        }
        return new ArrayList<>(attributeSet);
    }

    /**
     * Calculates the average accuracy of the decision tree on the mushrooms dataset
     * @param args
     */
//    TODO: Uncomment this when you are ready to test your implementation!
//    public static void main(String[] args) {
//        DecisionTreeTester<TreeGenerator, Dataset> tester;
//        try {
//            tester = new DecisionTreeTester<>(TreeGenerator.class, Dataset.class);
//            Dataset trainingData = makeDataset(SONG_TRAINING, Dataset.class);
//            double accuracy =
//                    tester.getDecisionTreeAccuracy(trainingData, trainingData, IS_POPULAR);
//            System.out.println("Accuracy on training data: " + accuracy);
//
//            int numIters = 100;
//            Dataset testingData = makeDataset(SONG_TESTING, Dataset.class);
//            accuracy = tester.getAverageDecisionTreeAccuracy(trainingData, testingData, IS_POPULAR, numIters);
//            System.out.println("Accuracy on testing data: " + accuracy);
//
//        } catch (InstantiationException | InvocationTargetException
//                 | NoSuchMethodException | IllegalAccessException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
