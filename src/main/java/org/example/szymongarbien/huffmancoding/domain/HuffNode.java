package org.example.szymongarbien.huffmancoding.domain;

public class HuffNode {
    int id;
    char character;
    int weight;

    HuffNode left;
    HuffNode right;

    public HuffNode() {
    }

    public HuffNode(int id, char character, int weight) {
        this.id = id;
        this.character = character;
        this.weight = weight;
    }

    public char getCharacter() {
        return character;
    }

    public int getWeight() {
        return weight;
    }

    public HuffNode getLeft() {
        return left;
    }

    public HuffNode getRight() {
        return right;
    }

    public int getId() {
        return id;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setLeft(HuffNode left) {
        this.left = left;
    }

    public void setRight(HuffNode right) {
        this.right = right;
    }

    public void setId(int id) {
        this.id = id;
    }
}
