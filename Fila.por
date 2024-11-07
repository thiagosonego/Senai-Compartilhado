programa {

  const inteiro tamanho = 5

  cadeia nomes[tamanho], auxiliar
  inteiro apontador = 0
  caracter opcao

  funcao inicio() {
    opcao = '0'

    escreva("1- Enfileirar (adiciona um elemento no final da fila)\n")
    escreva("2- Desenfileirar (remove e retorna um elemento do inicio da fila)\n")
    escreva("3- Exibir Inicio (exibe o próximo resultado sem remove-lo)\n")
    escreva("4- Exibir Quantidade (exibe a quantidade de itens atualmente na lista)\n")
    escreva("5- Esvaziar (remove todos os elementos da lista)\n")
    leia(opcao)
    escreva("\n")

    escolha (opcao){
      caso '1': {
        escreva("Valor a ser adicionado:\n")
        escreva("\n")
        leia(auxiliar)
        enfileirar(auxiliar)
      }
      caso '2': {
        escreva("Valor removido: ", desenfileirar())
        retorno()
      }
      caso '3': {
        exibeInicio()
      }
      caso '4': {
        exibeQuantidade()
      }
      caso '5': {
        escreva("Removendo itens:\n")
        escreva("\n")
        esvaziar()
      }
    }
  }

  funcao retorno(){
    escreva("\n")
    escreva("------------------------------")
    escreva("\n")
    escreva("\n")
    inicio()
  }

  funcao enfileirar(cadeia aux){
    se(apontador < tamanho){
      nomes[apontador] = aux
      apontador++
      escreva("valor adicionado ", aux, "\n")
    } senao {
      escreva("Lista Cheia!\n")
    }
    retorno()
  }

  funcao cadeia desenfileirar(){
    se(apontador > 0){
      cadeia proximoNome = nomes[0]
      apontador--
      para (inteiro i = 0; i < apontador; i ++){
        nomes[i] = nomes[i+1]
      }
      retorne proximoNome
    }
    retorne "Nenhum valor a retirar!"
  }

  funcao exibeInicio(){
    se(apontador > 0){
      escreva("O próximo resultado é: ", nomes[0])
    } senao {
      escreva("Nenhum valor na lista!")
    }
    retorno()
  }

  funcao exibeQuantidade(){
    escreva("A lista possui ", apontador, " nomes cadastrados")
    retorno()
  }

  funcao esvaziar (){
    para (inteiro i = 0; i < apontador; i ++){
      nomes[i] = ""
    }
    apontador = 0
    retorno()
  }
}