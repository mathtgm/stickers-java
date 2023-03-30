import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB implements ExtratorCounteudo {

    public List<Conteudo> extrairConteudos(String json) {

        // Converte a resposta em JSON para um Mapa/Dicionario
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDados = jsonParser.parse(json);

        return listaDados.stream()
                .map(atributo -> new Conteudo(atributo.get("title"), atributo.get("image")))
                .toList();

        /* List<Conteudo> listaConteudo = new ArrayList<>();

        // Alimenta a lista de conteudo
        for (Map<String, String> dado: listaDados) {
            String titulo = dado.get("title");
            String urlImagem = dado.get("image");
            listaConteudo.add(new Conteudo(titulo, urlImagem));
        }

        return listaConteudo;
    */
    }

}
