package org.example.szymongarbien.huffmancoding.domain;

import java.util.Map;

public record HuffMessage(Map<Character, String> codePage, String msg) {
    @Override
    public String toString() {
        return "HuffMessage{" +
                "dictionary=" + codePage +
                ", msg='" + msg + '\'' +
                '}';
    }
}
