import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        // URL do JSON
        // String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
        // String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/MostPopularTVs.json";
        // String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/Top250TVs.json";
        // String url = "https://raw.githubusercontent.com/mathtgm/stickers-java/main/MostPopularFilms.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=2ksEVqKZh7sjFuhcsMVe4GCjO8asobDLhREqXQoK&start_date=2023-01-01&end_date=2023-01-13";


        String body = new ClienteHttp().buscaDados(url);
        ExtratorConteudoNASA extratorConteudoNASA = new ExtratorConteudoNASA();
        List<Conteudo> listaConteudo = extratorConteudoNASA.extrairConteudos(body);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = listaConteudo.get(i);
            System.out.println(conteudo.getTitulo());

            System.out.println("======================================================");
            InputStream imagemConteudo = new URL(conteudo.getUrlImagem()).openStream();
            new GeradorStickers().gerar(imagemConteudo, conteudo.getTitulo());

        }
    }
}