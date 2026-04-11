package com.arthurmartins.monitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileScanner {

    private FileScanner() {
        throw new UnsupportedOperationException(
                "Esta é uma classe utilitária e não pode ser instanciada.");
    }

    /**
     * Lê um arquivo de texto e retorna uma lista com todas as suas linhas.
     * Cada linha representa uma URL de um site a ser verificado.
     *
     * @param fileName O nome ou caminho do arquivo a ser lido.
     * @return Uma lista de Strings contendo as linhas do arquivo.
     * Retorna uma lista vazia se o arquivo não for encontrado ou ocorrer erro.
     */
    public static List<String> readSites(final String fileName) {
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
