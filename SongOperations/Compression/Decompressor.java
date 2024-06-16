package SongOperations.Compression;
import SongOperations.IntegrityAssurance.CyclicRedundancyCheck;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Decompressor {
    private HuffmanTreeNode huffmanTreeRoot;
    private String headerInfo;
    private int compressedTextLength;
    private int headerLength;
    private int fileCrc;

    public int decompressFile(String fileName) throws IOException {

        String newFileName = fileName.replace("compressed", "decompressed");

        BitSet compressedText = loadCompressedDataFromFile(fileName);

        if (compressedTextLength == 0) {
            saveDecompressedFile(newFileName , "");
            return 0;
        }

        huffmanTreeRoot = buildHuffmanTree(headerInfo);
        StringBuilder bitString = bitSetToSb(compressedText, compressedTextLength);
        String decompressedText = decompressSb(bitString);

        int calculatedCrc = CyclicRedundancyCheck.calculateCrc(decompressedText);
        if (fileCrc != calculatedCrc) {
            throw new IOException("Blad crc, plik moze byc uszkodzony");
        }

        saveDecompressedFile(newFileName, decompressedText);

        return fileCrc;
    }

    private BitSet loadCompressedDataFromFile(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        byte[] headerBytes;
        byte[] byteArray = new byte[0];

        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            fileCrc = dis.readInt();
            compressedTextLength = dis.readInt();
            headerLength = dis.readInt();
            headerBytes = new byte[headerLength];

            int bytesRead = dis.read(headerBytes);
            if (bytesRead < headerLength) {
                throw new EOFException("Problem z odczytaniem naglowka pliku");
            }

            headerInfo = new String(headerBytes);
            byteArray = dis.readAllBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitSet.valueOf(byteArray);
    }

    private void saveDecompressedFile(String fileName, String dataToSave){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dataToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HuffmanTreeNode buildHuffmanTree(String headerInfo) {
        Stack<HuffmanTreeNode> stack = new Stack<>();
        int headerInfoIt = 0;

        while (headerInfoIt < headerInfo.length()) {
            char curBit = headerInfo.charAt(headerInfoIt);
            if (curBit == '1') {
                headerInfoIt++;
                stack.push(new HuffmanTreeNode(new AbstractMap.SimpleEntry<>(headerInfo.charAt(headerInfoIt), 0)));
            } else if (curBit == '0') {
                if (stack.size() == 1) {
                    return stack.pop();
                }
                HuffmanTreeNode right = stack.pop();
                HuffmanTreeNode left = stack.pop();
                HuffmanTreeNode parent = new HuffmanTreeNode();
                parent.setRightChild(right);
                parent.setLeftChild(left);
                stack.push(parent);
            }
            headerInfoIt++;
        }
        return null;
    }

    public String decompressSb(StringBuilder sbToDecompress) {
        StringBuilder decompressedSb = new StringBuilder();
        if (huffmanTreeRoot.isLeaf()) {
            for (int it = 0; it < sbToDecompress.length(); it++) {
                decompressedSb.append(huffmanTreeRoot.getKey());
            }
            return decompressedSb.toString();
        }

        if (sbToDecompress.toString().equals("0")) {
            return huffmanTreeRoot.getKey().toString();
        }

        while (true) {
            if (sbToDecompress.isEmpty()) return decompressedSb.toString();
            decompressedSb.append(decompressChar(huffmanTreeRoot, sbToDecompress));
        }
    }

    private char decompressChar(HuffmanTreeNode currentNode, StringBuilder sbToDecompress) {
        while (true) {
            if (currentNode.isLeaf()) {
                return currentNode.getKey();
            } else if (sbToDecompress.charAt(0) == '0') {
                currentNode = currentNode.getLeftChild();
            } else if (sbToDecompress.charAt(0) == '1') {
                currentNode = currentNode.getRightChild();
            }
            sbToDecompress.deleteCharAt(0);
        }
    }

    private StringBuilder bitSetToSb(BitSet compressedText, int length) {
        StringBuilder compressedTextSb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (compressedText.get(i)) {
                compressedTextSb.append('1');
            } else {
                compressedTextSb.append('0');
            }
        }
        return compressedTextSb;
    }

    public int getCrc(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");
        int fileCrc = 0;

        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            fileCrc = dis.readInt();
        }

        return fileCrc;
    }
}
