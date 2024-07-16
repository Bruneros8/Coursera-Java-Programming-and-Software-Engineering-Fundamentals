import edu.duke.URLResource;

public class GeneFinder {
    public String findSimpleGene(String dna) {
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            return "";
        }

        int stopCodon = dna.indexOf("TAA", startCodon + 3);
        if (stopCodon == -1) {
            return "";
        }

        if ((stopCodon - startCodon) % 3 == 0) {
            return dna.substring(startCodon, stopCodon + 3);
        } else {
            return "";
        }
    }

    public void testSimpleGene() {
        String[] testCases = {
                "ATCGATCGATCG",       // No ATG
                "ATGCTAGCTA",         // No TAA
                "ATCGATCGATCGTAA",    // Valid gene
                "ATCGATCGATCGTA",     // Not a multiple of 3
                "ATCGATCGATCGTAA",    // Valid gene
        };

        for (String dna : testCases) {
            System.out.println("DNA: " + dna);
            String gene = findSimpleGene(dna);
            if (!gene.isEmpty()) {
                System.out.println("Gene: " + gene);
            } else {
                System.out.println("No gene found");
            }
            System.out.println();
        }
    }

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int start = dna.indexOf(startCodon);
        if (start == -1) {
            return "";
        }

        int stop = dna.indexOf(stopCodon, start + 3);
        if (stop == -1) {
            return "";
        }

        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        } else {
            return "";
        }
    }

    public void testSimpleGeneReorganized() {
        String[] testCases = {
                "ATCGATCGATCG",       // No ATG
                "ATGCTAGCTA",         // No TAA
                "ATCGATCGATCGTAA",    // Valid gene
                "ATCGATCGATCGTA",     // Not a multiple of 3
                "ATCGATCGATCGTAA",    // Valid gene
        };

        for (String dna : testCases) {
            System.out.println("DNA: " + dna);
            String gene = findSimpleGene(dna, "ATG", "TAA");
            if (!gene.isEmpty()) {
                System.out.println("Gene: " + gene);
            } else {
                System.out.println("No gene found");
            }
            System.out.println();
        }
    }

    public boolean twoOccurrences(String stringa, String stringb) {
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence != -1) {
            int secondOccurrence = stringb.indexOf(stringa, firstOccurrence + 1);
            return secondOccurrence != -1;
        }
        return false;
    }

    public void testing() {
        String[] testCases = {
                "by", "A story by Abby Long",      // true
                "a", "banana",                     // true
                "atg", "ctgtatgta",                // false
        };

        for (int i = 0; i < testCases.length; i += 2) {
            String stringa = testCases[i];
            String stringb = testCases[i + 1];
            System.out.println("StringA: " + stringa);
            System.out.println("StringB: " + stringb);
            System.out.println("Result: " + twoOccurrences(stringa, stringb));
            System.out.println();
        }

        // Test lastPart method
        String result1 = lastPart("an", "banana");
        System.out.println("The part of the string after an in banana is " + result1);

        String result2 = lastPart("zoo", "forest");
        System.out.println("The part of the string after zoo in forest is " + result2);
    }

    public String lastPart(String stringa, String stringb) {
        int start = stringb.indexOf(stringa);
        if (start != -1) {
            return stringb.substring(start + stringa.length());
        }
        return stringb;
    }

    public void findYouTubeLinks() {
        URLResource urlResource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

        for (String word : urlResource.words()) {
            String lowerCaseWord = word.toLowerCase();
            if (lowerCaseWord.contains("youtube.com")) {
                int startQuote = word.lastIndexOf("\"", lowerCaseWord.indexOf("youtube.com"));
                int endQuote = word.indexOf("\"", lowerCaseWord.indexOf("youtube.com"));

                if (startQuote != -1 && endQuote != -1) {
                    String youTubeLink = word.substring(startQuote + 1, endQuote);
                    System.out.println(youTubeLink);
                }
            }
        }
    }

    public static void main(String[] args) {
        GeneFinder geneFinder = new GeneFinder();

        // Uncomment and call the methods as needed
        // geneFinder.testSimpleGene();
        // geneFinder.testSimpleGeneReorganized();
        // geneFinder.testing();
        // geneFinder.findYouTubeLinks();
    }
}