import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradorStickers  {

    public void gerar(InputStream urlImagem, String nomeArquivo) throws Exception {

        // Leitura da imagem
        BufferedImage image = ImageIO.read(urlImagem);

        // Cria um nova imagem com transparencia e tamanho novo
        int largura = image.getWidth();
        int altura = image.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
        Graphics2D graphics2D = (Graphics2D) novaImagem.getGraphics();
        graphics2D.drawImage(image, 0, 0, null);

        //Configurando a fonte
        Font fonte = new Font("Impact", Font.BOLD, (int) (altura * 0.10));
        graphics2D.setFont(fonte);
        graphics2D.setColor(Color.YELLOW);

        //Configurando a frase
        String frase = "TOPZERA !";

        // Lagura do texto - Largura da imagem, depois divide por 2 e é igual a margem
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int novoX = (int) (largura - fontMetrics.getStringBounds(frase, graphics2D).getWidth())/2;
        graphics2D.drawString(frase, novoX, novaAltura);

        // Configurando o outline da frase
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        TextLayout textLayout = new TextLayout(frase, fonte, fontRenderContext);
        Shape shape = textLayout.getOutline(null);
        AffineTransform affineTransform = graphics2D.getTransform();
        affineTransform.translate(novoX, novaAltura);
        graphics2D.setTransform(affineTransform);

        BasicStroke basicStroke = new BasicStroke(largura * 0.004f);
        graphics2D.setStroke(basicStroke);

        graphics2D.setColor(Color.black);
        graphics2D.draw(shape);
        graphics2D.setClip(shape);

        // Define nome do arquivo e caminho
        String novoNomeArquivo = tratarNomeArquivo(nomeArquivo);
        new File("output/").mkdir();
        ImageIO.write(novaImagem, "png", new File("output/" + novoNomeArquivo + ".png"));

    }

    // Retira todos os caracteres especiais e espacos do nome do arquivo
    public String tratarNomeArquivo(String nomeArquivo) {

        return nomeArquivo.replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "");
    }

}
