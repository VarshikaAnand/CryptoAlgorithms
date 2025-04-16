//Gronsfeld Cipher

import java.util.Scanner;

public class a6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter plaintext");
        String plain=sc.nextLine(); //input plaintext
        System.out.println("enter numeric key");
        String key=sc.next();
        if(!key.matches("\\d+")){
            System.out.println("Invalid key");
        }
        else{
            String cipher=gronsfeld(plain,key);
            System.out.println("Ciphertext= "+cipher);
            String decrypted=decrypt(cipher,key);
            System.out.println("Decrypted= "+decrypted);
        }    
    }
    public static String gronsfeld(String plain, String key) {
        StringBuilder result = new StringBuilder();
        int keyLen = key.length();
        for (int i = 0, j = 0; i < plain.length(); i++) {
            char c = plain.charAt(i);

            if (Character.isUpperCase(c)) {
                int shift = key.charAt(j % keyLen) - '0'; // Convert key digit to int
                char enc = (char) ('A' + (c - 'A' + shift) % 26);  //shift within A-Z
                result.append(enc);
                j++;
            } else if (Character.isLowerCase(c)) {
                int shift = key.charAt(j % keyLen) - '0';
                char enc = (char) ('a' + (c - 'a' + shift) % 26);
                result.append(enc);
                j++;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static String decrypt(String cipher, String key) {
        StringBuilder result = new StringBuilder();
        int keyLen = key.length();

        for (int i = 0, j = 0; i < cipher.length(); i++) {
            char c = cipher.charAt(i);

            if (Character.isUpperCase(c)) {
                int shift = key.charAt(j % keyLen) - '0';
                char dec = (char) ('A' + (c - 'A' - shift + 26) % 26);  //avoids negative values before modulo 26
                result.append(dec);
                j++;
            } else if (Character.isLowerCase(c)) {
                int shift = key.charAt(j % keyLen) - '0';
                char dec = (char) ('a' + (c - 'a' - shift + 26) % 26);
                result.append(dec);
                j++;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
