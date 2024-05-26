package testClasses;

import SongOperations.Compression.Compressor;
import SongOperations.Compression.Decompressor;

import java.io.IOException;

public class CompressorTests {
    private static Compressor comp;
    private static Decompressor decomp;

    public static void main(String[] args) throws IOException {
        startTests();
    }

    public static void startTests() throws IOException {
        // test1();
        //test2();
        // test3();
        // test4();
        //  test5();
        // test6();
        // test7();
        // test8();
        test8_1();
        test8_2();
    }
    private static void test1() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test1.txt");
        decomp.decompressFile("res/compressorTestFiles/test1 compressed.txt");
    }
    private static void test2() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test2.txt");
        decomp.decompressFile("res/compressorTestFiles/test2 compressed.txt");
    }
    private static void test3() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test3.txt");
        decomp.decompressFile("res/compressorTestFiles/test3 compressed.txt");
    }
    private static void test4() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test4.txt");
        decomp.decompressFile("res/compressorTestFiles/test4 compressed.txt");
    }
    private static void test5() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test5.txt");
        decomp.decompressFile("res/compressorTestFiles/test5 compressed.txt");
    }
    private static void test6() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test6.txt");
        decomp.decompressFile("res/compressorTestFiles/test6 compressed.txt");
    }
    private static void test7() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test7.txt");
        decomp.decompressFile("res/compressorTestFiles/test7 compressed.txt");
    }

    private static void test8() throws IOException {
        comp = new Compressor();
        decomp = new Decompressor();

        comp.compressFile("res/compressorTestFiles/test8.txt");
        decomp.decompressFile("res/compressorTestFiles/test8 compressed.txt");
    }

    private static void test8_1() throws IOException {
        comp = new Compressor();

        comp.compressFile("res/compressorTestFiles/test8.txt");
    }

    private static void test8_2() throws IOException {
        decomp = new Decompressor();

        decomp.decompressFile("res/compressorTestFiles/test8 compressed.txt");
    }


}
