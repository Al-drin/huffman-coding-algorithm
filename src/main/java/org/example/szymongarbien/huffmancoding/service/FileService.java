package org.example.szymongarbien.huffmancoding.service;

import com.google.gson.Gson;
import org.example.szymongarbien.huffmancoding.domain.HuffMessage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class FileService {

    Gson gson = new Gson();

    public void saveMessage(HuffMessage huffMessage, File file) throws IOException {
        try(FileWriter fw = new FileWriter(file)) {
            gson.toJson(huffMessage, fw);
        }
    }

    public Optional<HuffMessage> loadMessage(File file) {
        try(FileReader fr = new FileReader(file)) {
            return Optional.of(gson.fromJson(fr, HuffMessage.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
