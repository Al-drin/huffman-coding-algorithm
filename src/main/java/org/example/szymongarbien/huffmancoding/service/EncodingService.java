package org.example.szymongarbien.huffmancoding.service;

import org.example.szymongarbien.huffmancoding.domain.HuffComparator;
import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.domain.HuffNode;

import java.util.*;

public class EncodingService {

    int id;

    public HuffMessage encode(String originalString) {

        Map<Character, Integer> charFrequency = buildCharFrequencyMap(originalString);

        Queue<HuffNode> huffQueue = buildNodeQueue(charFrequency);

        HuffNode huffRoot = buildHuffmanTree(huffQueue);

        Map<Character, String> codePage = new HashMap<>(charFrequency.size());

        generateCodePage(huffRoot, codePage, "");

        String encodedString = buildEncodedString(originalString, codePage);

        return new HuffMessage(codePage, encodedString, huffRoot);
    }

    private String buildEncodedString(String originalString, Map<Character, String> codePage) {
        StringBuilder encodedString = new StringBuilder();

        for (int i = 0; i< originalString.length(); i++) {
            encodedString.append(codePage.get(originalString.charAt(i)));
        }
        return encodedString.toString();
    }

    private HuffNode buildHuffmanTree(Queue<HuffNode> huffQueue) {
        HuffNode huffRoot = new HuffNode();
        HuffNode huffNode;

        while (huffQueue.size() > 1) {

            huffNode = new HuffNode();
            huffNode.setId(++id);
            huffNode.setLeft(huffQueue.poll());
            huffNode.setRight(huffQueue.poll());
            huffNode.setWeight( huffNode.getLeft().getWeight() + huffNode.getRight().getWeight() );

            huffRoot = huffNode;
            huffQueue.add(huffNode);
        }

        return huffRoot;
    }

    private Queue<HuffNode> buildNodeQueue(Map<Character, Integer> charFrequency) {
        Queue<HuffNode> huffQueue = new PriorityQueue<>(new HuffComparator());

        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            HuffNode huffNode = new HuffNode(++id, entry.getKey(), entry.getValue());

            huffQueue.add(huffNode);
        }

        return huffQueue;
    }

    private Map<Character, Integer> buildCharFrequencyMap(String str) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            charFrequency.merge(str.charAt(i), 1, Integer::sum);
        }
        
        return charFrequency;
    }

    private void generateCodePage(HuffNode node, Map<Character, String> codePage, String code) {

        if (node.getCharacter() > 0) {
            codePage.put(node.getCharacter(), code);
            return;
        }

        generateCodePage(node.getLeft(), codePage, code.concat("0"));
        generateCodePage(node.getRight(), codePage, code.concat("1"));
    }

    public String decode(HuffMessage huffMessage) {
        Map<String, Character> decodePage = buildDecodePage(huffMessage.getCodePage());

        return buildDecodedString(huffMessage.getEncodedString(), decodePage);
    }

    private String buildDecodedString(String encodedString, Map<String, Character> decodePage) {
        StringBuilder decodedString = new StringBuilder();

        String substring;
        int startIndex = 0;
        int endIndex = 1;

        while (endIndex < encodedString.length()) {
            substring = encodedString.substring(startIndex, endIndex);
            while (!decodePage.containsKey(substring)) {
                endIndex++;
                substring = encodedString.substring(startIndex, endIndex);
            }
            decodedString.append(decodePage.get(substring));
            startIndex = endIndex++;
        }
        return decodedString.toString();
    }

    public Map<String, Character> buildDecodePage(Map<Character, String> codePage) {
        Map<String, Character> decodePage = new HashMap<>();

        for (Map.Entry<Character, String> entry : codePage.entrySet()) {
            decodePage.put(entry.getValue(), entry.getKey());
        }

        return decodePage;
    }
}
