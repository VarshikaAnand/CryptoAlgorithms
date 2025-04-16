//Vignere Cipher
import java.util.Scanner;

public class a5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter plaintext");
        String plain=sc.nextLine(); //input plaintext

        String key = "KEY";
        String cipher=vignere(plain,key);
        String decrypted = decrypt(cipher,key);
        System.out.println("Ciphertext= "+cipher);
        System.out.println("Decrypted= "+decrypted);
    }

    static String generateKey(String text,String key){
        StringBuilder result=new StringBuilder();
        key = key.toLowerCase();
        for (int i=0,j=0;i < text.length(); i++){
            char c=text.charAt(i);
            if(Character.isLetter(c)){
                result.append(key.charAt(j % key.length()));
                j++;
            }
            else{
                result.append(c);
            }
        }
        return result.toString();
    }
    public static String vignere(String plain, String key) {
        String fullKey = generateKey(plain, key);
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < plain.length(); i++) {
            char p = plain.charAt(i);
            char k = fullKey.charAt(i);

            if (Character.isUpperCase(p)) {
                int shift=(p-'A'+(k-'a')) % 26; //shift it forward corresponding to key
                encrypted.append((char)('A' + shift));
            } else if (Character.isLowerCase(p)) {
                int shift=(p-'a'+(k-'a')) % 26;
                encrypted.append((char) ('a' + shift));
            } else {
                encrypted.append(p);
            }
        }
        return encrypted.toString();
    }
    public static String decrypt(String cipher, String key) {
        String fullKey = generateKey(cipher, key);
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {
            char c = cipher.charAt(i);
            char k = fullKey.charAt(i);

            if (Character.isUpperCase(c)) {
                int shift=(c-'A'-(k-'a')+26)%26; //subtract the key shift
                decrypted.append((char)('A' + shift));
            } else if (Character.isLowerCase(c)) {
                int shift=(c-'a'-(k-'a')+26)%26;
                decrypted.append((char)('a'+ shift));
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }
}
