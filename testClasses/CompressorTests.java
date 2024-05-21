package testClasses;

import SongOperations.Compressor;

import java.io.IOException;

public class CompressorTests {
    private static Compressor comp;

    public static void main(String[] args) throws IOException {
        startTests();
    }
    public static void startTests() throws IOException {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }
    private static void test1() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test1.txt");
        comp.decompressFile("res/compressorTestFiles/test1 compressed.txt");
    }
    private static void test2() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test2.txt");
        comp.decompressFile("res/compressorTestFiles/test2 compressed.txt");
    }
    private static void test3() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test3.txt");
        comp.decompressFile("res/compressorTestFiles/test3 compressed.txt");
    }
    private static void test4() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test4.txt");
        comp.decompressFile("res/compressorTestFiles/test4 compressed.txt");
    }
    private static void test5() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test5.txt");
        comp.decompressFile("res/compressorTestFiles/test5 compressed.txt");
    }
    private static void test6() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test6.txt");
        comp.decompressFile("res/compressorTestFiles/test6 compressed.txt");
    }
    private static void test7() throws IOException {
        comp = new Compressor();
        comp.compressFile("res/compressorTestFiles/test7.txt");
        comp.decompressFile("res/compressorTestFiles/test7 compressed.txt");
    }
}
