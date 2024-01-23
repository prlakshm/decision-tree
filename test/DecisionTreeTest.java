package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import sol.Dataset;
import sol.TreeGenerator;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
    String trainingPath = "data/fruits-and-vegetables.csv";
    String targetAttribute = "foodType";
    TreeGenerator testGenerator;

    String trainingPath2 = "data/isMammal.csv";
    String targetAttribute2 = "isMammal";
    TreeGenerator testGenerator2;

    String trainingPath4 = "data/candidates/testing.csv";
    String targetAttribute4 = "hired";
    TreeGenerator testGenerator4;

    String trainingPath6 = "data/songs/testing.csv";
    String targetAttribute6 = "isPopular";
    TreeGenerator testGenerator6;

    String trainingPath9 = "data/all-fruits.csv";
    String targetAttribute9 = "foodType";
    TreeGenerator testGenerator9;

    /**
     * This test shows syntax for a basic assertEquals assertion -- can be deleted
     */
    @Test
    public void testAssertEqual() {
        assertEquals(1 + 1, 2);
    }

    /**
     * This test shows syntax for a basic assertTrue assertion -- can be deleted
     */
    @Test
    public void testAssertTrue() {
        assertTrue(true);
    }

    /**
     * This test shows syntax for a basic assertFalse assertion -- can be deleted
     */
    @Test
    public void testAssertFalse() {
        assertFalse(false);
    }


//    /**
//     * Tests DataSet basic methods
//     */
//    @Test
//    public void testDataSet(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
//        Assert.assertEquals(training.getSelectionType(), AttributeSelection.ASCENDING_ALPHABETICAL);
//        Assert.assertEquals(training.size(), 7);
//
//        List<String> attributes = new ArrayList<>();
//        attributes.add("highProtein");
//        attributes.add("calories");
//        attributes.add("color");
//        attributes.add("foodType");
//        Assert.assertEquals(training.getAttributeList(), attributes);
//
//    }
//
//    /**
//     * Test DataSet advanced methods (rowFilter and collectOptions)
//     */
//    @Test
//    public void testDataSetAdvanced(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
//
//
//        List<Row> greenRows = new ArrayList<>();
//        greenRows = training.rowFilter("color", "green");
//        Assert.assertEquals(greenRows.size(), 3);
//
//        List<String> colorOptions = new ArrayList<>();
//        colorOptions.add("green");
//        colorOptions.add("orange");
//        colorOptions.add("yellow");
//        Assert.assertEquals(training.collectOptions("color"), colorOptions);
//
//
//        List<String> highProteinOptions = new ArrayList<>();
//        highProteinOptions.add("true");
//        highProteinOptions.add("false");
//        Assert.assertEquals(training.collectOptions("highProtein"), highProteinOptions);
//
//
//        List<String> caloriesOptions = new ArrayList<>();
//        caloriesOptions.add("low");
//        caloriesOptions.add("high");
//        caloriesOptions.add("medium");
//        Assert.assertEquals(training.collectOptions("calories"), caloriesOptions);
//
//
//    }
//    /**
//     * Test DataSet more advanced methods (popularOption)
//     */
//    @Test
//    public void testDataSetAdvanced2() {
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
//
//        Assert.assertEquals(training.popularOption(training.rowFilter("color", "green"), "foodType"), "vegetable");
//        Assert.assertEquals(training.popularOption(training.rowFilter("highProtein", "true"), "foodType"), "vegetable");
//        Assert.assertEquals(training.popularOption(training.rowFilter("calories", "low"), "foodType"), "vegetable");
//        Assert.assertEquals(training.popularOption(training.rowFilter("calories", "high"), "foodType"), "fruit");
//        Assert.assertEquals(training.popularOption(training.getDataObjects(),"foodType"), "vegetable");
//
//    }
//
//    /**
//     * Test DataSet more advanced boolean methods (isValidOption and checkTargetValue)
//     */
//    @Test
//    public void testDataSetBoolean() {
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
//
//        Assert.assertEquals(training.ifValidOption("color", "red"), false);
//        Assert.assertEquals(training.ifValidOption("color", "green"), true);
//        Assert.assertEquals(training.ifValidOption("foodType", "grain"), false);
//        Assert.assertEquals(training.ifValidOption("highProtein", "true"), true);
//
//        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("color", "green"), "foodType")), false);
//        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("color", "yellow"), "foodType")), true);
//        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("highProtein", "false"), "foodType")), false);
//        Assert.assertEquals((training.checkTargetAttribute(training.rowFilter("calories", "low"), "foodType")), true);
//
//    }
//
//    /**
//     * Tests randomizer for random order selection type
//     */
//    @Test
//    public void testRandomize(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);
//
//        System.out.println(training.getAttributeToSplitOn());
//        System.out.println(training.getAttributeToSplitOn());
//        System.out.println(training.getAttributeToSplitOn());
//
//    }
//
//    /**
//     * Tests generateTree and setAttributeValue.
//     */
//    @Test
//    public void testFruitRandom(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);
//
//        // builds a TreeGenerator object and generates a tree for "foodType"
//        this.testGenerator = new TreeGenerator();
//        this.testGenerator.generateTree(training, this.targetAttribute);
//
//        // makes a new (partial) Row representing the tangerine from the example
//        Row tangerine = new Row("test row (tangerine)");
//        tangerine.setAttributeValue("color", "orange");
//        tangerine.setAttributeValue("highProtein", "false");
//        tangerine.setAttributeValue("calories", "high");
//        //return classification of tangerine under new decision tree
//        Assert.assertEquals("fruit", this.testGenerator.getDecision(tangerine));
//
//        // makes a new (partial) Row representing the cucumber from the example
//        Row cucumber = new Row("test row (cucumber)");
//        cucumber.setAttributeValue("color", "green");
//        cucumber.setAttributeValue("highProtein", "false");
//        cucumber.setAttributeValue("calories", "low");
//        //return classification of cucumber under new decision tree
//        Assert.assertEquals("vegetable", this.testGenerator.getDecision(cucumber));
//
//
//    }
//
//    @Test
//    public void testFruitAscAlpha(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
//
//        // builds a TreeGenerator object and generates a tree for "foodType"
//        this.testGenerator = new TreeGenerator();
//        this.testGenerator.generateTree(training, this.targetAttribute);
//
//        // makes a new (partial) Row representing the grape from the example
//        Row grape = new Row("test row (grape)");
//        grape.setAttributeValue("color", "purple");
//        grape.setAttributeValue("highProtein", "false");
//        grape.setAttributeValue("calories", "low");
//        //return classification of grape under new decision tree
//        Assert.assertEquals("vegetable", this.testGenerator.getDecision(grape));
//
//        // makes a new (partial) Row representing the redPepper from the example
//        Row redPepper = new Row("test row (redPepper)");
//        redPepper.setAttributeValue("color", "red");
//        redPepper.setAttributeValue("highProtein", "false");
//        redPepper.setAttributeValue("calories", "low");
//        //return classification of redPepper under new decision tree
//        Assert.assertEquals("vegetable", this.testGenerator.getDecision(redPepper));
//
//
//    }
//
//    @Test
//    public void testFruitDescAlpha(){
//        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
//        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
//        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.DESCENDING_ALPHABETICAL);
//
//        // builds a TreeGenerator object and generates a tree for "foodType"
//        this.testGenerator = new TreeGenerator();
//        this.testGenerator.generateTree(training, this.targetAttribute);
//
//        // makes a new (partial) Row representing the goldenApple from the example
//        Row goldenApple = new Row("test row (goldenApple)");
//        goldenApple.setAttributeValue("color", "yellow");
//        goldenApple.setAttributeValue("highProtein", "true");
//        goldenApple.setAttributeValue("calories", "medium");
//        //return classification of goldenApple under new decision tree
//        Assert.assertEquals("fruit", this.testGenerator.getDecision(goldenApple));
//
//        // makes a new (partial) Row representing the pumpkin from the example
//        Row pumpkin = new Row("test row (pumpkin)");
//        pumpkin.setAttributeValue("color", "orange");
//        pumpkin.setAttributeValue("highProtein", "medium");
//        pumpkin.setAttributeValue("calories", "high");
//        //return classification of pumpkin under new decision tree
//        Assert.assertEquals("vegetable", this.testGenerator.getDecision(pumpkin));
//
//
//    }
//

    /**
     * Tests candidates dataset with random node order
     */
    @Test
    public void testCandidatesRandom(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath4);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);

        // builds a TreeGenerator object and generates a tree for "hired"
        this.testGenerator4 = new TreeGenerator();
        this.testGenerator4.generateTree(training, this.targetAttribute4);

        // makes a new (partial) Row representing the sam from the example
        Row sam = new Row("test row (sam)");
        sam.setAttributeValue("gender", "Non-binary");
        sam.setAttributeValue("leadershipExperience", "TRUE");
        sam.setAttributeValue("lastPositionDuration", "1-2");
        sam.setAttributeValue("numWorkExperiences", "<2");
        sam.setAttributeValue("programmingLanguages", "4-3");
        sam.setAttributeValue("gpa", "3.5-4.0");
        sam.setAttributeValue("location", "nonlocal");
        //return classification of sam under new decision tree
        Assert.assertEquals("FALSE", this.testGenerator4.getDecision(sam));

        // makes a new (partial) Row representing the Jake from the example
        Row jake = new Row("test row (jake)");
        jake.setAttributeValue("gender", "Male");
        jake.setAttributeValue("leadershipExperience", "True");
        jake.setAttributeValue("lastPositionDuration", "3+");
        jake.setAttributeValue("numWorkExperiences", "2");
        jake.setAttributeValue("programmingLanguages", "3-4");
        jake.setAttributeValue("gpa", "3.5-4.0");
        jake.setAttributeValue("location", "local");
        //return classification of jake under new decision tree
        Assert.assertEquals("FALSE", this.testGenerator4.getDecision(jake));

    }

    /**
     * Tests candidates dataset with ascending alphabetical node order
     */
    @Test
    public void testCandidatesAscAlpha(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath4);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        // builds a TreeGenerator object and generates a tree for "hired"
        this.testGenerator4 = new TreeGenerator();
        this.testGenerator4.generateTree(training, this.targetAttribute4);

        // makes a new (partial) Row representing the anna from the example
        Row anna = new Row("test row (anna)");
        anna.setAttributeValue("gender", "Female");
        anna.setAttributeValue("leadershipExperience", "TRUE");
        anna.setAttributeValue("lastPositionDuration", "1-2");
        anna.setAttributeValue("numWorkExperiences", "<2");
        anna.setAttributeValue("programmingLanguages", "4-3");
        anna.setAttributeValue("gpa", "3.5-4.0");
        anna.setAttributeValue("location", "nonlocal");
        //return classification of anna under new decision tree
        Assert.assertEquals("FALSE", this.testGenerator4.getDecision(anna));

        // makes a new (partial) Row representing the kyle from the example
        Row kyle = new Row("test row (kyle)");
        kyle.setAttributeValue("gender", "Male");
        kyle.setAttributeValue("leadershipExperience", "False");
        kyle.setAttributeValue("lastPositionDuration", "1-2");
        kyle.setAttributeValue("numWorkExperiences", "<1");
        kyle.setAttributeValue("programmingLanguages", "1-2");
        kyle.setAttributeValue("gpa", "3.5-4.0");
        kyle.setAttributeValue("location", "local");
        //return classification of kyle under new decision tree
        Assert.assertEquals("FALSE", this.testGenerator4.getDecision(kyle));



    }

    /**
     * Tests candidates dataset with descending alphabetical node order
     */
    @Test
    public void testCandidatesDescAlpha(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath4);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.DESCENDING_ALPHABETICAL);

        // builds a TreeGenerator object and generates a tree for "hired"
        this.testGenerator4 = new TreeGenerator();
        this.testGenerator4.generateTree(training, this.targetAttribute4);

        // makes a new (partial) Row representing the tara from the example
        Row tara = new Row("test row (tara)");
        tara.setAttributeValue("gender", "Female");
        tara.setAttributeValue("leadershipExperience", "FALSE");
        tara.setAttributeValue("lastPositionDuration", "<1");
        tara.setAttributeValue("numWorkExperiences", "0");
        tara.setAttributeValue("programmingLanguages", "0");
        tara.setAttributeValue("gpa", "3.5-4.0");
        tara.setAttributeValue("location", "local");
        //return classification of tara under new decision tree
        Assert.assertEquals("FALSE", this.testGenerator4.getDecision(tara));

        // makes a new (partial) Row representing the evie from the example
        Row evie = new Row("test row (evie)");
        evie.setAttributeValue("gender", "Female");
        evie.setAttributeValue("leadershipExperience", "FALSE");
        evie.setAttributeValue("lastPositionDuration", "<1");
        evie.setAttributeValue("numWorkExperiences", "0");
        evie.setAttributeValue("programmingLanguages", "5+");
        evie.setAttributeValue("gpa", "3.0-3.4");
        evie.setAttributeValue("location", "nonlocal");
        //return classification of evie under new decision tree
        Assert.assertEquals("TRUE", this.testGenerator4.getDecision(evie));

    }

    @Test
    public void testSongsRandom(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath6);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);

        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator6 = new TreeGenerator();
        this.testGenerator6.generateTree(training, this.targetAttribute6);

        // makes a new (partial) Row representing the song1 from the example
        Row song1 = new Row("test row (song1)");
        song1.setAttributeValue("topGenre", "dance rock");
        song1.setAttributeValue("isHighEnergy", "FALSE");
        song1.setAttributeValue("isDanceable", "TRUE");
        song1.setAttributeValue("isLoud", "FALSE");
        song1.setAttributeValue("isLively", "TRUE");
        song1.setAttributeValue("isHighValence", "FALSE");
        song1.setAttributeValue("isAcoustic", "FALSE");
        song1.setAttributeValue("isSpeechy", "FALSE");
        //return classification of song under new decision tree
        Assert.assertEquals("TRUE", this.testGenerator6.getDecision(song1));

        // makes a new (partial) Row representing the song2 from the example
        Row song2 = new Row("test row (song2)");
        song2.setAttributeValue("topGenre", "classical");
        song2.setAttributeValue("isHighEnergy", "FALSE");
        song2.setAttributeValue("isDanceable", "FALSE");
        song2.setAttributeValue("isLoud", "FALSE");
        song2.setAttributeValue("isLively", "FALSE");
        song2.setAttributeValue("isHighValence", "FALSE");
        song2.setAttributeValue("isAcoustic", "FALSE");
        song2.setAttributeValue("isSpeechy", "FALSE");
        //return classification of song under new decision tree
        Assert.assertEquals("TRUE", this.testGenerator6.getDecision(song2));


    }

    /**
     * Tests all-fruits dataset to make sure handles case where only one foodType
     */
    @Test
    public void testFruitAscAlpha(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator4 = new TreeGenerator();
        this.testGenerator4.generateTree(training, this.targetAttribute);

        // makes a new (partial) Row representing the grape from the example
        Row grape = new Row("test row (grape)");
        grape.setAttributeValue("color", "purple");
        grape.setAttributeValue("highProtein", "false");
        grape.setAttributeValue("calories", "low");
        //return classification of grape under new decision tree
        Assert.assertEquals("vegetable", this.testGenerator4.getDecision(grape));

        // makes a new (partial) Row representing the redPepper from the example
        Row redPepper = new Row("test row (redPepper)");
        redPepper.setAttributeValue("color", "red");
        redPepper.setAttributeValue("highProtein", "false");
        redPepper.setAttributeValue("calories", "low");
        //return classification of redPepper under new decision tree
        Assert.assertEquals("vegetable", this.testGenerator4.getDecision(redPepper));


    }

    @Test
    public void testFruitDescAlpha(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.DESCENDING_ALPHABETICAL);

        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
        this.testGenerator.generateTree(training, this.targetAttribute);

        // makes a new (partial) Row representing the goldenApple from the example
        Row goldenApple = new Row("test row (goldenApple)");
        goldenApple.setAttributeValue("color", "yellow");
        goldenApple.setAttributeValue("highProtein", "true");
        goldenApple.setAttributeValue("calories", "medium");
        //return classification of goldenApple under new decision tree
        Assert.assertEquals("fruit", this.testGenerator.getDecision(goldenApple));

        // makes a new (partial) Row representing the pumpkin from the example
        Row pumpkin = new Row("test row (pumpkin)");
        pumpkin.setAttributeValue("color", "orange");
        pumpkin.setAttributeValue("highProtein", "medium");
        pumpkin.setAttributeValue("calories", "high");
        //return classification of pumpkin under new decision tree
        Assert.assertEquals("vegetable", this.testGenerator.getDecision(pumpkin));


    }

    @Test
    public void testAllFruitsRandom(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath9);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.RANDOM);

        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator9 = new TreeGenerator();
        this.testGenerator9.generateTree(training, this.targetAttribute9);

        // makes a new (partial) Row representing the sweetPotato from the example
        Row sweetPotato = new Row("test row (sweetPotato)");
        sweetPotato.setAttributeValue("color", "orange");
        sweetPotato.setAttributeValue("highProtein", "true");
        sweetPotato.setAttributeValue("calories", "high");
        //return classification of sweetPotato under new decision tree
        Assert.assertEquals("fruit", this.testGenerator9.getDecision(sweetPotato));

        // makes a new (partial) Row representing the squash from the example
        Row squash = new Row("test row (squash)");
        squash.setAttributeValue("color", "white");
        squash.setAttributeValue("highProtein", "true");
        squash.setAttributeValue("calories", "medium");
        //return classification of squash under new decision tree
        Assert.assertEquals("fruit", this.testGenerator9.getDecision(squash));


    }

}
