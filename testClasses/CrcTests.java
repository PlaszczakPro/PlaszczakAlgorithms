package testClasses;

import SongOperations.Compression.Compressor;
import SongOperations.Compression.Decompressor;
import SongOperations.IntegrityAssurance.CyclicRedundancyCheck;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CrcTests {

    public static void main(String[] args) throws IOException {

        if (test1()) System.out.println("Test1 Passed");
        else System.out.println("Test1 Error");

        if (test2()) System.out.println("Test2 Passed");
        else System.out.println("Test2 Error");

        if (test3()) System.out.println("Test3 Passed");
        else System.out.println("Test3 Error");

        if (test4()) System.out.println("Test4 Passed");
        else System.out.println("Test4 Error");

        if (test5()) System.out.println("Test5 Passed");
        else System.out.println("Test5 Error");

        if (test6()) System.out.println("Test6 Passed");
        else System.out.println("Test6 Error");

        if (test7()) System.out.println("Test7 Passed");
        else System.out.println("Test7 Error");

    }

    private static boolean test1(){
        return CyclicRedundancyCheck.calculateCrc("123456789") == 0xCBF43926;
    }

    private static boolean test2(){
      return   CyclicRedundancyCheck.calculateCrc("asdyuafsdhjgfg12e ufydusaiDBvsa hksd gfsua") == 0x927314DD;
    }

    private static boolean test3(){
        return   CyclicRedundancyCheck.calculateCrc("Litwo! Ojczyzno moja! ty jestes jak zdrowie.") == 0xB68CCB46;
    }

    private static boolean test4() throws IOException {

        Compressor comp = new Compressor();
        Decompressor decomp = new Decompressor();

        comp.compressFile("PlaszczakAlgorithms/res/CrcTestFiles/test2.txt");
        int compressedTextCrc = decomp.decompressFile("PlaszczakAlgorithms/res/CrcTestFiles/test2_compressed.txt");


        StringBuilder uncompressedText = new StringBuilder();

        Scanner scanner = new Scanner(new File("PlaszczakAlgorithms/res/CrcTestFiles/test2.txt"));
        while (scanner.hasNextLine()) {
            uncompressedText.append(scanner.nextLine());
            uncompressedText.append("\n");
        }
        if (!uncompressedText.isEmpty()) uncompressedText.deleteCharAt(uncompressedText.length() - 1);

        return CyclicRedundancyCheck.checkIntegrity(compressedTextCrc , uncompressedText.toString());
    }

    private static boolean test5() throws IOException {

        Compressor comp = new Compressor();
        Decompressor decomp = new Decompressor();

        comp.compressFile("PlaszczakAlgorithms/res/CrcTestFiles/test1.txt");
        int compressedTextCrc = decomp.decompressFile("PlaszczakAlgorithms/res/CrcTestFiles/test1_compressed.txt");


        StringBuilder uncompressedText = new StringBuilder();

        Scanner scanner = new Scanner(new File("PlaszczakAlgorithms/res/CrcTestFiles/test1.txt"));
        while (scanner.hasNextLine()) {
            uncompressedText.append(scanner.nextLine());
            uncompressedText.append("\n");
        }
        if (!uncompressedText.isEmpty()) uncompressedText.deleteCharAt(uncompressedText.length() - 1);



        return CyclicRedundancyCheck.checkIntegrity(compressedTextCrc , uncompressedText.toString());
    }

    private static boolean test6() throws IOException {

        Compressor comp = new Compressor();
        Decompressor decomp = new Decompressor();

        comp.compressFile("PlaszczakAlgorithms/res/CrcTestFiles/test2.txt");

        StringBuilder uncompressedText = new StringBuilder();
        Scanner scanner = new Scanner(new File("PlaszczakAlgorithms/res/CrcTestFiles/test2.txt"));
        while (scanner.hasNextLine()) {
            uncompressedText.append(scanner.nextLine());
            uncompressedText.append("\n");
        }
        if (!uncompressedText.isEmpty()) uncompressedText.deleteCharAt(uncompressedText.length() - 1);



        return CyclicRedundancyCheck.checkIntegrity(decomp.getCrc("PlaszczakAlgorithms/res/CrcTestFiles/test2_compressed.txt") , uncompressedText.toString());
    }

    private static boolean test7() throws IOException {

        Compressor comp = new Compressor();
        Decompressor decomp = new Decompressor();

        comp.compressFile("PlaszczakAlgorithms/res/CrcTestFiles/test1.txt");
        int compressedTextCrc = decomp.decompressFile("PlaszczakAlgorithms/res/CrcTestFiles/test1_compressed.txt");


        StringBuilder uncompressedText = new StringBuilder();

        Scanner scanner = new Scanner(new File("PlaszczakAlgorithms/res/CrcTestFiles/test1_corrupted.txt"));
        while (scanner.hasNextLine()) {
            uncompressedText.append(scanner.nextLine());
            uncompressedText.append("\n");
        }
        if (!uncompressedText.isEmpty()) uncompressedText.deleteCharAt(uncompressedText.length() - 1);



        return !CyclicRedundancyCheck.checkIntegrity(compressedTextCrc , uncompressedText.toString());
    }

}


