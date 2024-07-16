import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVParserAssignment {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // Part 2
        countryInfo(parser, "Germany");
        // Part 3
        listExportersTwoProducts(parser, "gold", "diamonds");
        // Part 4
        int numberOfExportersResult = numberOfExporters(parser, "gold");
        System.out.println("Number of exporters: " + numberOfExportersResult);
        // Part 5
        bigExporters(parser, "$999,999,999");
    }

    public void countryInfo(CSVParser parser, String country) {
        boolean found = false;
        for (CSVRecord record : parser) {
            String currentCountry = record.get("Country");
            if (currentCountry.equals(country)) {
                found = true;
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(currentCountry + ": " + exports + ": " + value);
                break;
            }
        }
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                count++;
                if (count == 3) {
                    String country = record.get("Country");
                    System.out.println("Third country exporting both " + exportItem1 + " and " + exportItem2 + ": " + country);
                    break;
                }
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount) {
        // Remove dollar sign and commas from the amount string
        amount = amount.replace("$", "").replace(",", "");
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)").replace("$", "").replace(",", "");
            // Compare numeric values
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + record.get("Value (dollars)"));
            }
        }
    }

    public String getNthExporter(CSVParser parser, int n, String exportItem1, String exportItem2) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                count++;
                if (count == n) {
                    return record.get("Country");
                }
            }
        }
        return "Not Found";
    }

    public int getNumberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public String getNthBigExporter(CSVParser parser, int n, String amount) {
        // Remove dollar sign and commas from the amount string
        amount = amount.replace("$", "").replace(",", "");
        int count = 0;

        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)").replace("$", "").replace(",", "");
            // Compare numeric values
            if (value.length() > amount.length()) {
                count++;
                if (count == n) {
                    return record.get("Country");
                }
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        CSVParserAssignment csvParserAssignment = new CSVParserAssignment();
        // Run your program on the file exportdata.csv
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        
        // Question 1: What is the name of the country that is listed as the second country that exports both cotton and flowers?
        String secondCountry = csvParserAssignment.getNthExporter(parser, 2, "cotton", "flowers");
        System.out.println("Second country exporting both cotton and flowers: " + secondCountry);

        // Question 2: How many countries export cocoa?
        parser = fr.getCSVParser(); // Reset parser
        int numberOfCocoaExporters = csvParserAssignment.getNumberOfExporters(parser, "cocoa");
        System.out.println("Number of countries exporting cocoa: " + numberOfCocoaExporters);

        // Question 3: What is the name of the third country (on the third line of the output) listed whose exports are valued at one trillion US dollars or more?
        parser = fr.getCSVParser(); // Reset parser
        String thirdTrillionDollarCountry = csvParserAssignment.getNthBigExporter(parser, 3, "$999,999,999,999");
        System.out.println("Third country with exports valued at one trillion or more: " + thirdTrillionDollarCountry);
    }
}