import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3);
            codonMap.put(codon, codonMap.getOrDefault(codon, 0) + 1);
        }
    }

    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = "";
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
    }

    public static void main(String[] args) {
        CodonCount codonCounter = new CodonCount();
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString().trim().toUpperCase();

        for (int i = 0; i < 3; i++) {
            codonCounter.buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + codonCounter.codonMap.size() + " unique codons");
            System.out.println("  and most common codon is " + codonCounter.getMostCommonCodon() + " with count " + codonCounter.codonMap.get(codonCounter.getMostCommonCodon()));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            codonCounter.printCodonCounts(1, 5);
            System.out.println();
        }
    }
}