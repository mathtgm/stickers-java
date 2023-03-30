import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ClienteHttp {

    public String buscaDados(String url) {
        try {

            URI uri = URI.create(url);
            // Cria um cliente
            var httpClient = HttpClient.newHttpClient();

            // Monto minha requisicao
            HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();

            // Envio a requisicao e armazeno a repsosta em um body
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String body = httpResponse.body();

            return body;

        } catch (IOException | InterruptedException ex) {
            throw new HttpClientException("Erro ao buscar as informações! Verifique o URL da API");
        }
    }
}
