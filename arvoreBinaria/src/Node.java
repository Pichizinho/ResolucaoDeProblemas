class No {
    int info;
    No esquerda;
    No direito;

    public No(int info) {
        this.info = info;
        esquerda = null;
        direito = null;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

}