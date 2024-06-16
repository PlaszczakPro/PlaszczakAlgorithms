package GraphFundamentals.GraphFunctions;

import SongOperations.Compression.Compressor;
import SongOperations.Compression.Decompressor;

import java.io.*;
import java.util.Scanner;

public class KMP {
    public static int[] computePrefixFunction(String pattern) {
        int[] prefixFunction = new int[pattern.length()];
        int lenght=0;
        int i=1;
        prefixFunction[0] = 0;
        int k = 0;
        while(i<pattern.length()){
            if(pattern.charAt(i)==pattern.charAt(lenght)){
                lenght++;
                prefixFunction[i]=lenght;
                i++;
            }else{
                if(lenght!=0){
                    lenght=prefixFunction[lenght-1];
                }else{
                    prefixFunction[i]=lenght;
                    i++;
                }
            }
        }
        return prefixFunction;
    }
    public static void kmpReplace(File file, String pattern, String replacement) throws IOException {
        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            text.append(scanner.nextLine());
            text.append("\n");
        }
        int[] prefixFunction = computePrefixFunction(pattern);
        int m=pattern.length();
        int n=text.length();
        int j=0;
        int i=0;
        while ((n-i)>=(m-j)){
            if(pattern.charAt(j)==text.charAt(i)){
                i++;
                j++;
            }
            if(j==m){
                text.replace(i-j,i,replacement);
                n=text.length();
                i=i-m+j+replacement.length();
                j=prefixFunction[j-1];
            }
            else if(i<n && pattern.charAt(j)!=text.charAt(i)){
                if(j!=0){
                    j=prefixFunction[j-1];
                }
                else{
                    i++;
                }
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