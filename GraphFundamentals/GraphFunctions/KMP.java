package GraphFundamentals.GraphFunctions;

import SongOperations.Compression.Compressor;
import SongOperations.Compression.Decompressor;

import java.io.*;
import java.util.Scanner;

public class KMP {
    public static int[] computePrefixFunction(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        prefixFunction[0] = 0;
        int k = 0;
        for (int q = 1; q < pattern.length(); q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = prefixFunction[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(q)) {
                k++;
            }
            prefixFunction[q] = k;
        }
        return prefixFunction;
    }

    public static int kmpMatcher(File file, String pattern) throws FileNotFoundException {

        StringBuilder text = new StringBuilder();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            text.append(scanner.nextLine());
            text.append("\n");
        }
        int n = text.length();
        int m = pattern.length();
        int[] prefixFunction = computePrefixFunction(pattern);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = prefixFunction[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }
            if (q == m) {
                return i - m + 1;
            }
        }
        return -1;
    }
    public static void kmpReplace(File file, String pattern, String replacement) throws IOException {

        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            text.append(scanner.nextLine());
            text.append("\n");
        }
        int n = text.length();
        int m = pattern.length();
        int[] prefixFunction = computePrefixFunction(pattern);
        int q = 0;
        for (int i = 0; i < n; i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = prefixFunction[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }
            if (q == m) {
                text.replace(i - m + 1, i + 1, replacement);
                q = 0;
                n = text.length();
            }
        }
        BufferedWriter writer= new BufferedWriter(new FileWriter(file));
        writer.write(text.toString());
        writer.close();

        try {
            Compressor compressor = new Compressor();
            compressor.compressFile(file.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
