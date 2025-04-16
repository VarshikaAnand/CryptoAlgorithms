//Caesar Cipher
import java.util.*;
public class a1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String plain=sc.nextLine(); //input plaintext and offset
        int offset=sc.nextInt();
        String cipher=caesar(plain,offset);
        System.out.println("Ciphertext= "+cipher);

    }
    static String caesar(String plain,int offset){
        StringBuilder result = new StringBuilder();
        for (char c :plain.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) (((c - 'A' +offset) % 26) + 'A')); //apply shift
            } else if (Character.isLowerCase(c)) {
                result.append((char) (((c - 'a' +offset) % 26) + 'a'));
            } else {
                result.append(c); // Leave symbols, numbers, etc.
            }
        }
        return result.toString();
    }
}
