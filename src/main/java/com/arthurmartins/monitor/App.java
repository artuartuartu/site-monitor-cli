package com.arthurmartins.monitor;

import java.util.Scanner;
import java.util.List;

public final class App {
    /** Scanner para leitura de entradas do usuário via console. */
    private static final Scanner SCANNER = new Scanner(System.in);
    /** Instância do verificador HTTP para validar o status dos sites. */
    private static final HttpChecker CHECKER = new HttpChecker();
    /** Variável para gerir o fluxo do prograna. */
    private static boolean continuar = true;

    /**
     * Método para acessar e retornar o valor da variável "continuar".
     *
     * @return true para o programa continuar, false para finalizar o programa.
     */
    public static boolean getContinuar() {
        return continuar;
    }

    /**
     * Método para acessar e alterar o valor da variável "continuar".
     *
     * @param valor Novo valor para a variável "continuar".
     */
    public static void setContinuar(final boolean valor) {
        continuar = valor;
    }
    private App() {
        throw new UnsupportedOperationException(
                "Esta é uma classe utilitária e não pode ser instanciada.");
    }

    /**
     * Ponto de entrada principal da aplicação. Gerencia o loop do menu.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(final String[] args) {
        exibirBanner();

        while (getContinuar()) {
            exibirMenu();

            if (!SCANNER.hasNextLine()) {
                break;
            }

            String opcao = SCANNER.nextLine();

            switch (opcao) {
                case "1" -> processarUrl();
                case "2" -> processarArquivo();
                case "0" -> {
                    System.out.println("\nEncerrando...");
                    setContinuar(false);
                }
                default -> System.out.println(
                        "\nOpção inválida! Tente novamente.");
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
        String nomeArquivo = SCANNER.nextLine();

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
        setContinuar(false);
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
        String url = SCANNER.nextLine();

        if (url.isBlank()) {
            System.out.println(" Erro: A URL não pode estar vazia.");
            return;
        }

        validarSite(url);
    }

    private static void validarSite(final String url) {
        String urlFormatada = url.startsWith("http") ? url : "https://" + url;
        System.out.println("\nVerificando...");

        if (CHECKER.isSiteUp(urlFormatada)) {
            System.out.println("STATUS: ONLINE [" + urlFormatada + "]");
        } else {
            System.out.println("STATUS: OFFLINE ou inacessível");
        }
        setContinuar(false);
    }
}
