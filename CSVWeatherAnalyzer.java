import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Scanner;

public class CSVWeatherAnalyzer {

    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord hottestSoFar = null;

        for (CSVRecord currentRow : parser) {
            if (hottestSoFar == null) {
                hottestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double hottestTemp = Double.parseDouble(hottestSoFar.get("TemperatureF"));

                if (currentTemp > hottestTemp && currentTemp != -9999) {
                    hottestSoFar = currentRow;
                }
            }
        }
        return hottestSoFar;
    }

    public void testHottestInDay() {
        FileResource fr = new FileResource("E:/Coursera/nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord hottest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + hottest.get("TemperatureF") + " at " + hottest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord hottestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());

            if (hottestSoFar == null) {
                hottestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double hottestTemp = Double.parseDouble(hottestSoFar.get("TemperatureF"));

                if (currentTemp > hottestTemp) {
                    hottestSoFar = currentRow;
                }
            }
        }
        return hottestSoFar;
    }

    public void testHottestInManyDays() {
        CSVRecord hottest = hottestInManyDays();
        System.out.println("Hottest temperature was " + hottest.get("TemperatureF") + " at " + hottest.get("DateUTC"));
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;

        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));

                if (currentTemp < coldestTemp && currentTemp != -9999) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);

        System.out.println("Coldest temperature was " + coldest.get("TemperatureF") +
                " at " + coldest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        String coldestFile = "";

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());

            if (coldestSoFar == null || Double.parseDouble(currentRow.get("TemperatureF")) <
                    Double.parseDouble(coldestSoFar.get("TemperatureF"))) {
                coldestSoFar = currentRow;
                coldestFile = f.getName();
            }
        }
        return coldestFile;
    }

    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource("nc_weather/2013/" + coldestFile);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());

        System.out.println("Coldest day was in file " + coldestFile);
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;

        for (CSVRecord currentRow : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                String currentHumidityStr = currentRow.get("Humidity");
                String lowestHumidityStr = lowestSoFar.get("Humidity");

                if (!currentHumidityStr.equals("N/A") && !lowestHumidityStr.equals("N/A")) {
                    double currentHumidity = Double.parseDouble(currentHumidityStr);
                    double lowestHumidity = Double.parseDouble(lowestHumidityStr);

                    if (currentHumidity < lowestHumidity) {
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get("Humidity") +
                " at " + lowestHumidityRecord.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());

            if (lowestSoFar == null || Double.parseDouble(currentRow.get("Humidity")) <
                    Double.parseDouble(lowestSoFar.get("Humidity"))) {
                lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get("Humidity") +
                " at " + lowestHumidityRecord.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;

        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (temp != -9999) {
                sum += temp;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);

        System.out.println("Average temperature in file is " + averageTemperature);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;

        for (CSVRecord record : parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            double temp = Double.parseDouble(record.get("TemperatureF"));

            if (humidity >= value && temp != -9999) {
                sum += temp;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No temperatures with that humidity");
            return 0;
        }
        return sum / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int humidityValue = 80;
        double averageTemperature = averageTemperatureWithHighHumidityInFile(parser, humidityValue);

        if (averageTemperature != 0) {
            System.out.println("Average Temp when high Humidity is " + averageTemperature);
        }
    }

    public static void main(String[] args) {
        CSVWeatherAnalyzer analyzer = new CSVWeatherAnalyzer();

        // Run the base tests
        System.out.println("Base Tests:");
        System.out.println("1. Test Hottest Hour in File");
        System.out.println("2. Test Hottest Hour in Many Files");
        System.out.println("3. Test Coldest Hour in File");
        System.out.println("4. Test File With Coldest Temperature");
        System.out.println("5. Test Lowest Humidity in File");
        System.out.println("6. Test Lowest Humidity in Many Files");
        System.out.println("7. Test Average Temperature in File");
        System.out.println("8. Test Average Temperature with High Humidity in File");

        System.out.print("Enter your choice (1-8): ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                analyzer.testHottestInDay();
                break;
            case 2:
                analyzer.testHottestInManyDays();
                break;
            case 3:
                analyzer.testColdestHourInFile();
                break;
            case 4:
                analyzer.testFileWithColdestTemperature();
                break;
            case 5:
                analyzer.testLowestHumidityInFile();
                break;
            case 6:
                analyzer.testLowestHumidityInManyFiles();
                break;
            case 7:
                analyzer.testAverageTemperatureInFile();
                break;
            case 8:
                analyzer.testAverageTemperatureWithHighHumidityInFile();
                break;
            default:
                System.out.println("Invalid choice. Exiting program.");
        }
    }
}