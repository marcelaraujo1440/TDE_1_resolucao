
class Pilha {
    private No topo;
    private int tamanho;

    private class No {
        Coordenada coord;
        No proximo;

        No(Coordenada coord) {
            this.coord = coord;
        }
    }

    public Pilha() {
        topo = null;
        tamanho = 0;
    }

    public void empilhar(Coordenada coord) {
        No novo = new No(coord);
        novo.proximo = topo;
        topo = novo;
        tamanho++;
    }

    public Coordenada desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Coordenada coord = topo.coord;
        topo = topo.proximo;
        tamanho--;
        return coord;
    }


    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}