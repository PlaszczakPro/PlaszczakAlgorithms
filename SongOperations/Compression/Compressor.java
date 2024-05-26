package SongOperations.Compression;

import java.io.*;
import java.util.*;

public class Compressor {
    private Map<Character, Integer> charsFrequencyMap;
    private HuffmanTreeNode huffmanTreeRoot;
    private Map<Character, String> codesMap;
    private int compressedTextLength;

    public Compressor() {
        codesMap = new HashMap<>();
        charsFrequencyMap = new TreeMap<>();
    }

    public void compressFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        StringBuilder sbToCompress = new StringBuilder();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            sbToCompress.append(scanner.nextLine());
            sbToCompress.append("\n");
        }
        if (!sbToCompress.isEmpty()) sbToCompress.deleteCharAt(sbToCompress.length() - 1); // Aby zdekompresowany plik nie miał nowego wiersza na końcu


        BitSet compressedText = compressString(sbToCompress.toString());
        String newFileName = fileName.replace(".txt", " compressed.txt");

        StringBuilder headerInfo = new StringBuilder();

        if(compressedTextLength==0) headerInfo = new StringBuilder("0");
        else generateHeaderInfo(huffmanTreeRoot, headerInfo);

        headerInfo.append("0"); // 0 oznacza koniec headera i poczatek kodowanego pliku


        byte[] headerBytes = headerInfo.toString().getBytes();
        int headerLength = headerBytes.length;
        int originalLength = sbToCompress.length();


        try (FileOutputStream fos = new FileOutputStream(newFileName);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(compressedTextLength);
            dos.writeInt(headerLength);
            dos.writeInt(originalLength);
            dos.write(headerBytes);
            dos.write(compressedText.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateHeaderInfo(HuffmanTreeNode node, StringBuilder headerInfo) {
        if (node.isLeaf) {
            headerInfo.append("1").append(node.key);
        } else {
            if (node.leftChild != null) generateHeaderInfo(node.leftChild, headerInfo);
            if (node.rightChild != null) generateHeaderInfo(node.rightChild, headerInfo);
            headerInfo.append("0");
        }
    }

    public BitSet compressString(String textToCompress) {
        fillFrequencyMap(textToCompress);

        if (charsFrequencyMap.size() == 1) {
            createHuffmanTree(true);
            fillCodesMap(true);
        } else {
            createHuffmanTree(false);
            fillCodesMap(false);
        }
        return changeTextToCode(textToCompress);
    }

    private void fillFrequencyMap(String textToAnalyze){
        for(char it : textToAnalyze.toCharArray()){
            if(charsFrequencyMap.containsKey(it) ){
                charsFrequencyMap.put(it , charsFrequencyMap.get(it)+1);
            }
            else{
                charsFrequencyMap.put(it , 1);
            }
        }
    }

    private void createHuffmanTree(boolean onlyOneChar) {

        if (onlyOneChar) {
            Map.Entry<Character, Integer> entry = charsFrequencyMap.entrySet().iterator().next();
            huffmanTreeRoot = new HuffmanTreeNode(entry);
            huffmanTreeRoot.isLeaf = true;
        }

        PriorityQueue<HuffmanTreeNode> nodesToAddQueue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> it : charsFrequencyMap.entrySet()) {
            nodesToAddQueue.add(new HuffmanTreeNode(it));
        }

        while (nodesToAddQueue.size() > 1) {
            HuffmanTreeNode newNode = new HuffmanTreeNode();
            newNode.leftChild = nodesToAddQueue.poll();
            newNode.rightChild = nodesToAddQueue.poll();
            newNode.value = newNode.leftChild.value + newNode.rightChild.value;

            nodesToAddQueue.add(newNode);
        }
        huffmanTreeRoot = nodesToAddQueue.poll();
    }

    private void fillCodesMap(boolean onlyOneChar) {
        if (onlyOneChar) {
            Map.Entry<Character, Integer> entry = charsFrequencyMap.entrySet().iterator().next();
            codesMap.put(entry.getKey(), "0");
        } else
        {
            fillCodesMap(huffmanTreeRoot, new StringBuilder());
        }
    }

    private void fillCodesMap(HuffmanTreeNode root, StringBuilder code) {
        if (root != null) {
            if (root.isLeaf) {
                codesMap.put(root.key, code.toString());
            } else {
                fillCodesMap(root.leftChild, code.append('0'));
                code.deleteCharAt(code.length() - 1);
                fillCodesMap(root.rightChild, code.append('1'));
                code.deleteCharAt(code.length() - 1);
            }
        }
    }

    private BitSet changeTextToCode(String textToCompress) {
        BitSet compressedText = new BitSet();
        int bitSetIterator = 0;
        for (char it : textToCompress.toCharArray()) {
            if (codesMap.get(it) != null) {
                for (char it2 : codesMap.get(it).toCharArray()) {
                    if (it2 == '1') {
                        compressedText.set(bitSetIterator);
                    }
                    else{
                    }
                    bitSetIterator++;
                }
            }
        }
        compressedTextLength = bitSetIterator;
        return compressedText;
    }
}

//TODO
// uproscic logike, zwlaszcza wyrzucic z decompressora zbedne codesMap i freqMap, poprawic dostepnosc atrybutow HuffmanTreeNode