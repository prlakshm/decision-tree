package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class representing a single row of the training dataset.
 */
public class Row {

    private Map<String, String> attributeValues;
    private String displayName;
    private List<String> attributeAccesses;

    /**
     * First constructor for Row
     * @param displayName - the row's displayed name
     */
    public Row(String displayName) {
        this.displayName = displayName;
        this.attributeValues = new HashMap<>();
        this.attributeAccesses = new ArrayList<>();
    }

    /**
     * Second constructor for Row
     * @param valuesMap - a map from attributes to attribute values
     */
    public Row(Map<String, String> valuesMap) {
        this.attributeValues = new HashMap<>();
        this.attributeAccesses = new ArrayList<>();
        this.setAttributeValues(valuesMap);
    }

    /**
     * Method to obtain all attributes of a dataset.
     *
     * From the "attributeValues" hashmap, we get all keys (equivalent to column names
     * in the data table) as Strings, and the method returns them as a Set of Strings.
     *
     * @return a set of all attributes of a row
     */
    public Set<String> getAttributes() {
        return this.attributeValues.keySet();
    }

    /**
     * Method to get the value for the given attribute.
     *
     * This method gets the corresponding value of THIS row to the passed-in "attributeName",
     * which is one of the column names.
     * Remember, this class represents a SINGLE row, so we are able to look up one specific value for the
     * passed-in attributeName.
     *
     * @param attributeName - the attribute
     * @return the value for the attribute
     */
    public String getAttributeValue(String attributeName) {
        if (this.attributeValues.containsKey(attributeName)) {
            this.attributeAccesses.add(attributeName);
            return this.attributeValues.get(attributeName);
        } else {
            throw new RuntimeException("Attribute '" + attributeName
                    + "' did not exist in datum with name: " + this.displayName);
        }
    }

    /**
     * Method to set the value of an attribute in the row.
     *
     * We set the passed-in value as the value for attributeName in this current row.
     *
     * @param attributeName - the name of the attribute to set
     * @param value         - the value of the given attribute
     */
    public void setAttributeValue(String attributeName, String value) {
        this.attributeValues.put(attributeName, value);
    }


    /**
     * Method to set the values of multiple attributes
     *
     * @param valueMap - a map from attributes to values
     */
    protected void setAttributeValues(Map<String, String> valueMap) {
        this.attributeValues.putAll(valueMap);
    }

    /**
     * Gets the access order of the attributes by removing contiguous duplicate
     * access calls. This can be used to determine the order on which
     * attributes were split to create a single path from root to leaf of a tree.
     * For example, if attributeAccesses were [color, color, color, highFiber, lowCarb, lowCarb],
     * this method would output [color, highFiber, lowCarb]. There may be duplicates
     * in the attributeAccesses list because in their Node class, students
     * may have a for loop comparing the datum value of an attribute with each
     * of the edge values, so the attribute value may be retrieved multiple times.
     *
     * @return the attribute access order
     */
    protected List<String> getAccessOrder() {
        List<String> accessOrder = new ArrayList<>();
        String current = null;
        for (String attribute : this.attributeAccesses) {
            if (!attribute.equals(current)) {
                accessOrder.add(attribute);
                current = attribute;
            }
        }
        return accessOrder;
    }

    /**
     * Clears access order when needed
     */
    protected void clearAccessOrder() {
        this.attributeAccesses = new ArrayList<>();
    }

    /**
     * An equals method for row objects
     *
     * @param o - an object being compared to the current row object
     * @return a boolean representing whether two rows are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Row row = (Row) o;
        // If displayNames are both null then we only consider attr vals
        if (this.displayName == null || row.displayName == null){ // this clause to check null name cases.
            return this.attributeValues.equals(row.attributeValues) &&
                    this.displayName == null && row.displayName == null;
        } else {
            return this.attributeValues.equals(row.attributeValues) &&
                    this.displayName.equals(row.displayName);
        }
    }
}
