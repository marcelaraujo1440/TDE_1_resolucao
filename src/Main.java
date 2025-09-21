import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String caminhoEntrada = "src/tde1_.png";

            //verificao do arquivo
            File arquivoTeste = new File(caminhoEntrada);
            if (!arquivoTeste.exists()) {
                System.err.println("Arquivo não encontrado: " + caminhoEntrada);
                return;
            }

            System.out.println("Arquivo encontrado: " + caminhoEntrada);

            // analisa a imagem
            AnalisadorImagem.analisarImagem(caminhoEntrada);

            // cria o algoritmo
            FloodFillAlgoritmo floodFill = new FloodFillAlgoritmo(caminhoEntrada);

            // carrega a imagem para análise
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoEntrada));
            int largura = imagemOriginal.getWidth();
            int altura = imagemOriginal.getHeight();

            //vai mostrar as dimensoes da imagem
            System.out.println("\n Dimensões da imagem: " + largura + " x " + altura + " pixels");

            // define pontos estratégicos para testar (centro de diferentes quadrados)
            java.util.List<Coordenada> pontosParaTestar = new java.util.ArrayList<>();

            // calcula posicoes aproximadas dos centros dos quadrados
            int quadradoLargura = largura / 5;
            int quadradoAltura = altura / 5;

            System.out.println("Tamanho aproximado de cada quadrado: " + quadradoLargura + " x " + quadradoAltura);

            // adiciona pontos no centro de alguns quadrados para teste
            pontosParaTestar.add(new Coordenada(quadradoLargura / 2, quadradoAltura / 2)); // Quadrado superior esquerdo
            pontosParaTestar.add(new Coordenada(quadradoLargura * 2 + quadradoLargura / 2, quadradoAltura / 2)); // Quadrado superior meio
            pontosParaTestar.add(new Coordenada(quadradoLargura / 2, quadradoAltura * 2 + quadradoAltura / 2)); // Quadrado meio esquerdo
            pontosParaTestar.add(new Coordenada(largura / 2, altura / 2)); // Centro da imagem

            System.out.println("\n  FLOOD FILL COM PILHA (UM QUADRADO) ===");

            // escolhe o primeiro ponto valido (nao pode ser preto)
            Coordenada pontoEscolhido = null;
            for (Coordenada ponto : pontosParaTestar) {
                Color corNoPonto = new Color(imagemOriginal.getRGB(ponto.getX(), ponto.getY()));
                System.out.println(" Testando ponto " + ponto + " - Cor: RGB(" +
                        corNoPonto.getRed() + ", " + corNoPonto.getGreen() + ", " + corNoPonto.getBlue() + ")");

                // se for não preto, usa este ponto
                if (!(corNoPonto.getRed() == 0 && corNoPonto.getGreen() == 0 && corNoPonto.getBlue() == 0)) {
                    pontoEscolhido = ponto;
                    System.out.println(" Ponto selecionado: " + ponto + " (não é borda preta)");
                    break;
                }
            }

            if (pontoEscolhido == null) {
                System.err.println("Não foi possível encontrar um ponto válido para preenchimento!");
                return;
            }

            System.out.println(" Pintando UM quadrado com VERDE usando PILHA...");
            System.out.println(" Ponto inicial: " + pontoEscolhido);

            floodFill.floodFillComPilha(pontoEscolhido.getX(), pontoEscolhido.getY(), Color.GREEN);
            floodFill.salvarImagemResultado("resultado_um_quadrado_pilha.png");
            floodFill.salvarAnimacao("animacao_um_quadrado_pilha");

            System.out.println("Preenchimento com PILHA concluído!");

            // aqui ele vai resetar a imagem para poder rodar em FILA
            floodFill.resetarImagem(caminhoEntrada);


            System.out.println("\n FLOOD FILL COM FILA (UM QUADRADO)");

            System.out.println(" Pintando o MESMO quadrado com VERDE usando FILA...");
            System.out.println(" Ponto inicial: " + pontoEscolhido);

            floodFill.floodFillComFila(pontoEscolhido.getX(), pontoEscolhido.getY(), Color.GREEN);
            floodFill.salvarImagemResultado("resultado_um_quadrado_fila.png");
            floodFill.salvarAnimacao("animacao_um_quadrado_fila");

            System.out.println(" Preenchimento com FILA concluído!");

            System.out.println("\n --- PROCESSAMENTO CONCLUÍDO COM SUCESSO ---");
            System.out.println(" Arquivos que foram gerados:");
            System.out.println("    resultado_um_quadrado_pilha.png (um quadrado verde - pilha)");
            System.out.println("    resultado_um_quadrado_fila.png (um quadrado verde - fila)");

            System.out.println("    animacao_um_quadrado_pilha/ (animação - pilha)");
            System.out.println("    animacao_um_quadrado_fila/ (animação - fila)");

            //sessao de tratamento de erros da imagem
        } catch (IOException e) {
            System.err.println(" Erro ao processar imagem: " + e.getMessage());
            System.err.println("Certifique-se de que:");
            System.err.println("1. O arquivo 'tde1_.png' existe na pasta 'src/'");
            System.err.println("2. O arquivo não esta corrompido");
            System.err.println("3. Voce tem permissao para ler o arquivo");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(" Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

}