//Myskowiski Cipher

import java.util.*;

public class a13 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key: ");
        String key = sc.next();
        String encrypted = myskowiski(text, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    static String myskowiski(String plaintext, String key) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();
        key = key.toUpperCase();
        int[] keyNum = assignKey(key);
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] grid = new char[rows][cols];

        // Fill grid with plaintext
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < plaintext.length()) {
                    grid[r][c] = plaintext.charAt(index++);
                } else {
                    grid[r][c] = 'X'; // Padding character
                }
            }
        }

        // Generate ciphertext
        StringBuilder ciphertext = new StringBuilder();
        Set<Integer> processedNum= new TreeSet<>();
        for (int num : keyNum) {
            processedNum.add(num);
        }

        for (int num : processedNum) {
            for (int c = 0; c < cols; c++) {
                if (keyNum[c] == num) {
                    for (int r = 0; r < rows; r++) {
                        ciphertext.append(grid[r][c]);
                    }
                }
            }
        }

        return ciphertext.toString();
    }
    static int[] assignKey(String key) {
        int length = key.length();
        int[] keyNum= new int[length];
        Character[] keyChars = new Character[length];
        for (int i = 0; i < length; i++) {
            keyChars[i] = key.charAt(i);
        }

        Character[] sortedKeyChars = keyChars.clone();
        Arrays.sort(sortedKeyChars);

        Map<Character, Integer> charToNum = new HashMap<>();
        int number = 1;
        for (Character ch : sortedKeyChars) {
            if (!charToNum.containsKey(ch)) {
                charToNum.put(ch, number++);
            }
        }

        for (int i = 0; i < length; i++) {
            keyNum[i] = charToNum.get(keyChars[i]);
        }

        return keyNum;
    }

    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.replaceAll("\\s+", "").toUpperCase();
        key = key.toUpperCase();
        int[] keyNum = assignKey(key);
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);
        char[][] grid = new char[rows][cols];

        // Determine the order of columns
        Map<Integer, List<Integer>> numberToColumns = new TreeMap<>();
        for (int i = 0; i < keyNum.length; i++) {
            numberToColumns.computeIfAbsent(keyNum[i], k -> new ArrayList<>()).add(i);
        }

        // Fill grid column-wise
        int index = 0;
        for (int num : numberToColumns.keySet()) {
            for (int c : numberToColumns.get(num)) {
                for (int r = 0; r < rows; r++) {
                    if (index < ciphertext.length()) {
                        grid[r][c] = ciphertext.charAt(index++);
                    } else {
                        grid[r][c] = 'X'; // Padding character
                    }
                }
            }
        }

        // Read plaintext row-wise
        StringBuilder plaintext = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                plaintext.append(grid[r][c]);
            }
        }

        return plaintext.toString();
    }
}
