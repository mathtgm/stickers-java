import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        API api = API.NASA;
        String body = new ClienteHttp().buscaDados(api.getUrl());
        ExtratorCounteudo extratorConteudo = api.getExtrator();
        List<Conteudo> listaConteudo = extratorConteudo.extrairConteudos(body);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = listaConteudo.get(i);
            System.out.println(conteudo.titulo());

            System.out.println("======================================================");
            InputStream imagemConteudo = new URL(conteudo.urlImagem()).openStream();
            new GeradorStickers().gerar(imagemConteudo, conteudo.titulo());

        }
    }
}