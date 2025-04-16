//Route cipher

import java.util.Scanner;

public class a12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String text = sc.nextLine();

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        String encrypted = route(text, rows, cols);
        String decrypted = decrypt(encrypted, rows, cols);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    static String route(String plaintext, int rows, int cols) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();

        // Pad plaintext to fit the grid
        int size = rows * cols;
        while (plaintext.length() < size) {
            plaintext += "X";
        }
        char[][] grid = new char[rows][cols];

        // Fill grid row-wise
        int idx = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = plaintext.charAt(idx++);
            }
        }
        // Read grid column-wise
        StringBuilder cipher = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                cipher.append(grid[r][c]);
            }
        }
        return cipher.toString();
    }
    static String decrypt(String ciphertext, int rows, int cols) {
        char[][] grid = new char[rows][cols];

        // Fill grid column-wise
        int idx = 0;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                grid[r][c] = ciphertext.charAt(idx++);
            }
        }

        // Read grid row-wise
        StringBuilder plaintext = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                plaintext.append(grid[r][c]);
            }
        }
        return plaintext.toString();
    }
}
