import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNASA implements ExtratorCounteudo {

    public List<Conteudo> extrairConteudos(String json) {

        // Converte a resposta em JSON para um Mapa/Dicionario
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDados = jsonParser.parse(json);

        List<Conteudo> listaConteudo = new ArrayList<>();

        // Alimenta a lista de conteudo
        for (Map<String, String> dado: listaDados) {
            String titulo = dado.get("title");
            String urlImagem = dado.get("url");
            listaConteudo.add(new Conteudo(titulo, urlImagem));
        }

        return listaConteudo;

    }

}
