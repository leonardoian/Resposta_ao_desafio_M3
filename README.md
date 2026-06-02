# 🏦 Conta Bancária Distribuída — Java RMI

![Java](https://img.shields.io/badge/Java-RMI-orange?style=flat-square&logo=java)
![Status](https://img.shields.io/badge/status-concluído-brightgreen?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-blue?style=flat-square)

> Atividade — **Resposta ao Desafio M3** | Disciplina de Sistemas Distribuídos
> Autor: **Leonardo Ian de Oliveira**

Aplicação cliente-servidor desenvolvida em **Java** que simula as operações básicas de uma conta bancária utilizando **RMI (Remote Method Invocation)**. O sistema permite que **múltiplos clientes** acessem e modifiquem o saldo da **mesma conta** remotamente, de forma segura e concorrente.

---

## 📋 Sumário

- [Sobre o desafio](#-sobre-o-desafio)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Pré-requisitos](#-pré-requisitos)
- [Como executar](#️-como-executar)
- [Testando a concorrência](#-testando-a-concorrência)
- [Detalhes técnicos](#-detalhes-técnicos)
- [Autor](#-autor)

---

## 🎯 Sobre o desafio

> *"Desenvolva uma aplicação RMI onde um objeto remoto é uma conta bancária e vários clientes podem acessar essa conta. O objeto remoto deve implementar no mínimo os métodos de **deposita**, **retira** e **saldo**."*

Este repositório atende a todos os requisitos solicitados e adiciona melhorias como:

- ✅ Controle de concorrência com `synchronized`
- ✅ Validação de saldo na operação de saque
- ✅ Cliente em interface gráfica (Swing) — **bônus**
- ✅ Cliente em modo terminal com menu interativo

---

## 🚀 Funcionalidades

| Operação | Descrição |
|---|---|
| 💰 **Saldo** | Consulta o saldo atual da conta. |
| ⬆️ **Depósito** | Adiciona um valor ao saldo da conta. |
| ⬇️ **Saque** | Retira um valor, validando se há saldo suficiente. |
| 🔒 **Concorrência** | Métodos remotos sincronizados (`synchronized`) garantem integridade entre múltiplos clientes simultâneos. |

---

## 🛠️ Tecnologias

- **Java SE** (JDK 8 ou superior)
- **Java RMI** (`java.rmi.*`)
- **Swing** (para o cliente GUI)
- **rmiregistry** embutido na porta `1099`

---

## 📂 Estrutura do projeto

```
Resposta_ao_desafio_M3/
├── ContaBancaria.java          → Interface remota (contrato RMI)
├── ContaBancariaImpl.java      → Implementação do objeto remoto
├── ContaBancariaServer.java    → Servidor que registra o objeto no RMI
├── ContaBancariaClient.java    → Cliente em modo terminal (menu)
├── ContaBancariaClientGUI.java → Cliente em interface gráfica (Swing)
├── README.md                   → Este arquivo
└── README.txt                  → Instruções em texto puro (exigência da atividade)
```

### Interface remota

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContaBancaria extends Remote {
    void deposita(double valor) throws RemoteException;
    boolean retira(double valor) throws RemoteException;
    double saldo() throws RemoteException;
}
```

---

## 📦 Pré-requisitos

- **JDK 8+** instalado e configurado no `PATH`
- Terminal (cmd, PowerShell, bash ou terminal do VS Code)

Verifique a instalação com:

```bash
java -version
javac -version
```

---

## ▶️ Como executar

### Passo 1 — Clonar o repositório

```bash
git clone https://github.com/leonardoian/Resposta_ao_desafio_M3.git
cd Resposta_ao_desafio_M3
```

### Passo 2 — Compilar o código

Na raiz do projeto (onde estão os arquivos `.java`):

```bash
javac *.java
```

### Passo 3 — Iniciar o servidor

Em um terminal:

```bash
java ContaBancariaServer
```

> ⚠️ Mantenha este terminal aberto. O servidor exibirá uma mensagem indicando que está aguardando conexões na porta **1099**.

### Passo 4 — Executar o cliente (modo terminal)

Abra um **novo terminal** e rode:

```bash
java ContaBancariaClient
```

Um menu interativo aparecerá com as opções de saldo, depósito e saque.

### Passo 4b — Executar o cliente gráfico (opcional)

```bash
java ContaBancariaClientGUI
```

---

## 🧪 Testando a concorrência

Para validar o controle de acesso simultâneo:

1. Mantenha o servidor rodando no primeiro terminal.
2. Abra **dois ou mais** terminais adicionais.
3. Em cada um, execute `java ContaBancariaClient` (ou o GUI).
4. Realize operações simultâneas (ex.: deposite num terminal e consulte o saldo no outro).

Você verá que o saldo permanece **consistente e unificado** entre todos os clientes — graças ao `synchronized` nos métodos remotos.

> 💡 **Dica para VS Code / Codespaces:** use o ícone *Dividir Terminal* (canto superior direito do painel) para abrir várias abas lado a lado.

---

## 🔧 Detalhes técnicos

| Item | Valor |
|---|---|
| Porta do RMI Registry | `1099` |
| Nome do serviço no registry | `ContaBancaria` |
| Estratégia de concorrência | `synchronized` nos métodos da implementação |
| Tipo de dado do saldo | `double` |

### Como funciona o RMI nesta aplicação

```
┌─────────────────┐      Stub      ┌──────────────────┐
│   Cliente(s)    │ ─────────────► │   RMI Registry   │
│                 │   (lookup)     │   porta 1099     │
└─────────────────┘                └──────────────────┘
        │                                    │
        │                                    │ bind
        │  Invocação remota                  ▼
        │  (deposita/retira/saldo)   ┌──────────────────┐
        └───────────────────────────►│ ContaBancariaImpl│
                                     │  (objeto remoto) │
                                     └──────────────────┘
```

---

## 👤 Autor

**Leonardo Ian de Oliveira**
🎓 Graduação em Sistemas Distribuídos
📍 Canoas, RS — Brasil
🔗 [GitHub: @leonardoian](https://github.com/leonardoian)

---

<div align="center">

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!

</div>
