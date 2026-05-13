package com.arthurmartins.monitor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SiteMonitorTest {

    @Test
    void testeCaminhoFeliz() {
        assertTrue(HttpChecker.isSiteUp("https://www.google.com"),
                "Google deveria estar online! Há um erro no código");
    }

    @Test
    void testeEntradaInvalida(){
        assertFalse(HttpChecker.isSiteUp(""),
                "Uma URL vazia deve ser considerada offline e não deve travar o programa");
    }

    @Test
    void testCasoLimite() {
        List<String> sites = FileScanner.readSites("ArquivoInexistente.txt");
        assertNotNull(sites, "A lista não deve ser nula");
        assertTrue(sites.isEmpty(), "A lista deve estar vazia para arquivos inexistentes");
    }

    @Test
    void testIntegracaoIpApi() throws Exception {
        final int statusCodeEsperado = 200;
        final String urlTeste = "http://ip-api.com/json/google.com";
        
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(urlTeste))
                .GET()
                .build();

        java.net.http.HttpResponse<String> response = client.send(
                request, java.net.http.HttpResponse.BodyHandlers.ofString());

        org.junit.jupiter.api.Assertions.assertEquals(statusCodeEsperado, 
                response.statusCode(), "A API está acessível.");
        
        org.junit.jupiter.api.Assertions.assertTrue(response.body().contains("success"), 
                "A API deve retornar um JSON de sucesso.");
    }

}