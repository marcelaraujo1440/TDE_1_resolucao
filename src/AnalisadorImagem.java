import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class AnalisadorImagem {
    public static void analisarImagem(String caminhoImagem) throws IOException {
        File arquivo = new File(caminhoImagem);
        BufferedImage imagem = ImageIO.read(arquivo);

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        System.out.println("=== ANÁLISE DA IMAGEM ===");
        System.out.println("Arquivo: " + caminhoImagem);
        System.out.println("Dimensões: " + largura + " x " + altura + " pixels");

        // vai analisar cores da imagem
        java.util.Map<String, Integer> coresEncontradas = new java.util.HashMap<>();

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                Color cor = new Color(imagem.getRGB(x, y));
                String corString = String.format("RGB(%d,%d,%d)", cor.getRed(), cor.getGreen(), cor.getBlue());
                coresEncontradas.put(corString, coresEncontradas.getOrDefault(corString, 0) + 1);
            }
        }
        //vai mostrar todas as cores encontradas
        System.out.println("Cores encontradas:");
        for (java.util.Map.Entry<String, Integer> entrada : coresEncontradas.entrySet()) {
            System.out.println("- " + entrada.getKey() + ": " + entrada.getValue() + " pixels");
        }

        // sugere pontos para flood fill baseados na cor mais comum
        String corMaisComum = null;
        int maxPixels = 0;
        for (java.util.Map.Entry<String, Integer> entrada : coresEncontradas.entrySet()) {
            if (entrada.getValue() > maxPixels) {
                maxPixels = entrada.getValue();
                corMaisComum = entrada.getKey();
            }
        }

        System.out.println("Cor mais comum (provável fundo): " + corMaisComum);
        System.out.println("Recomendação: Use flood fill em uma área desta cor para preenchimento máximo");

        // encontra o primeiro pixel da cor do fundo
        Color corFundo = extrairCorDaString(corMaisComum);
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                Color corAtual = new Color(imagem.getRGB(x, y));
                if (corAtual.equals(corFundo)) {
                    System.out.println("Primeiro pixel da cor de fundo encontrado em: (" + x + ", " + y + ")");
                    return;
                }
            }
        }
    }

    private static Color extrairCorDaString(String corString) {
        // extrai rgb para Color
        String[] valores = corString.replace("RGB(", "").replace(")", "").split(",");
        return new Color(
                Integer.parseInt(valores[0]),
                Integer.parseInt(valores[1]),
                Integer.parseInt(valores[2])
        );
    }

    public static Color encontrarCorDeFundo(BufferedImage imagem) {
        // assume que o pixel (0,0) é da cor de fundo
        return new Color(imagem.getRGB(0, 0));
    }

    public static java.util.List<Coordenada> encontrarPontosDePreenchimento(BufferedImage imagem, Color corAlvo) {
        java.util.List<Coordenada> pontos = new java.util.ArrayList<>();
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        // procura por regiões conectadas da cor alvo
        boolean[][] visitado = new boolean[largura][altura];

        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                if (!visitado[x][y]) {
                    Color corAtual = new Color(imagem.getRGB(x, y));
                    if (corAtual.equals(corAlvo)) {
                        pontos.add(new Coordenada(x, y));
                        marcarRegiaoVisitada(imagem, visitado, x, y, corAlvo);
                    }
                }
            }
        }

        return pontos;
    }

    private static void marcarRegiaoVisitada(BufferedImage imagem, boolean[][] visitado, int x, int y, Color corAlvo) {
        if (x < 0 || x >= imagem.getWidth() || y < 0 || y >= imagem.getHeight() || visitado[x][y]) {
            return;
        }

        Color corAtual = new Color(imagem.getRGB(x, y));
        if (!corAtual.equals(corAlvo)) {
            return;
        }


        visitado[x][y] = true;

        // marca os vizinhos
        marcarRegiaoVisitada(imagem, visitado, x + 1, y, corAlvo);
        marcarRegiaoVisitada(imagem, visitado, x - 1, y, corAlvo);
        marcarRegiaoVisitada(imagem, visitado, x, y + 1, corAlvo);
        marcarRegiaoVisitada(imagem, visitado, x, y - 1, corAlvo);
    }
}