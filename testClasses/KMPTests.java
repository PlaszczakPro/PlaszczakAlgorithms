package testClasses;

import GraphFundamentals.GraphFunctions.KMP;
import SongOperations.Compression.Decompressor;

import java.io.File;
import java.io.IOException;

public class KMPTests {
    public static void main(String[] args) throws IOException {
        Decompressor decompressor=new Decompressor();
        KMP.kmpReplace(new File("res/compressorTestFiles/test1.txt"), "kotek", "piesek");
        decompressor.decompressFile("res/compressorTestFiles/test1_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test2.txt"), "AB", "BA");
        decompressor.decompressFile("res/compressorTestFiles/test2_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test3.txt"), "A", "B");
        decompressor.decompressFile("res/compressorTestFiles/test3_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test4.txt"), "Since", "Lorem");
        decompressor.decompressFile("res/compressorTestFiles/test4_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test5.txt"), "AA", "CB");
        decompressor.decompressFile("res/compressorTestFiles/test5_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test6.txt"), "Slowo", "Word");
        decompressor.decompressFile("res/compressorTestFiles/test6_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test7.txt"), "Lorem", "Since");
        decompressor.decompressFile("res/compressorTestFiles/test7_compressed.txt");
        KMP.kmpReplace(new File("res/compressorTestFiles/test8.txt"), "Stone", "Sticks");
        decompressor.decompressFile("res/compressorTestFiles/test8_compressed.txt");
    }
}
