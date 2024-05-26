package SongOperations.Compression;
import java.io.*;
import java.util.*;

public class Decompressor {

    private Map<Character, Integer> charsFrequencyMap;
    private HuffmanTreeNode huffmanTreeRoot;
    private Map<Character, String> codesMap;

    public Decompressor(){
        codesMap = new HashMap<>();
        charsFrequencyMap = new TreeMap<>();
    }

    public void decompressFile(String fileName) throws IOException {

        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        BitSet compressedText = new BitSet();
        int compressedTextLength = 0;
        int headerLength = 0;
        int originalLength = 0;
        byte[] headerBytes = new byte[0];

        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            compressedTextLength = dis.readInt();
            headerLength = dis.readInt();
            originalLength = dis.readInt();
            headerBytes = new byte[headerLength];
            dis.read(headerBytes);
            byte[] byteArray = dis.readAllBytes();
            compressedText = BitSet.valueOf(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(compressedTextLength == 0){
            return;
        }

        String headerInfo = new String(headerBytes);
        huffmanTreeRoot = rebuildHuffmanTree(headerInfo);


        StringBuilder bitString = bitSetToSb(compressedText, compressedTextLength);
        String decompressedText = decompressSb(bitString);
        String newFileName = fileName.replace("compressed", "decompressed");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            writer.write(decompressedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private HuffmanTreeNode rebuildHuffmanTree(String headerInfo) {
        Stack<HuffmanTreeNode> stack = new Stack<>();
        int i = 0;

        while (i < headerInfo.length()) {
            char c = headerInfo.charAt(i);
            if (c == '1') {
                char ch = headerInfo.charAt(++i);
                stack.push(new HuffmanTreeNode(new AbstractMap.SimpleEntry<>(ch, 0)));
            } else if (c == '0') {
                if (stack.size() == 1) {
                    return stack.pop();
                }
                HuffmanTreeNode right = stack.pop();
                HuffmanTreeNode left = stack.pop();
                HuffmanTreeNode parent = new HuffmanTreeNode();
                parent.rightChild = right;
                parent.leftChild = left;
                stack.push(parent);
            }
            i++;
        }

        return null;
    }
    public String decompressSb(StringBuilder sbToDecompress) {
        StringBuilder decompressedSb = new StringBuilder();
        //Zabezpieczenie przed przypadkiem gdzie dekompresowany tekst to ciag, ktorego wszystkie elementy to ten sam znak
        if (/*codesMap.size() == 1*/ huffmanTreeRoot.isLeaf) {
            for (int it = 0; it < sbToDecompress.length(); it++) {
                decompressedSb.append( huffmanTreeRoot.key);
            }
            return decompressedSb.toString();
        }

        if (sbToDecompress.toString().equals("0")) {
            return huffmanTreeRoot.key.toString();
        }

        while (true) {
            if (sbToDecompress.isEmpty()) return decompressedSb.toString();
            decompressedSb.append(decompressChar(huffmanTreeRoot, sbToDecompress));
        }
    }
    private char decompressChar(HuffmanTreeNode currentNode, StringBuilder sbToDecompress) {
        while (true) {

            if (currentNode.isLeaf)
            {
                return currentNode.key;
            }
            else if (sbToDecompress.charAt(0) == '0')
            {
                currentNode = currentNode.leftChild;
            }
            else if (sbToDecompress.charAt(0) == '1')
            {
                currentNode = currentNode.rightChild;
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
}