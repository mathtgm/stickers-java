import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class GeradorStickers  {

    public void gerar(InputStream imagem, String nomeArquivo, Double pontuacao) throws Exception {

        // Leitura da imagem
        BufferedImage image = ImageIO.read(imagem);

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

        //Configurando a frase e selo
        String frase;
        if(pontuacao < 5) {
            frase = "Ruim!";
        } else if(pontuacao <= 7) {
            frase = "Bom!";
        } else {
            frase = "TOPZERA!!";
            // Adiciona o selo
            FileInputStream seloStream = new FileInputStream(new File("image/selo.png"));
            BufferedImage selo = ImageIO.read(seloStream);
            graphics2D.drawImage(selo, 100, 100, (int) (altura * 0.20),(int) (altura * 0.20), null);
        }
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        // Lagura do texto - Largura da imagem, depois divide por 2 e é igual a margem
        int novoX = (int) (largura - fontMetrics.getStringBounds(frase, graphics2D).getWidth())/2;
        int novoY = novaAltura;
        graphics2D.drawString(frase, novoX, novoY);

        // Configurando o outline da frase
        FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        TextLayout textLayout = new TextLayout(frase, fonte, fontRenderContext);
        Shape shape = textLayout.getOutline(null);
        AffineTransform affineTransform = graphics2D.getTransform();
        affineTransform.translate(novoX, novoY);
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

    public String tratarNomeArquivo(String nomeArquivo) {

        String novoNomeArquivo = nomeArquivo.replaceAll(" ", "").replaceAll("[^a-zA-Z0-9]", "");

        return novoNomeArquivo;
    }

}
