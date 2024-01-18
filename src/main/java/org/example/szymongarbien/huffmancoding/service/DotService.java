package org.example.szymongarbien.huffmancoding.service;

import org.example.szymongarbien.huffmancoding.domain.HuffNode;

import java.io.*;
import java.util.Map;

public class DotService {
    public void writeDot(String fileName, HuffNode root, Map<Character, String> codePage) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".dot")))) {
            writer.println("## Command to generate pdf:  dot -Tpdf " + fileName + ".dot -o " + fileName + ".pdf");
            writer.println("digraph g {");
            dotWriteRecursion(root, writer, codePage);  // Recursion
            writer.println("}");
        } catch (IOException e) {
            throw new RuntimeException("Could not write .dot file!");
        }
    }


    private void dotWriteRecursion(HuffNode node, PrintWriter wr, Map<Character, String> codePage) {
        char c = node.getCharacter();
        wr.println(node.getId() + " [label=<" + node.getWeight()
                + (c=='\u0000' ? "" : "<br/>'" + c + "'" + "<br/>" + codePage.get(c)) + ">]");

        if (node.getLeft() != null) {
            wr.println(node.getId() + " -> " + node.getLeft().getId() + " [color=red, label=0]");
            dotWriteRecursion(node.getLeft(), wr, codePage);
        }

        if (node.getRight() != null) {
            wr.println(node.getId() + " -> " + node.getRight().getId() + " [color=blue, label=1]");
            dotWriteRecursion(node.getRight(), wr, codePage);
        }
    }

    public void generateImage(String fileName) {

        String[] command = {"dot", "-Tpng", fileName+".dot", "-o", fileName+".png"};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not generate image!");
        }
    }
}
