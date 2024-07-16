import java.io.File;
import edu.duke.FileResource;
import java.util.HashSet;
import java.util.HashMap;

public class VigenereBreaker {
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxValidWords = 0;
        String bestDecryption = "";
        int bestKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int[] key = tryKeyLength(encrypted, keyLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int validWordsCount = countWords(decrypted, dictionary);
            if (validWordsCount > maxValidWords) {
                maxValidWords = validWordsCount;
                bestDecryption = decrypted;
                bestKeyLength = keyLength;
            }
        }
        return bestDecryption;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxValidWords = 0;
        String bestDecryption = "";
        String bestLanguage = "";
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decryption = breakForLanguage(encrypted, dictionary);
            int validWordsCount = countWords(decryption, dictionary);
            if (validWordsCount > maxValidWords) {
                maxValidWords = validWordsCount;
                bestDecryption = decryption;
                bestLanguage = language;
            }
        }
        System.out.println("Best language: " + bestLanguage);
        System.out.println("Best decryption:\n" + bestDecryption);
        // Additional functionality to answer the questions
        String firstLine = getFirstLine(bestDecryption);
        System.out.println("First line of the decrypted message: " + firstLine);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder result = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i] = cracker.getKey(slice);
        }
        return key;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFrequency = new HashMap<>();
        for (String word : dictionary) {
            for (char ch : word.toCharArray()) {
                charFrequency.put(ch, charFrequency.getOrDefault(ch, 0) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFrequency = 0;
        for (char ch : charFrequency.keySet()) {
            int frequency = charFrequency.get(ch);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostCommonChar = ch;
            }
        }
        return mostCommonChar;
    }

    public String breakVigenereAndReturnDecryptedMessage(int keyLength) {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] key = tryKeyLength(encrypted, keyLength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decryptedMessage = vc.decrypt(encrypted);
        // Print the decrypted message
        System.out.println("Decrypted Message:\n" + decryptedMessage);
        return decryptedMessage;
    }

    public String getLanguage(String decryptedMessage, HashMap<String, HashSet<String>> languages) {
        int maxValidWords = 0;
        String bestLanguage = "";
        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            int validWordsCount = countWords(decryptedMessage, dictionary);
            if (validWordsCount > maxValidWords) {
                maxValidWords = validWordsCount;
                bestLanguage = language;
            }
        }
        return bestLanguage;
    }

    public String getFirstLine(String decryptedMessage) {
        String[] lines = decryptedMessage.split("\\r?\\n");
        if (lines.length > 0) {
            return lines[0];
        }
        return "";
    }

    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        String decryptedMessage = vb.breakVigenereAndReturnDecryptedMessage(10); // Specify the desired key length
        // Additional functionality to answer the questions
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        // Load dictionaries
        languages.put("Danish", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/Danish.txt")));
        languages.put("Dutch", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/Dutch.txt")));
        languages.put("English", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/English.txt")));
        languages.put("French", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/French.txt")));
        languages.put("German", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/German.txt")));
        languages.put("Italian", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/Italian.txt")));
        languages.put("Portuguese", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/Portuguese.txt")));
        languages.put("Spanish", vb.readDictionary(new FileResource("E:/Coursera/dictionaries/Spanish.txt")));
        vb.breakForAllLanguages(decryptedMessage, languages);
    }
}