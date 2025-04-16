//n-grams

import java.util.*;

public class a9 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter value of N for n-gram: ");
        int n = sc.nextInt();
         List<String> ngrams = generate(text, n);
         System.out.println("\nGenerated " + n + "-grams:");
        for (String gram : ngrams) {
            System.out.println(gram);
        }
    }
    static List<String> generate(String text,int n){
        List<String> ngrams = new ArrayList<>();
        String[] words = text.split("\\s+"); //split input text into array of words
        if (words.length < n){
            return ngrams;
        }
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j > 0) sb.append(" ");
                sb.append(words[i + j]);
            }
            ngrams.add(sb.toString());
        }
        return ngrams;
    }
}
