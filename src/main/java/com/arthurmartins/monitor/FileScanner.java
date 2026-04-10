package com.arthurmartins.monitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    public static List<String> readSites(String fileName) {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            System.out.println("Arquivo '" + fileName + "' não encontrado.");
            return new ArrayList<>();
        }

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}