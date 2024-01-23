package sol;

import org.junit.Assert;
import org.junit.Test;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

/**
 * A class to test basic decision tree functionality on a basic training dataset
 */
public class BasicDatasetTest {
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)
    String trainingPath = "data/fruits-and-vegetables.csv";
    String targetAttribute = "foodType";
    TreeGenerator testGenerator;

    /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
       this.testGenerator.generateTree(training, this.targetAttribute);
    }

    /**
     * Tests DataSet basic methods
     */
    @Test
    public void testDataSet(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        Assert.assertEquals(training.getSelectionType(), AttributeSelection.ASCENDING_ALPHABETICAL);
        Assert.assertEquals(training.size(), 7);

        List<String> attributes = new ArrayList<>();
        attributes.add("highProtein");
        attributes.add("calories");
        attributes.add("color");
        attributes.add("foodType");
        Assert.assertEquals(training.getAttributeList(), attributes);

    }

    /**
     * Test DataSet advanced methods (rowFilter and collectOptions)
     */
    @Test
    public void testDataSetAdvanced(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);


        List<Row> greenRows = new ArrayList<>();
        greenRows = training.rowFilter("color", "green");
        Assert.assertEquals(greenRows.size(), 3);

        List<String> colorOptions = new ArrayList<>();
        colorOptions.add("green");
        colorOptions.add("orange");
        colorOptions.add("yellow");
        Assert.assertEquals(training.collectOptions("color"), colorOptions);


        List<String> highProteinOptions = new ArrayList<>();
        highProteinOptions.add("true");
        highProteinOptions.add("false");
        Assert.assertEquals(training.collectOptions("highProtein"), highProteinOptions);


        List<String> caloriesOptions = new ArrayList<>();
        caloriesOptions.add("low");
        caloriesOptions.add("high");
        caloriesOptions.add("medium");
        Assert.assertEquals(training.collectOptions("calories"), caloriesOptions);


    }
    /**
     * Test DataSet more advanced methods (popularOption)
     */
    @Test
    public void testDataSetAdvanced2() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        Assert.assertEquals(training.popularOption(training.rowFilter("color", "green"), "foodType"), "vegetable");
        Assert.assertEquals(training.popularOption(training.rowFilter("highProtein", "true"), "foodType"), "vegetable");
        Assert.assertEquals(training.popularOption(training.rowFilter("calories", "low"), "foodType"), "vegetable");
        Assert.assertEquals(training.popularOption(training.rowFilter("calories", "high"), "foodType"), "fruit");
        Assert.assertEquals(training.popularOption(training.getDataObjects(),"foodType"), "vegetable");

    }

    /**
     * Test DataSet more advanced boolean methods (isValidOption and checkTargetValue)
     */
    @Test
    public void testDataSetBoolean() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        Assert.assertEquals(training.ifValidOption("color", "red"), false);
        Assert.assertEquals(training.ifValidOption("color", "green"), true);
        Assert.assertEquals(training.ifValidOption("foodType", "grain"), false);
        Assert.assertEquals(training.ifValidOption("highProtein", "true"), true);

        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("color", "green"), "foodType")), false);
        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("color", "yellow"), "foodType")), true);
        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("highProtein", "false"), "foodType")), false);
        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("calories", "low"), "foodType")), true);

    }

    /**
     * Tests randomizer for random order selection type
     */
    @Test
    public void testRandomize(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);

        System.out.println(training.getAttributeToSplitOn());
        System.out.println(training.getAttributeToSplitOn());
        System.out.println(training.getAttributeToSplitOn());

    }


    /**
     * Tests the expected classification of the "tangerine" row is a fruit
     */
    @Test
    public void testClassification() {
        // makes a new (partial) Row representing the tangerine from the example
        Row tangerine = new Row("test row (tangerine)");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
       Assert.assertEquals("fruit", this.testGenerator.getDecision(tangerine));

    }
}
