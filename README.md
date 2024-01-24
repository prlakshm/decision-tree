# Decision Tree 

## Project Details:

**Project name:** Decision Tree

**Team members:** Shruti Panse and Pranavi Lakshminarayanan 

**Project Description:** We created a decision tree machine learning algorithm that either classifies objects or 
predicts outcomes for objects. First, we train the model using a training dataset (represented as a csv file), then run 
our decision tree model on new data to classify the object or predict an outcome for the object based on attribute 
information about the object (i.e. if the candidate will get hired, if the object is a fruit or vegetable, etc.). 

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

When we tested our tree generator algorithm on different datasets for accuracy using AccuracyTester.java. The 
percentages vary a little on every run, but by no more than 1%. We found that all datasets received an 
accuracy score of over 70% on testing data and over 90% on training data, except the popular song dataset. This is the
largest and most complicated dataset. The popular song dataset received an accuracy of 89.59%  for training data and 
72-73% for testing data. Ideally, we would want the score for the training dataset to be higher. However, we were not 
able to increase the accuracy of our decision tree algorithm to meet that threshold within the duration of this project. 
We acknowledge this and note that if there were to be future improvements made, this would be the place to start. 