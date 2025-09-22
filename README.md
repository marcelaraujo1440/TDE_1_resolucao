# Flood Fill - Comparação de Algoritmos (Pilha vs Fila)

Este projeto implementa e compara duas variações do algoritmo **Flood Fill** (preenchimento de regiões em imagens):  
- Usando **Pilha (DFS - busca em profundidade)**  
- Usando **Fila (BFS - busca em largura)**  

A aplicação carrega uma imagem de entrada (`tde1_.png`), identifica pontos internos em regiões delimitadas por bordas pretas e realiza o preenchimento dessas áreas com a cor verde.  
Ao final, gera imagens de saída e animações que mostram a execução dos algoritmos.

---

## Estrutura do Projeto

- **`Main.java`**  
  Classe principal que:
  - Carrega e valida a imagem.
  - Analisa dimensões e subdivisões aproximadas (quadrados).
  - Seleciona pontos internos válidos (não pretos) para iniciar o preenchimento.
  - Executa o algoritmo Flood Fill usando **pilha** e **fila**.
  - Salva os resultados e animações.

- **`FloodFillAlgoritmo.java`**  
  Implementa os métodos de preenchimento:
  - `floodFillComPilha(x, y, cor)` → Preenchimento com **pilha**.
  - `floodFillComFila(x, y, cor)` → Preenchimento com **fila**.
  - Métodos auxiliares para salvar imagem final e animações.

- **`Coordenada.java`**  
  Classe simples para representar coordenadas (x, y).

- **`AnalisadorImagem.java`**  
  Classe auxiliar para análise inicial da imagem (estatísticas, validações, etc).

---

## Pré-requisitos

- **Java 8+**
- Uma imagem chamada **`tde1_.png`** localizada em `src/`.  
  - A imagem deve conter áreas delimitadas por **bordas pretas** (ex.: quadrados ou retângulos).

---

## Como Executar

1. Compile o projeto:
   ```bash
   javac Main.java
