//Rail fence Cipher
import java.util.*;

public class a11 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter no of rails: ");
        int n = sc.nextInt();
        String encrypted = railfence(text, n);
        String decrypted = decrypt(encrypted, n);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    static String railfence(String text, int rails) {
        if (rails <= 1) 
        return text;

        char[][] rail = new char[rails][text.length()];
        for (char[] row : rail) {
            Arrays.fill(row, '\n');  //initialize grid cells
        }
        boolean down = false;
        int row = 0;

        for (int i = 0; i < text.length(); i++) {
            rail[row][i] = text.charAt(i);

            if (row == 0 || row == rails - 1) down = !down; //When reaching the top or bottom rail, switch direction
            row += down ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (char[] r : rail) {
            for (char ch : r) {  //collect characters row-by-row
                if (ch != '\n') result.append(ch);
            }
        }
        return result.toString();
    }

    static String decrypt(String cipher, int rails) {
        if (rails <= 1) return cipher;

        char[][] rail = new char[rails][cipher.length()];
        for (char[] row : rail) {
            Arrays.fill(row, '\n');
        }

        // mark the zigzag path
        boolean down = false;
        int row = 0;

        for (int i = 0; i < cipher.length(); i++) {
            rail[row][i] = '*';

            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        // Fill the marks with characters from cipher
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Read the zigzag to reconstruct the message
        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;

        for (int i = 0; i < cipher.length(); i++) {
            result.append(rail[row][i]);

            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        return result.toString();
    }
}
