import edu.duke.FileResource;
import edu.duke.StorageResource;

public class GeneAnalysis {

    public static void main(String[] args) {
        // Specify the correct absolute path to the file
        String filePath = "E:\\Coursera\\brca1line.fa";

        try {
            // Read the DNA sequence from the file
            String sequence = readSequenceFromFile(filePath).trim();

            // Extract genes from the DNA sequence
            StorageResource genes = getGenes(sequence);

            // Question 1: Number of genes
            int numGenes = genes.size();
            System.out.println("Number of genes: " + numGenes);

            // Question 2: Number of genes longer than 60
            int numGenesGt60 = countGenesLengthGreaterThan(genes, 60);
            System.out.println("Number of genes longer than 60: " + numGenesGt60);

            // Question 3: Number of genes with C-G-ratio > 0.35
            int numGenesHighCGRatio = countGenesWithHighCGRatio(genes, 0.35);
            System.out.println("Number of genes with C-G-ratio > 0.35: " + numGenesHighCGRatio);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Function to read DNA sequence from the file
    private static String readSequenceFromFile(String filePath) {
        FileResource fileResource = new FileResource(filePath);
        return fileResource.asString();
    }

    // Function to extract genes from the DNA sequence
    private static StorageResource getGenes(String dna) {
        StorageResource genes = new StorageResource();
        String[] geneArray = dna.split("NNNNNNNN");

        for (String gene : geneArray) {
            genes.add(gene);
        }

        return genes;
    }

    // Function to count genes longer than a given length
    private static int countGenesLengthGreaterThan(StorageResource genes, int length) {
        int count = 0;
        for (String gene : genes.data()) {
            if (gene.length() > length) {
                count++;
            }
        }
        return count;
    }

    // Function to count genes with C-G ratio greater than a given threshold
    private static int countGenesWithHighCGRatio(StorageResource genes, double threshold) {
        int count = 0;
        for (String gene : genes.data()) {
            if (calculateCGRatio(gene) > threshold) {
                count++;
            }
        }
        return count;
    }

    // Function to calculate C-G ratio
    private static double calculateCGRatio(String sequence) {
        int cgCount = 0;
        int validBaseCount = 0;

        for (char base : sequence.toCharArray()) {
            if (base == 'C' || base == 'G') {
                cgCount++;
            }
            if (base == 'A' || base == 'C' || base == 'G' || base == 'T') {
                validBaseCount++;
            }
        }

        // Corrected calculation by considering only valid bases (A, C, G, T)
        return (double) cgCount / validBaseCount;
    }
}