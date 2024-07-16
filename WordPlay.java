public class WordPlay {
    public static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    public static String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (isVowel(c)) {
                result.append(ch);
            }
            else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char currentChar = phrase.charAt(i);
            if (Character.toLowerCase(currentChar) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    result.append('*');
                }
                else {
                    result.append('+');
                }
            }
            else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        // Testing isVowel method
        System.out.println(isVowel('F')); // false
        System.out.println(isVowel('a')); // true

        // Testing replaceVowels method
        System.out.println(replaceVowels("Hello World", '*')); // H*ll* W*rld

        // Testing emphasize method
        System.out.println(emphasize("dna ctgaaactga", 'a')); // dn* ctg+*+ctg+
        System.out.println(emphasize("Mary Bella Abracadabra", 'a')); // M+ry Bell+ +br*c*d*br+
    }
}