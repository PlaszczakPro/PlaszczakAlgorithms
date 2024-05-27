package SongOperations.Compression;

import java.util.Map;

public class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    private HuffmanTreeNode rightChild;
    private HuffmanTreeNode leftChild;
    private Character key;
    private Integer value;
    private boolean isLeaf;

    public HuffmanTreeNode() {
        this.isLeaf = false;
    }

    public HuffmanTreeNode(Map.Entry<Character, Integer> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
        this.isLeaf = true;
    }

    public HuffmanTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(HuffmanTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public HuffmanTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HuffmanTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public Character getKey() {
        return key;
    }

    public void setKey(Character key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    @Override
    public int compareTo(HuffmanTreeNode other) {
        return this.value - other.value;
    }
}