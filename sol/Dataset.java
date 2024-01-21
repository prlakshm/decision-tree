package sol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import src.AttributeSelection;
import src.IDataset;
import src.Row;

/**
 * A class representing a training dataset for the decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the IDataset interface!
public class Dataset implements IDataset {

    private AttributeSelection selectionType;
    private List<String> attributeList;
    private List<Row> dataObjects;

    /**
     * Constructor for a Dataset object
     * @param attributeList - a list of attributes
     * @param dataObjects -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes
     */
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {
        this.selectionType = attributeSelection;
        this.attributeList = new ArrayList<>(attributeList);
        this.dataObjects = new ArrayList<>(dataObjects);
    }

    /**
     * Gets list of attributes in the dataset
     *
     * @return a list of strings
     */
    public List<String> getAttributeList(){
        return this.attributeList;
    }

    /**
     * Gets list of data objects (row) in the dataset
     *
     * @return a list of Rows
     */
    public List<Row> getDataObjects(){
        return this.dataObjects;
    }

    /**
     * Returns the attribute selection type (alphabetical, reverse alphabetical, random) for this Dataset
     *
     * @return the attribute selection type
     */
    public AttributeSelection getSelectionType(){
        return this.selectionType;
    }

    /**
     * finds the size of the dataset (number of rows)
     *
     * @return the number of rows in the dataset
     */
    public int size(){
        return this.dataObjects.size();
    }


    /**
     * This method works to filter the rows based on a particular inputted attribute
     * @param attribute a String of a particular attribute
     * @param option a String of the attribute option the user wants to filter to get all the rows of a particular
     *               attribute
     * @return a List<Row> with a particular option for the inputted attribute.
     */
    public List<Row> rowFilter(String attribute, String option){
        List<Row> filteredRows = new ArrayList<>();
        for(Row r : this.dataObjects){
            if(r.getAttributeValue(attribute).equals(option)){
                filteredRows.add(r);
            }
        }
        return filteredRows;
    }

    /**
     * This method finds all the possible options for an attribute.
     * @param attribute A String representing the wanted attribute.
     * @return A List<String> of options for a given attribute.
     */
    public List<String> collectOptions(String attribute)
    {
        List<String> options = new ArrayList<>();
        for(Row r: this.dataObjects)
        {
            String attOption = r.getAttributeValue(attribute);
            if(!(options.contains(attOption))) {
                options.add(attOption);
            }
        }
        return options;
    };

    /**
     * This method finds the most popular option for the given attribute.
     * @param filteredRows A list of rows you want to find if have the same target attribute value
     * @param target A string of the target attribute
     * @return A string of the most popular option for the attribute
     */
    public String popularOption(List<Row> filteredRows, String target)
    {
        int max = 0;
        String popularOption = "";

        List<String> targetOptions = new ArrayList<>();
        targetOptions = this.collectOptions(target);

        for(String t : targetOptions)
        {
            int count = 0;
            for(Row r : filteredRows){
                if(r.getAttributeValue(target).equals(t)){
                    count++;
                }
            }
            if(count > max){
                max = count;
                popularOption = t;
            }
        }
        return popularOption;
    };


    /**
     * This method returns a boolean value that shows if the given input is a valid option in the dataset.
     * @param attribute A String representing the wanted attribute.
     * @param input A String, input that the user wants to find is valid in the dataset
     * @return a boolean value, true if the given input is a valid option in the dataset, false otherwise.
     */
    public boolean ifValidOption(String attribute, String input)
    {
        return this.collectOptions(attribute).contains(input);
    };

    /**
     * get last element of list
     * @param l list you want to get last element of
     * @return last element of list of string data type
     */
    public String getLast(List<String> l){
        return l.get(l.size() - 1);
    }

    /**
     * This method checks if all the rows in the list have the same target attribute value.
     * @param filteredRows list want to check if have same target attribute value.
     * @param target target attribute for decision tree
     * @return boolean, true if all values in row have the same last attribute, false otherwise.
     */
    public boolean checkTargetAttribute(List<Row> filteredRows, String target){
        String targetAttributeVal = filteredRows.get(0).getAttributeValue(target);
        for(Row r: filteredRows.subList(0,filteredRows.size())){
            if(!(r.getAttributeValue(target).equals(targetAttributeVal))){
                return false;
            }
        }
        return true;
    }


    /**
     * This method finds the attribute to split on based on the selection type order.
     * @return A String of the attribute next in tree based on selection type order.
     */
    public String getAttributeToSplitOn() {
        switch (this.selectionType) {
            case ASCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
            }
            case RANDOM -> {
                Random rand = new Random();
                int index = rand.nextInt(this.attributeList.size());
                return this.attributeList.get(index);
            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }
}
