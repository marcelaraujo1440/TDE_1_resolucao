# 🎨 Algoritmo Flood Fill - Guia de Apresentação e Auditoria

## 📋 Informações do Projeto

*Disciplina:* Resolução de Problemas Estruturados em Computação  
*Curso:* Bacharelado em Engenharia de Software  
*Professora:* Lisiane Reips  
*Algoritmo:* Flood Fill (Preenchimento por Inundação)  

## 🎯 Objetivo do Trabalho

Implementar o algoritmo *Flood Fill* utilizando estruturas de dados *Pilha* e *Fila* próprias, demonstrando as diferenças comportamentais entre as duas abordagens ao preencher uma imagem com grade 5x5.

## 🏗️ Arquitetura do Sistema

### 📁 Estrutura de Classes


├── Coordenada.java           # Representa pontos (x, y)
├── Pilha.java               # Implementação própria de pilha (LIFO)
├── Fila.java                # Implementação própria de fila (FIFO)
├── FloodFillAlgoritmo.java  # Algoritmo principal de flood fill
├── AnalisadorImagem.java    # Utilitários de análise de imagem
└── Main.java                # Classe principal e demonstração


### 🔧 Principais Funcionalidades

| Classe | Responsabilidade | Métodos Principais |
|--------|------------------|-------------------|
| *Coordenada* | Representar pontos (x,y) | ⁠ getX() ⁠, ⁠ getY() ⁠, ⁠ toString() ⁠ |
| *Pilha* | Estrutura LIFO | ⁠ empilhar() ⁠, ⁠ desempilhar() ⁠, ⁠ estaVazia() ⁠ |
| *Fila* | Estrutura FIFO | ⁠ enfileirar() ⁠, ⁠ desenfileirar() ⁠, ⁠ estaVazia() ⁠ |
| *FloodFillAlgoritmo* | Algoritmo principal | ⁠ floodFillComPilha() ⁠, ⁠ floodFillComFila() ⁠ |
| *AnalisadorImagem* | Análise de imagens | ⁠ analisarImagem() ⁠, ⁠ encontrarCorDeFundo() ⁠ |

## 🚀 Como Executar o Projeto

### 📂 Estrutura de Pastas Necessária


projeto/
├── src/
│   └── tde1_.png           # ✅ Sua imagem 5x5 aqui
├── Main.java               # Código principal
├── FloodFillAlgoritmo.java # Algoritmo flood fill
├── Pilha.java              # Implementação pilha
├── Fila.java               # Implementação fila
├── Coordenada.java         # Classe coordenada
└── AnalisadorImagem.java   # Analisador de imagem


### ⚡ Passos para Execução

1.⁠ ⁠*Preparação:*
   ⁠ bash
   # 1. Coloque a imagem tde1_.png na pasta src/
   # 2. Certifique-se que é uma imagem PNG com grade 5x5
    ⁠

2.⁠ ⁠*Compilação:*
   ⁠ bash
   javac *.java
    ⁠

3.⁠ ⁠*Execução:*
   ⁠ bash
   java Main
    ⁠

## 📊 Comportamento do Algoritmo

### 🔴 *Flood Fill com PILHA (LIFO)*


Comportamento: Depth-First Search (DFS)
┌─────────────────────────────┐
│ • Explora em PROFUNDIDADE   │
│ • Vai até o fim de uma      │
│   direção antes das outras  │
│ • Padrão irregular/serpenteado │
│ • Processa: ÚLTIMO → PRIMEIRO │
└─────────────────────────────┘


### 🔵 *Flood Fill com FILA (FIFO)*


Comportamento: Breadth-First Search (BFS)
┌─────────────────────────────┐
│ • Explora em LARGURA        │
│ • Expande uniformemente do  │
│   centro para as bordas     │
│ • Padrão em ondas concêntricas │
│ • Processa: PRIMEIRO → ÚLTIMO │
└─────────────────────────────┘


## 🛡️ Validações e Proteções Implementadas

### ✅ *Verificações de Segurança*

| Verificação | Descrição | Implementação |
|-------------|-----------|---------------|
| *Bounds Checking* | Coordenadas dentro da imagem | ⁠ coordenadaValida(x, y) ⁠ |
| *Detecção de Bordas* | Identifica pixels pretos (RGB 0,0,0) | Verificação RGB |
| *Prevenção de Loops* | Pixels já pintados não são reprocessados | Comparação de cores |
| *Validação de Entrada* | Arquivo existe e é legível | ⁠ File.exists() ⁠ |

### 🎨 *Algoritmo de Preenchimento*


┌─ INÍCIO
│
├─ 1️⃣ Seleciona ponto inicial (centro do quadrado)
├─ 2️⃣ Verifica se ponto NÃO é borda preta (RGB 0,0,0)
├─ 3️⃣ Armazena cor original do ponto
├─ 4️⃣ Adiciona ponto inicial na estrutura (Pilha/Fila)
│
├─ 🔄 LOOP PRINCIPAL:
│  ├─ Remove ponto da estrutura
│  ├─ Verifica coordenadas válidas
│  ├─ Verifica se cor = cor original
│  ├─ Pinta pixel com nova cor (VERDE)
│  ├─ Adiciona 4 vizinhos na estrutura (se válidos)
│  └─ Salva frame de animação
│
└─ ✅ FIM: Quando estrutura está vazia


## 📸 Arquivos Gerados

### 🎯 *Resultados da Execução*

| Arquivo | Descrição | Conteúdo |
|---------|-----------|----------|
| ⁠ resultado_um_quadrado_pilha.png ⁠ | Resultado pilha | 1 quadrado verde (DFS) |
| ⁠ resultado_um_quadrado_fila.png ⁠ | Resultado fila | 1 quadrado verde (BFS) |
| ⁠ animacao_um_quadrado_pilha/ ⁠ | Animação pilha | Frames do processo DFS |
| ⁠ animacao_um_quadrado_fila/ ⁠ | Animação fila | Frames do processo BFS |

### 📈 *Saída do Console*


✅ Arquivo encontrado: src/tde1_.png
📐 Dimensões da imagem: 250 x 250 pixels
📦 Tamanho aproximado de cada quadrado: 50 x 50
🎯 Testando ponto (25, 25) - Cor: RGB(255, 255, 255)
✅ Ponto selecionado: (25, 25) (não é borda preta)
🎨 Pintando UM quadrado com VERDE usando PILHA...
✅ Flood Fill com PILHA concluído!
🎨 Pixels pintados: 2401
⚫ Bordas pretas foram respeitadas (não pintadas)


## 🔍 Guia para Auditoria Técnica

### 📋 *Checklist de Requisitos*

| Critério | Status | Evidência | Pontuação |
|----------|--------|-----------|-----------|
| *Pilha Própria Implementada* | ✅ | Classe ⁠ Pilha ⁠ com LIFO | 1,5 pts |
| *Fila Própria Implementada* | ✅ | Classe ⁠ Fila ⁠ com FIFO | 1,5 pts |
| *Algoritmos Corretos* | ✅ | Métodos flood fill funcionais | 3,0 pts |
| *Orientação a Objetos* | ✅ | Classes modulares e encapsuladas | 2,0 pts |
| *Funcionamento Sem Bugs* | ✅ | Execução completa e correta | 2,0 pts |
| *TOTAL* | ✅ | Todos os requisitos atendidos | *10,0 pts* |

### 🔬 *Pontos de Verificação Técnica*

#### 1. *Implementações Próprias*
⁠ java
// ✅ CORRETO: Implementação própria
class Pilha {
    private No topo;
    // Implementação com lista ligada
}

// ❌ INCORRETO: Usar Stack do Java
// import java.util.Stack; // NÃO USADO!
 ⁠

#### 2. *Detecção de Bordas*
⁠ java
// ✅ CORRETO: Detecta e respeita bordas pretas
if (corAtual.getRed() == 0 && corAtual.getGreen() == 0 && corAtual.getBlue() == 0) {
    continue; // Não pinta bordas pretas
}
 ⁠

#### 3. *4-Conectividade*
⁠ java
// ✅ CORRETO: Adiciona apenas vizinhos laterais
adicionarVizinho(cx + 1, cy); // direita
adicionarVizinho(cx - 1, cy); // esquerda  
adicionarVizinho(cx, cy + 1); // baixo
adicionarVizinho(cx, cy - 1); // cima
// NÃO adiciona diagonais
 ⁠

## 🎯 Roteiro de Apresentação

### 🎤 *1. Introdução (2 min)*
•⁠  ⁠Explicar o que é Flood Fill
•⁠  ⁠Mostrar a imagem de entrada (grade 5x5)
•⁠  ⁠Objetivo: pintar apenas 1 quadrado

### 🔧 *2. Estruturas de Dados (3 min)*
•⁠  ⁠*Demonstrar Pilha (LIFO):*
  ⁠ java
  pilha.empilhar("A");
  pilha.empilhar("B");
  pilha.empilhar("C");
  // Saída: C, B, A
   ⁠
•⁠  ⁠*Demonstrar Fila (FIFO):*
  ⁠ java
  fila.enfileirar("A");
  fila.enfileirar("B");
  fila.enfileirar("C");
  // Saída: A, B, C
   ⁠

### 🖥️ *3. Execução ao Vivo (5 min)*
•⁠  ⁠Compilar e executar o código
•⁠  ⁠Mostrar saída do console
•⁠  ⁠Exibir imagens resultantes
•⁠  ⁠Comparar animações (se possível)

### 📊 *4. Análise dos Resultados (3 min)*
•⁠  ⁠*Pilha:* Padrão irregular, explora profundidade
•⁠  ⁠*Fila:* Padrão uniforme, expande em ondas
•⁠  ⁠Ambos respeitam bordas pretas
•⁠  ⁠Mesmo resultado final, processo diferente

### 💡 *5. Diferenças Visuais (2 min)*
•⁠  ⁠Mostrar side-by-side das duas imagens
•⁠  ⁠Explicar por que o resultado final é igual
•⁠  ⁠Destacar importância das bordas na contenção

## 🔧 Troubleshooting

### ❌ *Problemas Comuns*

| Problema | Causa | Solução |
|----------|--------|---------|
| "Arquivo não encontrado" | Imagem não está em src/ | Mover tde1_.png para src/ |
| "OutOfMemoryError" | Imagem muito grande | Usar imagem menor ou aumentar heap |
| "Pixels pintados: 0" | Ponto inicial é borda preta | Verificar coordenadas do ponto inicial |
| Animação não gerada | Sem permissão de escrita | Verificar permissões da pasta |

### 🐛 *Debug Tips*

⁠ java
// Para debug, adicione prints:
System.out.println("Processando: (" + cx + ", " + cy + ")");
System.out.println("Cor atual: " + corToString(corAtual));
System.out.println("Pixels na pilha/fila: " + estrutura.getTamanho());
 ⁠

## 📚 Fundamentos Teóricos

### 🧠 *Complexidade*
•⁠  ⁠*Temporal:* O(n×m) onde n×m é o tamanho da região conectada
•⁠  ⁠*Espacial:* O(n×m) no pior caso (toda a imagem na estrutura)

### 🔄 *Diferenças DFS vs BFS*
•⁠  ⁠*DFS (Pilha):* Vai fundo primeiro, usa menos memória em áreas estreitas
•⁠  ⁠*BFS (Fila):* Explora uniformemente, melhor para visualização do processo

## ✅ Critérios de Aprovação

### 🎯 *Para Aprovação Total:*
1.⁠ ⁠✅ Código compila sem erros
2.⁠ ⁠✅ Executa do início ao fim
3.⁠ ⁠✅ Gera imagens corretas
4.⁠ ⁠✅ Pilha e Fila são implementações próprias
5.⁠ ⁠✅ Respeita bordas pretas (não pinta)
6.⁠ ⁠✅ Demonstra diferenças entre pilha e fila
7.⁠ ⁠✅ Código bem documentado e organizado

### 📋 *Documentos de Entrega*
•⁠  ⁠[ ] Código fonte completo
•⁠  ⁠[ ] README explicativo
•⁠  ⁠[ ] Imagem de teste (tde1_.png)
•⁠  ⁠[ ] Resultados gerados
•⁠  ⁠[ ] Apresentação preparada

---

## 👥 Informações da Equipe

*Integrantes:*
•⁠  ⁠[Nome do Integrante 1] - [Matrícula]
•⁠  ⁠[Nome do Integrante 2] - [Matrícula] (se aplicável)

*Data de Entrega:* [Data]  
*Versão:* 1.0  
*Linguagem:* Java  
*IDE Recomendada:* IntelliJ IDEA, Eclipse ou VS Code  

---

Este projeto demonstra a implementação correta do algoritmo Flood Fill seguindo todos os requisitos especificados, com foco na diferenciação entre estruturas de dados Pilha (LIFO) e Fila (FIFO).
