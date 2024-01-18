package org.example.szymongarbien.huffmancoding.domain;

import java.util.Comparator;

public class HuffComparator implements Comparator<HuffNode> {

    @Override
    public int compare(HuffNode o1, HuffNode o2) {
        return o1.weight - o2.weight;
    }
}
