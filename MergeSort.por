programa {
  const inteiro tamanho = 10

  funcao inicio(){
    inteiro a[tamanho] = { 6, 18, 23, 18, 90, 35, 21, 8, 99, 1 }
    inteiro ini = 0
    inteiro fim = tamanho - 1

    para(inteiro x = 0; x < tamanho; x++) {
      escreva (a[x], "\n")
    }

    MergeSort(a, ini, fim)
    escreva ("Ordenado\n")

    para(inteiro x = 0; x < tamanho; x++) {
      escreva (a[x], "\n")
    }
  }

  funcao MergeSort(inteiro a[], inteiro ini, inteiro fim ){
    se (ini <  fim) {
      inteiro meio = ini + (fim - ini) / 2

      escreva("a\n")

      MergeSort (a, ini, meio)
      MergeSort (a, meio+1, fim)

      escreva("b\n")
      escreva("ini ", ini, " meio ", meio, " fim ", fim, "\n")
      Merge (a, ini, meio, fim)
    }
  }

  funcao Merge (inteiro a[], inteiro ini, inteiro meio, inteiro fim){
    inteiro inicioArrayLength = meio - ini + 1
    inteiro fimArrayLength = fim - meio

    se(inicioArrayLength <= 0){
      inicioArrayLength = 0
    }

    inteiro inicioTempArray[inicioArrayLength]
    inteiro fimTempArray[fimArrayLength]
    inteiro i, j

    para (i = 0; i < inicioArrayLength; i++) {
      inicioTempArray[i] = a[ini + i]
    }
    para (j = 0; j < fimArrayLength; j++) {
      fimTempArray[j] = a[meio + 1 + j]
    }

    i = 0
    j = 0
    inteiro k = ini

    enquanto (i < inicioArrayLength e j < fimArrayLength) {
      se (inicioTempArray[i] <= fimTempArray[j]) {
        a[k] = inicioTempArray[i]
        k++
        i++
      } senao {
        a[k] = fimTempArray[j]
        k++
        j++
      }
    }

    enquanto(i < inicioArrayLength){
      a[k] = inicioTempArray[i]
      k++
      i++
    }

    enquanto(j < fimArrayLength){
      a[k] = fimTempArray[j]
      k++
      j++
    }
  }
}
