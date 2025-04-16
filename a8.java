//Autoclave Cipher

import java.util.Scanner;

public class a8 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter plaintext");
        String plain=sc.nextLine(); //input plaintext and key
        System.out.println("enter key");
        String key=sc.next();

        String cipher=autoclave(plain,key);
        System.out.println("Ciphertext= "+cipher);
        String decrypted=decrypt(cipher,key);
        System.out.println("Decrypted= "+decrypted);
    }

    static String autoclave(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder fullKey = new StringBuilder(key);
        StringBuilder ciphertext = new StringBuilder();

        // Extend the key with the plaintext
        fullKey.append(plaintext);

        for (int i = 0; i < plaintext.length(); i++) {
            int p = plaintext.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';
            char c = (char) ((p + k) % 26 + 'A');
            ciphertext.append(c);
        }

        return ciphertext.toString();
    }

    static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder plaintext = new StringBuilder();
        StringBuilder fullKey = new StringBuilder(key);

        for (int i = 0; i < ciphertext.length(); i++) {
            int k = fullKey.charAt(i) - 'A';
            int c = ciphertext.charAt(i) - 'A';
            int p = (c - k + 26) % 26;
            char plainChar = (char) (p + 'A');
            plaintext.append(plainChar);
            fullKey.append(plainChar); // Expand key with recovered plaintext
        }

        return plaintext.toString();
    }
}
