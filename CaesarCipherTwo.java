public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for (int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currentChar));

            if (idx != -1) {
                if (currentChar == Character.toUpperCase(currentChar)) {
                    char newChar;
                    if (i % 2 == 0) {
                        newChar = shiftedAlphabet1.charAt(idx);
                    } else {
                        newChar = shiftedAlphabet2.charAt(idx);
                    }
                    encrypted.setCharAt(i, newChar);
                } else {
                    char newChar;
                    if (i % 2 == 0) {
                        newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                    } else {
                        newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                    }
                    encrypted.setCharAt(i, newChar);
                }
            }
        }

        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
}