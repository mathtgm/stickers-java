import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        // URL do JSON
        //String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
        // String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/MostPopularTVs.json";
        // String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/Top250TVs.json";
        String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/MostPopularFilms.json";
        URI uri = URI.create(url);
        // Cria um cliente
        var httpClient = HttpClient.newHttpClient();

        // Monto minha requisicao
        HttpRequest httpRequest = HttpRequest.newBuilder(uri).GET().build();

        // Envio a requisicao e armazeno a repsosta em um body
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
        String body = httpResponse.body();

        // Converte a resposta em JSON para um Mapa/Dicionario
        List<Map<String, String>> listaFilmes = new JsonParser().parse(httpResponse.body());

        for (Map<String, String> filme: listaFilmes) {
            System.out.println(filme.get("rank") + "" + filme.get("title"));
        }
    }
}