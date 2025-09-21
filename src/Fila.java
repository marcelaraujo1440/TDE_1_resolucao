class Fila {
    private No frente, tras;
    private int tamanho;

    private class No {
        Coordenada coord;
        No proximo;

        No(Coordenada coord) {
            this.coord = coord;
        }
    }


    public Fila() {
        frente = tras = null;
        tamanho = 0;
    }

    public void enfileirar(Coordenada coord) {
        No novo = new No(coord);
        if (tras == null) {
            frente = tras = novo;
        } else {
            tras.proximo = novo;
            tras = novo;
        }
        tamanho++;
    }

    public Coordenada desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        Coordenada coord = frente.coord;
        frente = frente.proximo;
        if (frente == null) {
            tras = null;
        }
        tamanho--;
        return coord;
    }

    public boolean estaVazia() {
        return frente == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
