import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;


class FloodFillAlgoritmo {

    private BufferedImage imagem;
    private int largura, altura;
    private List<BufferedImage> animacao;
    private int contadorFrames;

    public FloodFillAlgoritmo(String caminhoImagem) throws IOException {
        carregarImagem(caminhoImagem);
        animacao = new ArrayList<>();
        contadorFrames = 0;
    }

    private void carregarImagem(String caminho) throws IOException {
        File arquivo = new File(caminho);
        imagem = ImageIO.read(arquivo);
        largura = imagem.getWidth();
        altura = imagem.getHeight();
    }

    public void floodFillComPilha(int x, int y, Color novaCor) {
        if (!coordenadaValida(x, y)) return;

        Color corOriginal = new Color(imagem.getRGB(x, y));

        // verifica se o ponto inicial é uma borda preta
        // se for, ele nao vai pintar
        if (corOriginal.getRed() == 0 && corOriginal.getGreen() == 0 && corOriginal.getBlue() == 0) {
            System.out.println(" Ponto inicial é uma borda preta. Cancelando operação.");
            return;
        }

        if (corOriginal.equals(novaCor)) return;

        Pilha pilha = new Pilha();
        pilha.empilhar(new Coordenada(x, y));

        System.out.println("Iniciando Flood Fill com PILHA...");
        System.out.println("Cor original: " + corToString(corOriginal));
        System.out.println("Nova cor: " + corToString(novaCor));
        System.out.println("Bordas pretas serão respeitadas (não serão pintadas)");

        int pixelsPintados = 0;

        while (!pilha.estaVazia()) {
            Coordenada atual = pilha.desempilhar();
            int cx = atual.getX();
            int cy = atual.getY();

            if (!coordenadaValida(cx, cy)) continue;

            Color corAtual = new Color(imagem.getRGB(cx, cy));

            // IMPORTANTE: Para nas bordas pretas e só pinta pixels da cor original
            if (!corAtual.equals(corOriginal)) continue;

            // verifica se é borda preta (nao deve acontecer, mas é pra garantir)
            if (corAtual.getRed() == 0 && corAtual.getGreen() == 0 && corAtual.getBlue() == 0) {
                continue; // pula bordas pretas
            }

            // pinta o pixel
            imagem.setRGB(cx, cy, novaCor.getRGB());
            pixelsPintados++;

            // adiciona vizinhos a pilha APENAS se não forem bordas pretas, caso seja, nao vai adicionar
            adicionarVizinhoSePossivel(pilha, cx + 1, cy, corOriginal); // direita
            adicionarVizinhoSePossivel(pilha, cx - 1, cy, corOriginal); // esquerda
            adicionarVizinhoSePossivel(pilha, cx, cy + 1, corOriginal); // baixo
            adicionarVizinhoSePossivel(pilha, cx, cy - 1, corOriginal); // cima

            // salva frame da animação a cada 50 pixels pintados
            if (++contadorFrames % 50 == 0) {
                salvarFrameAnimacao();
            }
        }

        salvarFrameAnimacao(); // frame final, onde esta tudo pintado
        System.out.println("Flood Fill com PILHA concluído!");
        System.out.println("Pixels pintados: " + pixelsPintados);
        System.out.println("Bordas pretas foram respeitadas (não pintadas)");
    }

//fila
    public void floodFillComFila(int x, int y, Color novaCor) {
        if (!coordenadaValida(x, y)) return;

        Color corOriginal = new Color(imagem.getRGB(x, y));

        // verifica se o ponto inicial é uma borda preta, se for, nada acontece
        if (corOriginal.getRed() == 0 && corOriginal.getGreen() == 0 && corOriginal.getBlue() == 0) {
            System.out.println("Ponto inicial é uma borda preta. Cancelando operação.");
            return;
        }

        if (corOriginal.equals(novaCor)) return;

        Fila fila = new Fila();
        fila.enfileirar(new Coordenada(x, y));

        System.out.println("Iniciando Flood Fill com FILA...");
        System.out.println("Cor original: " + corToString(corOriginal));
        System.out.println("Nova cor: " + corToString(novaCor));
        System.out.println("Bordas pretas serão respeitadas (não serão pintadas)");

        int pixelsPintados = 0;

        while (!fila.estaVazia()) {
            Coordenada atual = fila.desenfileirar();
            int cx = atual.getX();
            int cy = atual.getY();

            if (!coordenadaValida(cx, cy)) continue;

            Color corAtual = new Color(imagem.getRGB(cx, cy));

            // IMPORTANTE: para nas bordas pretas e só pinta pixels da cor original
            if (!corAtual.equals(corOriginal)) continue;

            // verifica se é borda preta
            if (corAtual.getRed() == 0 && corAtual.getGreen() == 0 && corAtual.getBlue() == 0) {
                continue;
            }

            // pinta o pixel
            imagem.setRGB(cx, cy, novaCor.getRGB());
            pixelsPintados++;

            // adiciona vizinhos a fila APENAS se não forem bordas pretas
            adicionarVizinhoSePossivel(fila, cx + 1, cy, corOriginal); // direita
            adicionarVizinhoSePossivel(fila, cx - 1, cy, corOriginal); // esquerda
            adicionarVizinhoSePossivel(fila, cx, cy + 1, corOriginal); // baixo
            adicionarVizinhoSePossivel(fila, cx, cy - 1, corOriginal); // cima

            // Salva frame da animação a cada 50 pixels pintados
            if (++contadorFrames % 50 == 0) {
                salvarFrameAnimacao();
            }
        }

        salvarFrameAnimacao(); // Frame final
        System.out.println("Flood Fill com FILA concluído!");
        System.out.println("Pixels pintados: " + pixelsPintados);
        System.out.println("Bordas pretas foram respeitadas (não pintadas)");
    }

    // Método auxiliar para adicionar vizinho apenas se for válido e não for borda preta
    private void adicionarVizinhoSePossivel(Pilha pilha, int x, int y, Color corOriginal) {
        if (!coordenadaValida(x, y)) return;

        Color corVizinho = new Color(imagem.getRGB(x, y));

        // Só adiciona se:
        // 1. A cor é igual à cor original (mesmo tipo de área)
        // 2. NÃO é borda preta (RGB 0,0,0)
        if (corVizinho.equals(corOriginal) &&
                !(corVizinho.getRed() == 0 && corVizinho.getGreen() == 0 && corVizinho.getBlue() == 0)) {
            pilha.empilhar(new Coordenada(x, y));
        }
    }

    // Sobrecarga do método para Fila
    private void adicionarVizinhoSePossivel(Fila fila, int x, int y, Color corOriginal) {
        if (!coordenadaValida(x, y)) return;

        Color corVizinho = new Color(imagem.getRGB(x, y));

        // so vai adiciona se:
        // 1. A cor é igual à cor original (mesmo tipo de área)
        // 2. NÃO é borda preta (RGB 0,0,0)
        if (corVizinho.equals(corOriginal) &&
                !(corVizinho.getRed() == 0 && corVizinho.getGreen() == 0 && corVizinho.getBlue() == 0)) {
            fila.enfileirar(new Coordenada(x, y));
        }
    }

    private boolean coordenadaValida(int x, int y) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }

    private void salvarFrameAnimacao() {
        // Cria uma cópia da imagem atual
        BufferedImage frame = new BufferedImage(largura, altura, imagem.getType());
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                frame.setRGB(x, y, imagem.getRGB(x, y));
            }
        }
        animacao.add(frame);
    }

    public void salvarImagemResultado(String caminhoSaida) throws IOException {
        File arquivo = new File(caminhoSaida);
        ImageIO.write(imagem, "PNG", arquivo);
        System.out.println("Imagem salva em: " + caminhoSaida);
    }

    public void salvarAnimacao(String diretorioSaida) throws IOException {
        File dir = new File(diretorioSaida);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (int i = 0; i < animacao.size(); i++) {
            String nomeArquivo = String.format("%s/frame_%04d.png", diretorioSaida, i);
            File arquivo = new File(nomeArquivo);
            ImageIO.write(animacao.get(i), "PNG", arquivo);
        }

        System.out.println("Animação salva em: " + diretorioSaida);
        System.out.println("Total de frames: " + animacao.size());
    }

    private String corToString(Color cor) {
        return String.format("RGB(%d, %d, %d)", cor.getRed(), cor.getGreen(), cor.getBlue());
    }

    public void resetarImagem(String caminhoOriginal) throws IOException {
        carregarImagem(caminhoOriginal);
        animacao.clear();
        contadorFrames = 0;
    }
}