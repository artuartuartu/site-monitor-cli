package com.arthurmartins.monitor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.time.Duration;

public final class HttpChecker {
    /** Tempo limite para a conexão em segundos. */
    private static final int TIMEOUT_SECONDS = 5;
    /** Código de status que indica que o site está online. */
    private static final int STATUS_OK = 200;
    /** Código de status que indica que o site está offline. */
    private static final int STATUS_NOT_OK = 400;

    /** * Cliente HTTP utilizado para enviar as requisições de verificação.
     * Configurado para seguir redirecionamentos automaticamente.
     */
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private HttpChecker() {
        throw new UnsupportedOperationException(
                "Esta é uma classe utilitária e não pode ser instanciada.");
    }


    /**
     * Verifica se um site está online enviando uma requisição HTTP HEAD.
     * @param url A URL completa do site a ser verificado.
     * @return true se o site retornar um código de status de sucesso,
     * false caso o site esteja fora do ar ou ocorra um erro de conexão.
     */
    public static boolean isSiteUp(final String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0 "
                            +
                            "(Windows NT 10.0; Win64; x64) Chrome/120.0.0.0")
                    .header("Accept", "text/html,application/xhtml+xml,"
                            +
                            "application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .GET()
//                    .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                    .build();

            HttpResponse<Void> response = CLIENT.send(request,
                    HttpResponse.BodyHandlers.discarding());

            return response.statusCode() >= STATUS_OK
                    && response.statusCode() < STATUS_NOT_OK;

        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Obtém e exibe a localização geográfica do servidor do domínio fornecido.
     *
     * @param url A URL do site para verificar a localização.
     */
    public static void showLocation(final String url) {
        final int successCode = 200;
        try {
            String domain = url.replace("https://", "")
                    .replace("http://", "")
                    .split("/")[0];

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://ip-api.com/json/" + domain))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == successCode) {
                JsonObject json =
                        JsonParser.parseString(
                                response.body()).getAsJsonObject();

                if ("success".equals(json.get("status").getAsString())) {
                    String city = json.get("city").getAsString();
                    String country = json.get("country").getAsString();
                    String isp = json.get("isp").getAsString();

                    System.out.println("Localização: " + city + ", " + country);
                    System.out.println("Provedor: " + isp);
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível obter dados de localização.");
        }
    }
}
