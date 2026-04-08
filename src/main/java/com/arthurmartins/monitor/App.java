package com.arthurmartins.monitor;

import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        httpChecker checker = new httpChecker();
        List<String> sitesParaVerificar = new ArrayList<>();

        System.out.println("=== Site Monitor: Monitor de Disponibilidade de Sites ===\n");

        // 1. Prioridade: Argumentos do Terminal
        if (args.length > 0) {
            System.out.println("Argumento de linha de comando detectado.");
            sitesParaVerificar.add(args[0]);
        }
        // 2. Segunda opção: Tentar ler o arquivo (vamos implementar a lógica de arquivo logo mais)
        else {
            System.out.println("Verificando arquivo 'sites.txt'...");
            // Por enquanto, vamos simular uma lista vazia ou manual
            // sitesParaVerificar = FileScanner.read("sites.txt");
            System.out.println("⚠️  Leitura de arquivo ainda não implementada. Use: java -jar app.jar <url>");
        }

        // 3. Execução da verificação
        if (sitesParaVerificar.isEmpty()) {
            System.out.println("\n❌ Erro: Nenhuma URL encontrada para monitorar.");
        } else {
            for (String site : sitesParaVerificar) {
                validarSite(checker, site);
            }
        }
    }

    private static void validarSite(httpChecker checker, String url) {
        // Pequeno ajuste para garantir que a URL seja válida para o HttpClient
        String urlFormatada = url.startsWith("http") ? url : "https://" + url;

        System.out.print("Verificando [" + urlFormatada + "]... ");

        if (checker.isSiteUp(urlFormatada)) {
            System.out.println("✅ ONLINE");
        } else {
            System.out.println("❌ OFFLINE ou inacessível");
        }
    }
}