package com.arthurmartins.monitor;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final httpChecker checker = new httpChecker();
    public static boolean continuar = true;

    public static void main(String[] args) {
        exibirBanner();

        while (continuar) {
            exibirMenu();

            if (!scanner.hasNextLine()) break;

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> processarUrl();
                case "2" -> processarArquivo();
                case "0" -> {
                    System.out.println("\nEncerrando...");
                    continuar = false;
                }
                default -> System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirBanner() {
        System.out.println("""
        =======================================================
           SITE-MONITOR-CLI: MONITOR DE DISPONIBILIDADE
        =======================================================
        """);
    }

    private static void processarArquivo() {
        System.out.print("\nDigite o nome do arquivo (ex: sites.txt): ");
        String nomeArquivo = scanner.nextLine();

        List<String> sites = FileScanner.readSites(nomeArquivo);

        if (sites.isEmpty()) {
            System.out.println("Nenhuma URL para processar.");
        } else {
            System.out.println("\n--- Iniciando Verificação em Lote ---");
            for (String site : sites) {
                String urlLimpa = site.trim();
                if (!urlLimpa.isEmpty()) {
                    validarSite(urlLimpa);
                }
            }
            System.out.println("--- Verificação Concluída ---");
        }
        continuar = false;
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1) Verificar uma URL única");
        System.out.println("2) Ler lista de sites de um arquivo");
        System.out.println("0) Finalizar");
        System.out.print("\n> Seleção: ");
    }

    private static void processarUrl() {
        System.out.print("\nDigite a URL para verificar (ex: google.com): ");
        String url = scanner.nextLine();

        if (url.isBlank()) {
            System.out.println(" Erro: A URL não pode estar vazia.");
            return;
        }

        validarSite(url);
    }

    private static void validarSite(String url) {
        String urlFormatada = url.startsWith("http") ? url : "https://" + url;
        System.out.println("\nVerificando...");

        if (checker.isSiteUp(urlFormatada)) {
            System.out.println("STATUS: ONLINE [" + urlFormatada + "]");
        } else {
            System.out.println("STATUS: OFFLINE ou inacessível");
        }
        continuar = false;
    }
}