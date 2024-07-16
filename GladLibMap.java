import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedCategories;

    public GladLibMap() {
        myMap = new HashMap<>();
        initializeMap();
        usedCategories = new ArrayList<>();
    }

    private void initializeMap() {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "time", "verb", "fruit"};
        for (String category : categories) {
            ArrayList<String> words = readIt("data/" + category + ".txt");
            myMap.put(category, words);
        }
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> words = new ArrayList<>();
        FileResource fr = new FileResource(source);
        for (String word : fr.words()) {
            words.add(word);
        }
        return words;
    }

    private String randomFrom(ArrayList<String> source) {
        int index = (int) (Math.random() * source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (!usedCategories.contains(label)) {
            usedCategories.add(label);
            return randomFrom(myMap.get(label));
        } else {
            return randomFrom(myMap.get(label));
        }
    }

    private String processWord(String word) {
        int index = word.indexOf("<");
        int lastIndex = word.indexOf(">", index);
        if (index == -1 || lastIndex == -1) {
            return word;
        }
        String prefix = word.substring(0, index);
        String suffix = word.substring(lastIndex + 1);
        String label = word.substring(index + 1, lastIndex);
        String substitute = getSubstitute(label);
        return prefix + substitute + suffix;
    }

    private String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();
        FileResource fr = new FileResource(source);

        for (String word : fr.words()) {
            story.append(processWord(word)).append(" ");
        }

        return story.toString();
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String word : s.split("\\s+")) {
            if (charsWritten + word.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(word + " ");
            charsWritten += word.length() + 1;
        }
        System.out.println();
    }

    public int totalWordsInMap() {
        int total = 0;
        for (String category : myMap.keySet()) {
            total += myMap.get(category).size();
        }
        return total;
    }

    public int totalWordsConsidered() {
        int total = 0;
        for (String category : usedCategories) {
            total += myMap.get(category).size();
        }
        return total;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total words in map: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
    }

    public static void main(String[] args) {
        GladLibMap gladLibMap = new GladLibMap();
        gladLibMap.makeStory();
    }
}