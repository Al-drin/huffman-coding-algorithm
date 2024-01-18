package org.example.szymongarbien.huffmancoding.domain;

import java.util.Map;

public class HuffMessage {
    volatile HuffNode root;
    Map<Character, String> codePage;
    String encodedString;

    public HuffMessage(Map<Character, String> codePage, String encodedString, HuffNode root) {
        this.codePage = codePage;
        this.encodedString = encodedString;
        this.root = root;
    }

    public Map<Character, String> getCodePage() {
        return codePage;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public HuffNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "HuffMessage{" +
                "codePage=" + codePage +
                ", encodedString='" + encodedString + '\'' +
                '}';
    }
}
