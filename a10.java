//Hill cipher

import java.util.*;
public class a10 {
    static int[][] key= {{3, 3}, {2, 5}};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter plaintext in only letters");
        String plain=sc.next(); 
        String cipher=hill(plain);
        System.out.println("Ciphertext= "+cipher);
        String decrypted=decrypt(cipher);
        System.out.println("Decrypted= "+decrypted);
    }

    static String hill(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        if (plaintext.length() % 2 != 0)
            plaintext += "X"; // Padding if not even

        StringBuilder cipher= new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] pair = {
                plaintext.charAt(i) - 'A',  //convert letters to numbers
                plaintext.charAt(i + 1) - 'A'
            };
            int[] encrypted = new int[2];
            for (int row = 0; row < 2; row++) {  //multiply with key matrix
                encrypted[row]=(key[row][0]*pair[0]+key[row][1]*pair[1])% 26;
            }

            cipher.append((char)(encrypted[0] + 'A'));
            cipher.append((char)(encrypted[1] + 'A'));
        }
        return cipher.toString();
    }
    static String decrypt(String ciphertext) {
        int[][] inverseKey = invertMatrix(key);

        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] pair = {
                ciphertext.charAt(i) - 'A', //convert letters to numbers
                ciphertext.charAt(i + 1) - 'A'
            };

            int[] decrypted = new int[2];
            for (int row = 0; row < 2; row++) {  //multiply 
                decrypted[row] = (inverseKey[row][0] * pair[0] + inverseKey[row][1] * pair[1]) % 26;
                if (decrypted[row] < 0) decrypted[row] += 26; // Normalize negatives
            }

            plaintext.append((char) (decrypted[0] + 'A'));
            plaintext.append((char) (decrypted[1] + 'A'));
        }
        return plaintext.toString();
    }
    static int[][] invertMatrix(int[][] m) {
        int det = (m[0][0] * m[1][1] - m[0][1] * m[1][0]) % 26;
        if (det < 0) det += 26;

        int detInv = modInverse(det, 26);
        if (detInv == -1) throw new RuntimeException("Matrix not invertible");

        int[][] inv = new int[2][2];
        inv[0][0] = m[1][1] * detInv % 26;
        inv[0][1] = -m[0][1] * detInv % 26;
        inv[1][0] = -m[1][0] * detInv % 26;
        inv[1][1] = m[0][0] * detInv % 26;

        // Normalize negative values
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                if (inv[i][j] < 0) inv[i][j] += 26;

        return inv;
    }
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return -1;
    }

}
