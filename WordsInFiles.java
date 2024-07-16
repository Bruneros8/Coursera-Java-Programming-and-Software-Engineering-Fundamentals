import edu.duke.*;
import java.io.File;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    public WordsInFiles() {
        wordFileMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fileResource = new FileResource(f);
        for (String word : fileResource.words()) {
            if (!wordFileMap.containsKey(word)) {
                wordFileMap.put(word, new ArrayList<>());
            }
            ArrayList<String> filenames = wordFileMap.get(word);
            if (!filenames.contains(f.getName())) {
                filenames.add(f.getName());
            }
        }
    }

    public void buildWordFileMap() {
        wordFileMap.clear();
        DirectoryResource dirResource = new DirectoryResource();
        for (File file : dirResource.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    public int wordsInFilesCount(int numFiles) {
        int count = 0;
        for (String word : wordFileMap.keySet()) {
            if (wordFileMap.get(word).size() == numFiles) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<String> wordsInFiles(int numFiles) {
        ArrayList<String> result = new ArrayList<>();
        for (String word : wordFileMap.keySet()) {
            if (wordFileMap.get(word).size() == numFiles) {
                result.add(word);
            }
        }
        return result;
    }

    public boolean wordAppearsInFile(String word, String filename) {
        if (wordFileMap.containsKey(word)) {
            ArrayList<String> filenames = wordFileMap.get(word);
            return filenames != null && filenames.contains(filename);
        }
        return false;
    }

    public ArrayList<String> filesWhereWordAppears(String word) {
        return wordFileMap.containsKey(word) ? wordFileMap.get(word) : new ArrayList<>();
    }

    private static ArrayList<String> filesWithoutWord(WordsInFiles wordsInFiles, String word) {
        ArrayList<String> filesWithoutWord = new ArrayList<>();
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + currentDirectory);

        for (File file : new File(currentDirectory + "/data").listFiles()) {
            if (!wordsInFiles.wordAppearsInFile(word, file.getName())) {
                filesWithoutWord.add(file.getName());
            }
        }
        return filesWithoutWord;
    }

    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.buildWordFileMap();

        // Question 4
        int wordsInFiveFilesCount = wordsInFiles.wordsInFilesCount(5);
        System.out.println("Words that occur in five files: " + wordsInFiveFilesCount);

        // Question 5
        ArrayList<String> wordsInFourFiles = wordsInFiles.wordsInFiles(4);
        System.out.println("Words that appear in four of the five files: " + wordsInFourFiles.size());

        // Question 6
        String wordNotAppearing = "sad";
        ArrayList<String> filesWithoutWord = filesWithoutWord(wordsInFiles, wordNotAppearing);
        System.out.println("Files where the word '" + wordNotAppearing + "' does NOT appear: " + filesWithoutWord);

        // Question 7
        String wordToCheck = "red";
        ArrayList<String> filesWithWordRed = wordsInFiles.filesWhereWordAppears(wordToCheck);
        System.out.println("Files where the word '" + wordToCheck + "' appears: " + filesWithWordRed);
    }
}