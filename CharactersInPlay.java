import edu.duke.FileResource;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    public void update(String person) {
        person = person.trim();
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            int count = counts.get(index);
            counts.set(index, count + 1);
        }
    }

    public void findAllCharacters() {
        characters.clear();
        counts.clear();

        FileResource resource = new FileResource(); // Assuming you have FileResource class for file input
        for (String line : resource.lines()) {
            if (line.contains(".")) {
                int periodIndex = line.indexOf(".");
                String person = line.substring(0, periodIndex).trim();
                update(person);
            }
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) > 1) {
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characters.size(); i++) {
            int count = counts.get(i);
            if (count >= num1 && count <= num2) {
                System.out.println(characters.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay characterCounter = new CharactersInPlay();
        characterCounter.tester();
        // Or use characterCounter.charactersWithNumParts(num1, num2) if you want to test that method
    }
}