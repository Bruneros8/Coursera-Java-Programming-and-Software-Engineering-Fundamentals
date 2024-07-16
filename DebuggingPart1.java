public class DebuggingPart1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);
            index = input.indexOf("abc", index + 4);
        }
    }

    public void test() {
        findAbc("abcd");
    }

    public static void main(String[] args) {
        DebuggingPart1 debuggingPart1 = new DebuggingPart1();
        debuggingPart1.test();
    }
}