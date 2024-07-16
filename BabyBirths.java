import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {

    private String projectDirectory = "E:/Coursera/us_babynames/";

    public int getNumNamesByGenderAndYear(String gender, int year) {
        FileResource fr = new FileResource(projectDirectory + "us_babynames_by_year/yob" + year + ".csv");
        int numNames = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                numNames++;
            }
        }

        return numNames;
    }

    public int getRankByNameAndYear(String name, int year, String gender) {
        FileResource fr = new FileResource(projectDirectory + "us_babynames_by_year/yob" + year + ".csv");
        int rank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }

        return -1;
    }

    public String getNameByRankAndYear(int rank, int year, String gender) {
        FileResource fr = new FileResource(projectDirectory + "us_babynames_by_year/yob" + year + ".csv");
        int currentRank = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return rec.get(0);
                }
            }
        }

        return "NO NAME.";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRankByNameAndYear(name, year, gender);
        String newName = getNameByRankAndYear(rank, newYear, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender) {
        int highestRank = Integer.MAX_VALUE;
        int yearWithHighestRank = -1;

        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));

            int currentRank = getRankByNameAndYear(name, year, gender);

            if (currentRank != -1 && currentRank < highestRank) {
                highestRank = currentRank;
                yearWithHighestRank = year;
            }
        }

        return yearWithHighestRank;
    }

    public double getAverageRank(String name, String gender) {
        double totalRank = 0.0;
        int fileCount = 0;

        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));

            int currentRank = getRankByNameAndYear(name, year, gender);

            if (currentRank != -1) {
                totalRank += currentRank;
                fileCount++;
            }
        }

        if (fileCount == 0) {
            return -1.0;
        }

        return totalRank / fileCount;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource(projectDirectory + "us_babynames_by_year/yob" + year + ".csv");
        int totalBirthsRankedHigher = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && rec.get(0).equals(name)) {
                break;
            }

            if (rec.get(1).equals(gender)) {
                totalBirthsRankedHigher += Integer.parseInt(rec.get(2));
            }
        }

        return totalBirthsRankedHigher;
    }

    public static void main(String[] args) {
        BabyBirths bb = new BabyBirths();

        // Question 1
        System.out.println("Question 1: " + bb.getNumNamesByGenderAndYear("F", 1900));

        // Question 2
        System.out.println("Question 2: " + bb.getNumNamesByGenderAndYear("M", 1905));

        // Question 3
        System.out.println("Question 3: " + bb.getRankByNameAndYear("Emily", 1960, "F"));

        // Question 4
        System.out.println("Question 4: " + bb.getRankByNameAndYear("Frank", 1971, "M"));

        // Question 5
        System.out.println("Question 5: " + bb.getNameByRankAndYear(350, 1980, "F"));

        // Question 6
        System.out.println("Question 6: " + bb.getNameByRankAndYear(450, 1982, "M"));

        // Question 7
        bb.whatIsNameInYear("Susan", 1972, 2014, "F");

        // Question 8
        bb.whatIsNameInYear("Owen", 1974, 2014, "M");

        // Question 9
        System.out.println("Question 9: " + bb.yearOfHighestRank("Genevieve", "F"));

        // Question 10
        System.out.println("Question 10: " + bb.yearOfHighestRank("Mich", "M"));

        // Question 11
        System.out.println("Question 11: " + String.format("%.2f", bb.getAverageRank("Susan", "F")));

        // Question 12
        System.out.println("Question 12: " + String.format("%.2f", bb.getAverageRank("Robert", "M")));

        // Question 13
        System.out.println("Question 13: " + bb.getTotalBirthsRankedHigher(1990, "Emily", "F"));

        // Question 14
        System.out.println("Question 14: " + bb.getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}