import edu.duke.FileResource;

public class CaesarBreaker {

    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher(26 - key);
        return cc.encrypt(encrypted);
    }

    public String halfOfString(String message, int start) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            result.append(message.charAt(i));
        }
        return result.toString();
    }

    public int getKey(String s) {
        int[] counts = countLetters(s);
        int maxIndex = maxIndex(counts);
        int key = maxIndex - 4; // Assuming 'e' is the most frequent letter (index 4)
        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }
        return key;
    }

    public int[] countLetters(String s) {
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                int idx = Character.toUpperCase(ch) - 'A';
                counts[idx]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public String decryptTwoKeys(String encrypted) {
        StringBuilder decrypted = new StringBuilder(encrypted);

        for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            if (Character.isLetter(currentChar)) {
                int key;
                if (i % 2 == 0) {
                    key = getKey(halfOfString(encrypted, 0));
                } else {
                    key = getKey(halfOfString(encrypted, 1));
                }
                decrypted.setCharAt(i, decrypt(Character.toString(currentChar), key).charAt(0));
            }
        }

        return decrypted.toString();
    }

    public void decryptFile(String filename) {
        FileResource fr = new FileResource(filename);
        String encryptedMessage = fr.asString();
        String decryptedMessage = decryptTwoKeys(encryptedMessage);

        System.out.println("\nDecrypted File:\n" + decryptedMessage.substring(0, 150)); // Print first 150 characters
    }

    public int[] findTwoKeys(String filename) {
        FileResource fr = new FileResource(filename);
        String encryptedMessage = fr.asString();

        String evenChars = halfOfString(encryptedMessage, 0);
        String oddChars = halfOfString(encryptedMessage, 1);

        int key1 = getKey(evenChars);
        int key2 = getKey(oddChars);

        return new int[]{key1, key2};
    }

    public static void main(String[] args) {
        CaesarBreaker breaker = new CaesarBreaker();

        // Question 6
        String encryptedMessage6 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key6 = 14;
        String decryptedMessage6 = breaker.decrypt(encryptedMessage6, key6);
        System.out.println("\nDecrypted Message 6:\n" + decryptedMessage6);

        // Question 7
        String encryptedMessage7 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        int[] keys7 = breaker.findTwoKeys("mysteryTwoKeysQuiz.txt");
        String decryptedMessage7 = new CaesarCipherTwo(keys7[0], keys7[1]).decrypt(encryptedMessage7);
        System.out.println("\nDecrypted Message 7:\n" + decryptedMessage7);
    }
}