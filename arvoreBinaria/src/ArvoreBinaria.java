import java.util.Scanner;

public class ArvoreBinaria {
    No raiz;

    // Método para adicionar um novo nó na árvore
    public void adicionarNo(int info) {
        raiz = addRecursividade(raiz, info);
    }
    // Método privado auxiliar para adicionar um nó recursivamente
    private No addRecursividade(No raiz, int info) {
        // Se a raiz for nula, cria um novo nó com a informação e o torna a raiz
        if (raiz == null) {
            raiz = new No(info);
            return raiz;
        }
        // Compara a informação com a informação da raiz e insere à esquerda ou à direita com base nessa comparação
        if (info < raiz.getInfo()) {
            raiz.setEsquerda(addRecursividade(raiz.getEsquerda(), info));
        } else if (info > raiz.getInfo()) {
            raiz.setDireito(addRecursividade(raiz.getDireito(), info));
        }
        return raiz;
    }

    // Método para percorrer a árvore em ordem
    public void percorrerInOrdem(No noAtual) {
        if (noAtual != null) {
            percorrerInOrdem(noAtual.getEsquerda());  // Primeiro, visita a subárvore esquerda
            System.out.println(noAtual.getInfo());    // Em seguida, processa (imprime) o nó atual
            percorrerInOrdem(noAtual.getDireito());   // Por fim, visita a subárvore direita
        }
    }


    // Método para percorrer a árvore em pré-ordem
    public void percorrerPreOrdem(No noAtual) {
        if (noAtual != null) {
            System.out.println(noAtual.getInfo());    // Primeiro, processa (imprime) o nó atual
            percorrerPreOrdem(noAtual.getEsquerda());  // Em seguida, visita a subárvore esquerda
            percorrerPreOrdem(noAtual.getDireito());   // Por fim, visita a subárvore direita
        }
    }


    // Método para percorrer a árvore em pós-ordem
    public void percorrerPosOrdem(No noAtual) {
        if (noAtual != null) {
            percorrerPosOrdem(noAtual.getEsquerda());  // Primeiro, visita a subárvore esquerda
            percorrerPosOrdem(noAtual.getDireito());   // Em seguida, visita a subárvore direita
            System.out.println(noAtual.getInfo());    // Por fim, processa (imprime) o nó atual
        }
    }


    // Método para encontrar um nó com uma informação específica
    public No encontrarNo(int info) {
        No noAtual = raiz;
        while (noAtual != null) {
            // Compara a informação do nó atual com a informação desejada
            if (info == noAtual.getInfo()) {
                System.out.println("Nó: " + info + " encontrado.");
                return noAtual;
            } else if (info < noAtual.getInfo()) {
                // Se a informação desejada for menor, move-se para a subárvore esquerda
                noAtual = noAtual.getEsquerda();
            } else {
                // Caso contrário, move-se para a subárvore direita
                noAtual = noAtual.getDireito();
            }
        }
        System.out.println("Nó: " + info + " não encontrado");
        return null;
    }

    // Método para encontrar e imprimir os filhos de um nó com uma informação específica
    public void encontrarFilhos(int info) {
        No noPai = encontrarNo(info);

        if (noPai != null) {
            No filhoEsquerda = noPai.getEsquerda();
            No filhoDireita = noPai.getDireito();

            if (filhoEsquerda != null) {
                // Imprime o filho à esquerda do nó pai
                System.out.println("Filho esquerda de " + noPai.getInfo() + ": " + filhoEsquerda.getInfo());
            } else {
                // Se não houver filho à esquerda, imprime que não existe
                System.out.println(noPai.getInfo() + " não possui Filho esquerda.");
            }

            if (filhoDireita != null) {
                // Imprime o filho à direita do nó pai
                System.out.println("Filho direito de " + noPai.getInfo() + ": " + filhoDireita.getInfo());
            } else {
                // Se não houver filho à direita, imprime que não existe
                System.out.println(noPai.getInfo() + " não possui Filho direito.");
            }
        } else {
            // Se o nó pai não foi encontrado, imprime que a informação desejada não foi encontrada
            System.out.println("Nó com a info " + info + " não encontrado na árvore.");
        }
    }

    // Usando a regra: utilizar o menor elemento da subárvore direita para “substituir” o elemento a ser retirado.
    private int encontrarMenorValor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no.getInfo();
    }

    // Método para remover um nó da árvore
    public void removerNo(int info) {
        raiz = removerRecursividade(raiz, info);
    }
    // Função recursiva para remover um nó específico da árvore
    private No removerRecursividade(No no, int info) {
        if (no == null) {
            return null;
        }
        if (info < no.getInfo()) {
            // Se o valor a ser removido for menor que o valor atual do nó, vá para a subárvore esquerda
            no.setEsquerda(removerRecursividade(no.getEsquerda(), info)); // A linha ajusta a subárvore esquerda do nó, removendo o nó com valor 'info' através de uma chamada recursiva.
        } else if (info > no.getInfo()) {
            // Se o valor a ser removido for maior que o valor atual do nó, vá para a subárvore direita
            no.setDireito(removerRecursividade(no.getDireito(), info));
        } else {
            // Caso o nó tenha sido encontrado

            // Se o nó possui apenas um filho ou nenhum filho
            if (no.getDireito() == null) {
                // Se não houver subárvore direita, retorne a subárvore esquerda (ou null se não houver)
                return no.getEsquerda();
            } else if (no.getEsquerda() == null) {
                // Se não houver subárvore esquerda, retorne a subárvore direita
                return no.getDireito();
            }
            // Se o nó possui dois filhos, encontre o menor elemento da subárvore direita
            int menorValorDireita = encontrarMenorValor(no.getDireito());

            // Substitua o valor do nó pelo menor valor da subárvore direita
            no.setInfo(menorValorDireita);

            // Remova o nó com o menor valor da subárvore direita (chamando recursivamente)
            no.setDireito(removerRecursividade(no.getDireito(), menorValorDireita));
        }
        // Retorne o nó atual com as modificações (ou não) realizadas
        return no;
    }

    // Método para remover e retornar o maior valor da árvore
    public int removerMaior() {
        if (raiz == null) {
            // Se a árvore estiver vazia, imprima uma mensagem e retorne 0 (ou outro valor de sua escolha)
            System.out.println("A árvore está vazia.");
            return 0;
        }

        // Inicialize variáveis para rastrear o nó a ser removido e seu pai
        No maiorNo = raiz;
        No paiMaiorNo = null;

        // Encontre o nó mais à direita (o maior valor)
        while (maiorNo.getDireito() != null) {
            paiMaiorNo = maiorNo;
            maiorNo = maiorNo.getDireito();
        }

        // Se o maior nó for a raiz, substitua a raiz pela subárvore esquerda
        if (paiMaiorNo == null) {
            raiz = raiz.getEsquerda();
        } else {
            // Caso contrário, ajuste o ponteiro do pai para apontar para a subárvore esquerda do maior nó
            paiMaiorNo.setDireito(maiorNo.getEsquerda());
        }

        // Retorne o valor do maior nó
        return maiorNo.getInfo();
    }

    // Método para remover e retornar o menor valor da árvore
    public int removerMenor() {
        if (raiz == null) {
            // Se a árvore estiver vazia, imprima uma mensagem e retorne 0 (ou outro valor de sua escolha)
            System.out.println("A árvore está vazia.");
            return 0;
        }
        // Inicialize variáveis para rastrear o nó a ser removido e seu pai
        No menorNo = raiz;
        No paiMenorNo = null;
        // Encontre o nó mais à esquerda (o menor valor)
        while (menorNo.getEsquerda() != null) {
            paiMenorNo = menorNo;
            menorNo = menorNo.getEsquerda();
        }
        // Se o menor nó for a raiz, substitua a raiz pela subárvore direita
        if (paiMenorNo == null) {
            raiz = raiz.getDireito();
        } else {
            // Caso contrário, ajuste o ponteiro do pai para apontar para a subárvore direita do menor nó
            paiMenorNo.setEsquerda(menorNo.getDireito());
        }
        // Retorne o valor do menor nó
        return menorNo.getInfo();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNo(8);
        arvore.adicionarNo(3);
        arvore.adicionarNo(11);
        arvore.adicionarNo(1);
        arvore.adicionarNo(5);
        arvore.adicionarNo(9);
        arvore.adicionarNo(14);
        arvore.adicionarNo(6);
        arvore.adicionarNo(10);
        arvore.adicionarNo(12);
        arvore.adicionarNo(15);
        arvore.adicionarNo(7);
        arvore.adicionarNo(13);

        System.out.println("Percorrer Pre Ordem");
        arvore.percorrerPreOrdem(arvore.raiz);
        System.out.println("Percorrer In Ordem");
        arvore.percorrerInOrdem(arvore.raiz);
        System.out.println("Percorrer Pos Ordem");
        arvore.percorrerPosOrdem(arvore.raiz);

        System.out.println("Menor");
        System.out.println(arvore.removerMenor());

        System.out.println("Maior");
        System.out.println(arvore.removerMaior());

        // Remova o 11
        arvore.removerNo(11);

        // Mostrar filhos de 8
        arvore.encontrarFilhos(8);

    }
}