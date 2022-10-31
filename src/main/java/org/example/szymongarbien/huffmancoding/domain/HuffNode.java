package org.example.szymongarbien.huffmancoding.domain;

public class HuffNode {
    char c;
    int val;

    HuffNode left;
    HuffNode right;

    public HuffNode() {
    }

    public HuffNode(char c, int val) {
        this.c = c;
        this.val = val;
    }

    public char getC() {
        return c;
    }

    public int getVal() {
        return val;
    }

    public HuffNode getLeft() {
        return left;
    }

    public HuffNode getRight() {
        return right;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLeft(HuffNode left) {
        this.left = left;
    }

    public void setRight(HuffNode right) {
        this.right = right;
    }
}
