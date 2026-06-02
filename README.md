========================================================================
             INSTRUÇÕES DE COMPILAÇÃO E EXECUÇÃO - JAVA RMI
========================================================================

Este projeto consiste em uma aplicação de Conta Bancária Distribuída utilizando
Java RMI (Remote Method Invocation). O servidor gerencia o estado centralizado
da conta e múltiplos clientes podem se conectar concorrentemente.

Arquivos inclusos na aplicação:
  - ContaBancaria.java       (Interface Remota)
  - ContaBancariaImpl.java   (Implementação do Objeto Remoto)
  - ContaBancariaServer.java (Programa Servidor)
  - ContaBancariaClient.java (Programa Cliente Interativo)

------------------------------------------------------------------------
PASSO 1: PRÉ-REQUISITOS
------------------------------------------------------------------------
Certifique-se de que o Java Development Kit (JDK 8 ou superior) está instalado
e que os comandos 'javac' e 'java' estão configurados nas variáveis de ambiente.

------------------------------------------------------------------------
PASSO 2: COMPILAÇÃO
------------------------------------------------------------------------
Abra o terminal do seu sistema operacional na pasta onde se encontram os
quatro arquivos de código-fonte (.java) e execute o comando abaixo para
compilar todos os arquivos simultaneamente:

  javac *.java

Nota: Em versões modernas do Java (Java 5+), os stubs e skeletons dinâmicos
são gerados em tempo de execução automaticamente, tornando o uso do utilitário 
'rmic' obsoleto.

------------------------------------------------------------------------
PASSO 3: EXECUÇÃO DO SERVIDOR
------------------------------------------------------------------------
Ainda na pasta do projeto, execute a classe que inicia o registro RMI e 
hospeda a aplicação servidora:

  java ContaBancariaServer

O terminal exibirá a mensagem indicando que o servidor está pronto e ativo.
Mantenha este terminal aberto.

------------------------------------------------------------------------
PASSO 4: EXECUÇÃO DOS CLIENTES (TESTE DE CONCORRÊNCIA)
------------------------------------------------------------------------
Para testar o acesso compartilhado de vários clientes simultaneamente:

1. Abra um NOVO terminal (ou prompt de comando) na pasta do projeto.
2. Execute o programa cliente com o comando:
   
     java ContaBancariaClient

3. Repita o processo abrindo um TERCEIRO terminal e executando o mesmo comando 
   do cliente novamente.

Agora você terá múltiplas instâncias clientes operando interativamente e
em tempo real sobre o saldo unificado armazenado no servidor. Assegurado 
pelo modificador 'synchronized', os saques concorrentes não gerarão 
inconsistências de saldo negativo ou concorrência desordenada.
========================================================================
