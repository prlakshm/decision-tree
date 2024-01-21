package src;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with a method to parse a CSV file into a list of data objects.
 */
public class DecisionTreeCSVParser {

    private static final char DELIMITER = ',';

    /**
     * @param filepath the path to the CSV file relative to the project root
     *                 directory
     * @return - a list of data objects that correspond to the rows of the
     * provided csv file
     */
    public static List<Row> parse(String filepath) {
        CSVFormat format = CSVFormat.RFC4180.withDelimiter(DELIMITER).withHeader();
        CSVParser parser;

        try {
            parser = new CSVParser(new FileReader(filepath), format);
        } catch (IOException e) {
            throw new RuntimeException(
                "IOException when reading from file: " + filepath);
        }
        List<Row> data = new ArrayList<>();
        for (CSVRecord record : parser) {
            data.add(new Row(record.toMap()));
        }
        return data;
    }
}