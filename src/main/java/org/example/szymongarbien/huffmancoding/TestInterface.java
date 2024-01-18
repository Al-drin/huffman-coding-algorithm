package org.example.szymongarbien.huffmancoding;

import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.service.DotService;
import org.example.szymongarbien.huffmancoding.service.EncodingService;

public class TestInterface{

    static EncodingService encodingService = new EncodingService();
    static DotService dotService = new DotService();
    public static void main(String[] args) {

        String text = "Techniki Multimedialne";

        String fileName = "test";

        HuffMessage message = encodingService.encode(text);
        System.out.println("Message encoded");

        dotService.writeDot(fileName, message.getRoot(), message.getCodePage());
        System.out.println("Dot file saved");

        dotService.generateImage(fileName);
        System.out.println("Image generated");
    }
}