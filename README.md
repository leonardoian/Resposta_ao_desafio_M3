## Leonardo Ian de Oliveira
## Atividade - Resposta ao Desafio M3

# 🏦 Conta Bancária Distribuída - Java RMI

Este projeto é uma aplicação cliente-servidor desenvolvida em Java que simula as operações básicas de uma conta bancária utilizando **RMI (Remote Method Invocation)**. O sistema permite que múltiplos clientes acessem e modifiquem o saldo da mesma conta remotamente de forma segura e concorrente.

## 🚀 Funcionalidades

* **Consulta de Saldo:** Retorna o valor atual da conta.
* **Depósito:** Adiciona um valor ao saldo existente.
* **Saque (Retirada):** Deduz um valor do saldo (com validação de saldo suficiente).
* **Controle de Concorrência:** Utilização da palavra-chave `synchronized` nos métodos remotos para garantir a integridade dos dados (evitando *race conditions* quando múltiplos clientes tentam sacar ou depositar ao mesmo tempo).

## 📂 Estrutura do Código

O projeto é composto por quatro arquivos principais:

* `ContaBancaria.java`: A interface remota que define os métodos (contrato) que o servidor disponibiliza.
* `ContaBancariaImpl.java`: A implementação das regras de negócio e do controle de concorrência.
* `ContaBancariaServer.java`: O servidor que inicializa o serviço de registro RMI (`rmiregistry` na porta 1099) e publica o objeto da conta.
* `ContaBancariaClient.java`: A aplicação cliente que consome o serviço remoto através de um menu interativo no terminal.

---

## 🛠️ Passo a Passo para Execução (VS Code / Codespaces)

Para testar esta aplicação corretamente, você precisará rodar o servidor e os clientes ao mesmo tempo. Siga os passos exatos abaixo:

### Passo 1: Compilar o código
1. Abra o terminal integrado no VS Code / Codespaces.
2. Certifique-se de estar na raiz do projeto (onde os arquivos `.java` estão).
3. Digite o comando abaixo e pressione **Enter**:
   
   javac *.java

## Passo 2: Iniciar o Servidor Remoto
1. No mesmo terminal, inicie o servidor digitando o comando:
    
    java ContaBancariaServer

2. O terminal exibirá uma mensagem indicando que o servidor está aguardando conexões. Não feche e não pare a execução deste terminal.

## Passo 3: Abrir um novo Terminal para o Cliente
1. Olhe para a barra superior do painel do terminal (onde estão escritas as abas "PROBLEMAS", "SAÍDA", "TERMINAL").
2. Clique no ícone Dividir Terminal (um ícone de um retângulo dividido ao meio, localizado no lado direito, perto da lixeira).
3. Um novo terminal em branco será aberto ao lado do terminal do servidor.

## Passo 4: Executar o Cliente 
1. Neste novo terminal, inicie o aplicativo cliente digitando:
    java ContaBancariaClient

2. O menu interativo da conta bancária aparecerá. Siga as instruções na tela para consultar o saldo, depositar ou sacar.

## Passo 5: Testar a Concorrência (Múltiplos Clientes)
1. Repita o Passo 3 para abrir uma terceira aba de terminal.
2. Repita o Passo 4 para iniciar um segundo cliente simultâneo.
3. Tente realizar operações nos dois terminais de clientes ao mesmo tempo. Você notará que o servidor processa as requisições de forma ordenada, mantendo o saldo unificado e consistente.
