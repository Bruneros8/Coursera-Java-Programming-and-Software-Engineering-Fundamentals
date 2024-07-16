import java.util.Scanner;

public class DebugingPart2 {
    public void findAbc(String input){
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            System.out.println("index before updating: " + index);
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);
            index = input.indexOf("abc", index + 4);
            System.out.println("index after updating: " + index);
        }
    }

    public void test(){
        //findAbc("abcd");
        findAbc("abcdabc");
    }

    public static void main(String[] args) {
        DebugingPart2 debuggingPart2 = new DebugingPart2();
        debuggingPart2.test();
    }
}