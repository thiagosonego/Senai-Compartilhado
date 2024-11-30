programa {
   // Declaração do tamanho do vetor e dos elementos
  const inteiro n = 10
  inteiro vetorA[n] = { 6, 18, 23, 55, 90, 35, 21, 8, 99, 1 }

  funcao inicio(){
    imprimirVetor(vetorA, n)

    inteiro vetorB[n] = {mergeSort(vetorA, n)}
    escreva ("Ordenado\n")

    escreva(vetorB)
  }

  funcao imprimirVetor(inteiro vetor[], inteiro n){
    // Impressão do vetor
    para(inteiro x = 0; x < n; x++) {
      escreva (vetor[x], "\n")
    }
  }

  funcao inteiro mergeSort(inteiro vetor[], inteiro tamanho){
    se (tamanho > 1) {
      inteiro meio = tamanho / 2
      inteiro tamanhoEsquerda = tamanho - meio
      inteiro tamanhoDireita = tamanho - tamanhoEsquerda
      inteiro esquerda[tamanhoEsquerda]
      inteiro direita[tamanhoDireita]

      // Copiando os dados para os arrays auxiliares
      para (inteiro i = 0; i < tamanhoEsquerda; i++) {
        esquerda[i] = vetor[i]
      }
      para (inteiro j = 0; j < tamanhoDireita; j++) {
        direita[j] = vetor[tamanhoEsquerda + j]
      }

      // Separado em duas listas recursivamente
      esquerda = mergeSort(esquerda, tamanhoEsquerda)
      direita = mergeSort(direita, tamanhoDireita)

      // Junta as duas metades
      retorne merge(esquerda, direita, tamanhoEsquerda, tamanhoDireita, tamanho)
    }
    retorne vetor
  }




  funcao inteiro merge(
    inteiro esquerda[],
    inteiro direita[],
    inteiro tamanhoEsquerda,
    inteiro tamanhoDireita,
    inteiro tamanhoTotal)
  {
    inteiro vetorNovo[tamanhoTotal]
    inteiro i, j, k

    // Mesclando os arrays auxiliares de volta para o vetor original
    i = 0
    j = 0
    k = 0

    enquanto (i < tamanhoEsquerda e j < tamanhoDireita) {
      se (esquerda[i] <= direita[j]) {
          vetorNovo[k] = esquerda[i]
          i++
      }senao{
          vetorNovo[k] = direita[j]
          j++
      }
      k++
    }

    // Copiando os elementos restantes, se houver
    enquanto (i < tamanhoEsquerda) {
      vetorNovo[k] = esquerda[i]
      i++
      k++
    }
    enquanto (j < tamanhoDireita) {
      vetorNovo[k] = direita[j]
      j++
      k++
    }

    retorne vetorNovo
  }
}
