//August Cipher
import java.util.*;
public class a3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String plain=sc.nextLine(); //input plaintext
        String cipher=august(plain);
        System.out.println("Ciphertext= "+cipher);
    }
    static String august(String plain){
        StringBuilder result = new StringBuilder();
        for (char c :plain.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char)(((c - 'A'+1)%26)+'A')); //apply shift
            } else if (Character.isLowerCase(c)) {
                result.append((char)(((c - 'a'+1)%26)+'a'));
            } else {
                result.append(c); // Leave symbols, numbers, etc.
            }
        }
        return result.toString();
    }
}
