package SongOperations;

import java.io.*;
import java.util.*;
public class Compressor {
    private Map<Character,Integer> charsFrequencyMap;
    private HuffmanTreeNode huffmanTreeRoot;
    private Map<Character , String> codesMap;
    private class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
        private HuffmanTreeNode rightChild;
        private HuffmanTreeNode leftChild;
        private Character key;
        private Integer value;
        private boolean isLeaf;
        public HuffmanTreeNode() {
            this.isLeaf = false;
        }
        public HuffmanTreeNode(Map.Entry<Character , Integer>entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
            this.isLeaf = true;
        }

        @Override
        public int compareTo(HuffmanTreeNode other) {
            return this.value - other.value;
        }
    }
    public Compressor(){
        codesMap = new HashMap<>();
        charsFrequencyMap = new TreeMap<>();
    }
    public void compressFile(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        StringBuilder sbToCompress = new StringBuilder();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            sbToCompress.append(scanner.nextLine());
            sbToCompress.append("\n");
        }
        if(!sbToCompress.isEmpty()) sbToCompress.deleteCharAt(sbToCompress.length()-1); //Aby zdekompresowany plik nie mial nowego wiersza na koncu


        BitSet compressedText = compressString(sbToCompress.toString());
        String newFileName = fileName.replace(".txt", " compressed.txt");

        try (FileOutputStream fos = new FileOutputStream(newFileName)) {
            byte[] compressedTextByteArray = compressedText.toByteArray();
            fos.write(compressedTextByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void decompressFile(String fileName) throws IOException {

        File file = new File(fileName);
        if(!file.exists()) throw new FileNotFoundException("Nie znaleziono pliku!");

        BitSet compressedText = new BitSet();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            byte[] byteArray = fis.readAllBytes();
            compressedText = BitSet.valueOf(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String decompressedText = decompressSb(bitSetToSb(compressedText));
        String newFileName = fileName.replace("compressed", "decompressed");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            writer.write(decompressedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BitSet compressString(String textToCompress){

        fillFrequencyMap(textToCompress );

        if(charsFrequencyMap.size()==1){
            createHuffmanTree(true);
            fillCodesMap(true);
        }
        else {
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
    private void createHuffmanTree(boolean onlyOneChar){

        if(onlyOneChar){
            Map.Entry<Character, Integer> entry = charsFrequencyMap.entrySet().iterator().next();
            huffmanTreeRoot = new HuffmanTreeNode(entry);
            huffmanTreeRoot.isLeaf = true;
        }

        PriorityQueue<HuffmanTreeNode> NodesToAddQueue = new PriorityQueue<>();

        for(Map.Entry it : charsFrequencyMap.entrySet()){
            NodesToAddQueue.add(new HuffmanTreeNode(it));;
        }

        while (NodesToAddQueue.size()>1){
            HuffmanTreeNode newNode = new HuffmanTreeNode();
            newNode.leftChild = NodesToAddQueue.poll();
            newNode.rightChild = NodesToAddQueue.poll();
            newNode.value = newNode.leftChild.value + newNode.rightChild.value;

            NodesToAddQueue.add(newNode);
        }
        huffmanTreeRoot = NodesToAddQueue.poll();
    }
    private void fillCodesMap(boolean onlyOneChar){
        if(onlyOneChar){
            Map.Entry<Character, Integer> entry = charsFrequencyMap.entrySet().iterator().next();
            codesMap.put(entry.getKey() , "0");
        }
        else
        {
            fillCodesMap(huffmanTreeRoot , new StringBuilder());
        }
    }
    private void fillCodesMap(HuffmanTreeNode root, StringBuilder code){
        if (root != null) {
            if (root.isLeaf) {
                codesMap.put(root.key , code.toString());
            } else {
                fillCodesMap(root.leftChild, code.append('0'));
                code.deleteCharAt(code.length() - 1);
                fillCodesMap(root.rightChild, code.append('1'));
                code.deleteCharAt(code.length() - 1);
            }
        }
    }
    private BitSet changeTextToCode(String textToCompress){

        BitSet compressedText = new BitSet();
        int bitSetIterator = 0;
        for(char it : textToCompress.toCharArray()){
            for(char it2 : codesMap.get(it).toCharArray()){
                if(it2=='1'){
                    compressedText.set(bitSetIterator);
                }
                else {
                }
                bitSetIterator++;
            }
        }
        return  compressedText;
    }
    public String decompressSb(StringBuilder sbToDecompress){
        StringBuilder decompressedSb = new StringBuilder();

        //Zabezpieczenie przed przypadkiem gdzie dekompresowany tekst to ciag, ktorego wszystkie elementy to ten sam znak
        if(sbToDecompress.isEmpty() && !codesMap.isEmpty()){
            Map.Entry<Character, Integer> entry = charsFrequencyMap.entrySet().iterator().next();
            for (int it = 0; it < entry.getValue(); it++) {
                decompressedSb.append(entry.getKey());
            }
        }

        if(sbToDecompress.toString().equals("0")){
            return huffmanTreeRoot.key.toString();
        }

        while(true) {
            if(sbToDecompress.isEmpty()) return decompressedSb.toString();
            decompressedSb.append( decompressChar(huffmanTreeRoot, sbToDecompress));
        }
    }
    private char decompressChar(HuffmanTreeNode currentNode, StringBuilder SbToDecompress){
        while(true){

            if(currentNode.isLeaf)
            {
                return currentNode.key;
            }
            else if(SbToDecompress.charAt(0)=='0') currentNode = currentNode.leftChild;
            else if(SbToDecompress.charAt(0)=='1') currentNode = currentNode.rightChild;
            SbToDecompress.deleteCharAt(0);
        }
    }
    private StringBuilder bitSetToSb(BitSet compressedText){
        StringBuilder compressedTextSb = new StringBuilder();
        for (int i = 0; i < compressedText.length(); i++) {
            if(compressedText.get(i)){
                compressedTextSb.append('1');
            }
            else{
                compressedTextSb.append('0');
            }
        }
        return compressedTextSb;
    }
}