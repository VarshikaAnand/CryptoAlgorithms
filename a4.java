//Affine Cipher

import java.util.*;
public class a4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter plaintext");
        String plain=sc.nextLine(); //input plaintext
        System.out.println("Enter a and b");
        int a=sc.nextInt();
        int b=sc.nextInt();
        String cipher=affine(plain,a,b);
        String decrypted = decrypt(cipher, a, b);
        System.out.println("Ciphertext= "+cipher);
        System.out.println("Decrypted= "+decrypted);
    }
    static String affine(String plain,int a,int b){
        StringBuilder result = new StringBuilder();

        for (char c : plain.toCharArray()){
            if (Character.isUpperCase(c)){
                int x=c-'A';
                int encrypted = (a*x+b) % 26; //apply function
                result.append((char)('A' + encrypted));
            }
            else if (Character.isLowerCase(c)){
                int x = c - 'a';
                int encrypted = (a * x + b) % 26;
                result.append((char) ('a' + encrypted));
            }
            else{
                result.append(c);
            }
        }
        return result.toString();
    }
     // Decrypts text using the affine cipher
    static String decrypt(String cipher,int a,int b){
        StringBuilder result = new StringBuilder();
        int a_inv = modInverse(a, 26);
        for (char c : cipher.toCharArray()) {
            if (Character.isUpperCase(c)) {
                int y = c - 'A';
                int decrypted = (a_inv * (y - b + 26)) % 26;
                result.append((char) ('A' + decrypted));
            } else if (Character.isLowerCase(c)) {
                int y = c - 'a';
                int decrypted = (a_inv * (y - b + 26)) % 26;
                result.append((char) ('a' + decrypted));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        throw new IllegalArgumentException("No modular inverse for a = " +a+" under mod " +m);
    }
}
