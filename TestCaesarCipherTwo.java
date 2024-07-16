import edu.duke.FileResource;

public class TestCaesarCipherTwo {

    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();

        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);

        String encrypted = ccTwo.encrypt(input);
        System.out.println("Encrypted String:\n" + encrypted);

        String decrypted = ccTwo.decrypt(encrypted);
        System.out.println("\nDecrypted String:\n" + decrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tester = new TestCaesarCipherTwo();
        tester.simpleTests();
    }
}