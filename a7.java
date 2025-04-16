//Beaufort Cipher
import java.util.Scanner;

public class a7 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter plaintext");
        String plain=sc.nextLine(); //input plaintext and key
        System.out.println("enter key");
        String key=sc.next();

        String cipher=beaufort(plain,key);
        System.out.println("Ciphertext= "+cipher);
        String decrypted=beaufort(cipher,key);
        System.out.println("Decrypted= "+decrypted);
    }

    static String generateKey(String text, String key) {
        StringBuilder fullKey = new StringBuilder();
        key = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                fullKey.append(key.charAt(j % key.length()));
                j++;
            } else {
                fullKey.append(text.charAt(i)); // Maintain non-letters
            }
        }
        return fullKey.toString();
    }

    static String beaufort(String text, String key) {
        StringBuilder result = new StringBuilder();
        String fullKey = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char t = text.charAt(i);
            char k = fullKey.charAt(i);

            if (Character.isUpperCase(t)) {
                int shift = (k - t + 26) % 26;
                result.append((char) ('A' + shift));
            } else if (Character.isLowerCase(t)) { //perform subtraction of letters
                int shift = (Character.toUpperCase(k) - Character.toUpperCase(t) + 26) % 26;
                result.append((char) ('a' + shift));
            } else {
                result.append(t);
            }
        }

        return result.toString();
    }
}
