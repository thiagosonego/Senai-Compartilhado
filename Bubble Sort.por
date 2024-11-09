programa {
  
  const inteiro tamanho = 20

  inteiro valores[tamanho] = { 6, 7, 10, 9, 2, 3, 20, 18, 33, 90, 44, 34, 30, 21, 27, 5, 8, 99, 54, 1 }

  funcao inicio() {
    escreva("Valores na ordem original:\n")
    para(inteiro i = 0; i < tamanho; i++){
      escreva(valores[i], " ")
    }
    escreva("\n")
    bubblesort(tamanho)
    para(inteiro i = 0; i < tamanho; i++){
      escreva(valores[i], " ")
    }
  }

  funcao bubblesort(inteiro tamnhoAtual){
    se(tamnhoAtual > 0){
      para(inteiro i = 0; i < tamnhoAtual; i++){
        se(i+1 < tamanho){
          se(valores[i] > valores[i+1]){
            inteiro temp = valores[i]
            valores[i] = valores[i+1]
            valores[i+1] = temp
          }
        }
      }
      bubblesort(tamnhoAtual-1)
    }
  }
}
