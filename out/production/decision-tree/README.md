# Decision Tree 

## Project Details:

**Project name:** Decision Tree

**Team members:** Shruti Panse and Pranavi Lakshminarayanan 

**Project Description:** 

**Estimated Completion Time:** 25 hours

## File Structure:

### "Sol" directory:

AttributeNode: A Class which represents a Node in a decision tree. This Class implements the ITreeNode Interface.

Dataset: A Class which represents a training data set. This can be thought of as a table, where the column values are
attributes and each row represents an item in the data (rows will be represented by the Row class). This Class
implements the IDataclass Interface.

DecisionLeaf: A Class which represents a Leaf in a decision tree. This Class implements the ITreeNode interface.

TreeGenerator: This Class will be responsible for creating the Decision Tree given a Dataset. This Class implements
the ITreeGenerator Interface.

ValueEdge: A Class which represents an edge in the decision tree.

---

### "Src" directory

DecisionTreeCSVParser: This Class provides a static method which takes in a CSV of data and outputs a List of rows.

IDataset: This Interface contains methods for getting information about a dataset. 

ITreeGenerator: This Interface contains methods for generating a decision tree and getting the decision for a Row.

ITreeNode: This Interface contains a method to get a decision given a Row. 

Row: A Class which represents a row in a dataset.

---

### "Test" directory

BasicDatasetTest: A Class with a few simple tests on decision tree implementation.

DecisionTreeTest: Tests decision tree implemented algorithm on datasets of different sizes and attributes.

AccuracyTester: This Class provides methods for testing the accuracy of our Decision Tree on testing data.
We have marked some lines that users can edit to change which dataset this runs on.

## Errors and Bugs

When we tested our tree generator algorithm on different datasets for accuracy using AccuracyTester.java. All datasets
received an accuracy score of over 70% on testing data and over 90% on training data except the popular song dataset. 
The popular song dataset received an accuracy of 89.59%  for training data and 73.