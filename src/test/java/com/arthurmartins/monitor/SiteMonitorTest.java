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

}