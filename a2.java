//Atbash Cipher
import java.util.*;
public class a2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String plain=sc.nextLine(); //input plaintext
        String cipher=atbash(plain);
        System.out.println("Ciphertext= "+cipher);
    }
    static String atbash(String plain){
        StringBuilder result = new StringBuilder();

        for (char c:plain.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ('Z'-(c-'A')));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ('z'-(c-'a')));
            } else {
                result.append(c); // Non-letters stay unchanged
            }
        }

        return result.toString();
    }
}
