import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int length = wordLength(word);
            if (length >= counts.length) {
                length = counts.length - 1;
            }
            counts[length]++;
        }
    }
    private int wordLength(String word) {
        int length = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                length++;
            }
        }
        return length;
    }
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(counts[i] + " words of length " + i);
            }
        }

        int mostCommonLength = indexOfMax(counts);
        System.out.println("Most common word length: " + mostCommonLength);
    }
    private int indexOfMax(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public static void main(String[] args) {
        WordLengths wordLengths = new WordLengths();
        wordLengths.testCountWordLengths();
    }
}