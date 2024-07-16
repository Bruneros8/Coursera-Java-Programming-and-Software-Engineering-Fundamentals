import edu.duke.FileResource;

public class TestCaesarCipher {
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();

        int key = 18;
        CaesarCipher cc = new CaesarCipher(key);

        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted String:\n" + encrypted);

        String decrypted = cc.decrypt(encrypted);
        System.out.println("\nDecrypted String:\n" + decrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipher tester = new TestCaesarCipher();
        tester.simpleTests();
    }
}