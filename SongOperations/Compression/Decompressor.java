package SongOperations.Compression;
import java.io.*;
import java.util.*;

public class Decompressor {
    private HuffmanTreeNode huffmanTreeRoot;

    public void decompressFile(String fileName) throws IOException {

        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        BitSet compressedText = new BitSet();
        int compressedTextLength = 0;
        int headerLength = 0;
        byte[] headerBytes = new byte[0];

        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            compressedTextLength = dis.readInt();
            headerLength = dis.readInt();
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
        huffmanTreeRoot = buildHuffmanTree(headerInfo);

        StringBuilder bitString = bitSetToSb(compressedText, compressedTextLength);
        String decompressedText = decompressSb(bitString);
        String newFileName = fileName.replace("compressed", "decompressed");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            writer.write(decompressedText);
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
        //Zabezpieczenie przed przypadkiem gdzie dekompresowany tekst to ciag, ktorego wszystkie elementy to ten sam znak
        if ( huffmanTreeRoot.isLeaf()) {
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

            if (currentNode.isLeaf())
            {
                return currentNode.getKey();
            }
            else if (sbToDecompress.charAt(0) == '0')
            {
                currentNode = currentNode.getLeftChild();
            }
            else if (sbToDecompress.charAt(0) == '1')
            {
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
}