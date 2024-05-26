package SongOperations.Compression;

import java.util.Map;

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    public HuffmanTreeNode rightChild;
    public HuffmanTreeNode leftChild;
    public Character key;
    public Integer value;
    public boolean isLeaf;

    public HuffmanTreeNode() {
        this.isLeaf = false;
    }

    public HuffmanTreeNode(Map.Entry<Character, Integer> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
        this.isLeaf = true;
    }

    @Override
    public int compareTo(HuffmanTreeNode other) {
        return this.value - other.value;
    }
}