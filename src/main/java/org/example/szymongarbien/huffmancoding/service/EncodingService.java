package org.example.szymongarbien.huffmancoding.service;

import org.example.szymongarbien.huffmancoding.domain.HuffComparator;
import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.domain.HuffNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class EncodingService {

    public HuffMessage encode(String str) {

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            charFrequency.merge(str.charAt(i), 1, Integer::sum);
        }

        Queue<HuffNode> huffQueue = new PriorityQueue<>(new HuffComparator());

        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            HuffNode huffNode = new HuffNode(entry.getKey(), entry.getValue());

            huffQueue.add(huffNode);
        }

        HuffNode huffRoot = new HuffNode();

        while (huffQueue.size() > 1) {

            HuffNode huffNode = new HuffNode();
            huffNode.setLeft(huffQueue.poll());
            huffNode.setRight(huffQueue.poll());
            huffNode.setVal( huffNode.getLeft().getVal() + huffNode.getRight().getVal() );

            huffRoot = huffNode;
            huffQueue.add(huffNode);
        }

        Map<Character, String> codePage = new HashMap<>(charFrequency.size());

        generateCodePage(huffRoot, codePage, "");

        for (Map.Entry<Character, String> entry : codePage.entrySet()) {
            str = str.replace(""+entry.getKey(), entry.getValue());
        }

        return new HuffMessage(codePage, str);
    }

    private void generateCodePage(HuffNode node, Map<Character, String> codePage, String code) {
        if (node.getC() > 0) {
            codePage.put(node.getC(), code);
            return;
        }

        generateCodePage(node.getLeft(), codePage, code.concat("0"));
        generateCodePage(node.getRight(), codePage, code.concat("1"));
    }

    public String decode(HuffMessage huffMessage) {
        Map<String, Character> decodePage = new HashMap<>();

        for (Map.Entry<Character, String> entry : huffMessage.codePage().entrySet()) {
            decodePage.put(entry.getValue(), entry.getKey());
        }

        StringBuilder decodedMessage = new StringBuilder();

        String msg = huffMessage.msg();
        String substring;
        int startIndex = 0;
        int endIndex = 1;
        while (endIndex < msg.length()) {
            substring = msg.substring(startIndex, endIndex);
            while (!decodePage.containsKey(substring)) {
                endIndex++;
                substring = msg.substring(startIndex, endIndex);
            }
            decodedMessage.append(decodePage.get(substring));
            startIndex = endIndex++;
        }

        return decodedMessage.toString();
    }
}
