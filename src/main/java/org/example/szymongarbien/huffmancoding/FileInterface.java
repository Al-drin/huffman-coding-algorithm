package org.example.szymongarbien.huffmancoding;

import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.service.EncodingService;
import org.example.szymongarbien.huffmancoding.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileInterface {
    public static void main(String[] args) {
        EncodingService encodingService = new EncodingService();
        FileService fileService = new FileService();

        if (args.length == 0) {
            System.out.println("Please provide a file path.");
            return;
        }

        File fileRead = new File(args[0]);

        if (!fileRead.exists() || fileRead.isDirectory()) {
            System.out.println("File not found!");
            return;
        }

        //let's try to load this file as a JSON with the encoded message

        Optional<HuffMessage> messageOptional = fileService.loadMessage(fileRead);

        if (messageOptional.isPresent()) {
            String text = encodingService.decode(messageOptional.get());

            System.out.println("Decoded message:");
            System.out.println(text);

            return;
        }

        //if it's unsuccessful - we treat it as plain text and encode it

        String text;

        try {
            text = Files.readString(Path.of(args[0]));
        } catch (IOException e) {
            throw new RuntimeException("Cannot load file!");
        }

        System.out.println("Original text:");
        System.out.println(text);

        HuffMessage message = encodingService.encode(text);

        try {
            fileService.saveMessage(message, new File(args[0] + ".huf"));
            System.out.println("File successfully encoded as:");
            System.out.println(args[0] + ".huf");
        } catch (IOException e) {
            throw new RuntimeException("Cannot save encoded file!");
        }
    }
}