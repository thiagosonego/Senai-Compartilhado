programa {

  const inteiro tamanho = 20

  cadeia nomes[tamanho], auxiliar
  inteiro apontador = 0
  caracter opcao

  funcao inicio() {
    opcao = '0'

    escreva("1- Empilhar (adiciona um elemento no topo da pilha)\n")
    escreva("2- Desempilhar (remove e retorna um elemento do topo da pilha)\n")
    escreva("3- Limpar (remove todos os elementos da pilha)\n")
    escreva("4- Listar (lista todos os elementos armazenados na pilha)\n")
    escreva("5- Vazia (retorna verdadeiro se a pilha estiver vazia, e falso caso contrário)\n")
    leia(opcao)
    escreva("\n")
    escreva("\n")

    escolha (opcao){
      caso '1': {
        escreva("Valor a ser empilhado\n")
        leia(auxiliar)
        empilhar(auxiliar)
      }
      caso '2': {
        auxiliar = desempilhar()
        escreva("Valor removido: ", auxiliar)
        retorno()
      }
      caso '3': {
        limpar()
      }
      caso '4': {
        listar()
      }
      caso '5': {
        escreva("A lista está vazia: ", vazia())
        retorno()
      }
    }
  }

  funcao retorno(){
    escreva("\n")
    escreva("------------------------------")
    escreva("\n")
    inicio()
  }

  funcao empilhar(cadeia aux){
    se(apontador < tamanho){
      nomes[apontador] = aux
      apontador++
      escreva("valor adicionado ", aux, "\n")
    } senao {
      escreva("Lista Cheia!\n")
    }
    retorno()
  }

  funcao cadeia desempilhar(){
    se(apontador > 0){
      apontador--
      cadeia temporario = nomes[apontador]
      nomes[apontador] = ""
      retorne temporario
    }
    retorne "Nenhum valor a retirar!"
  }

  funcao limpar(){
    para(inteiro i = apontador; i >= 0; i--){
      nomes[i] = ""
    }
    apontador = 0
    retorno()
  }

  funcao listar(){
    para(inteiro i = apontador - 1; i >= 0; i--){
      escreva(i, " - ", nomes[i], "\n")
    }
    retorno()
  }

  funcao logico vazia (){
    se(apontador == 0){
      retorne verdadeiro
    } senao {
      retorne falso
    }
  }
}
