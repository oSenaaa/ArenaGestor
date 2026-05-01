# 🏟️ Arena Gestor

**Arena Gestor** é um sistema desktop desenvolvido em Java para o gerenciamento de locação de quadras esportivas. 

Este projeto foi construído como **Avaliação Contínua (AVC)** para as disciplinas de **Análise e Projeto de Sistemas** e **Programação Orientada a Objetos (POO)** da Universidade Santo Amaro (UNISA).

---

## 🎯 Objetivo do Projeto
Aplicar na prática os conceitos de levantamento de requisitos, arquitetura de software e os pilares da Orientação a Objetos (Herança, Polimorfismo, Encapsulamento) através da criação de uma interface gráfica amigável sem o uso de banco de dados complexos (armazenamento em arquivos `.txt`).

## ✨ Funcionalidades Principais
* **Dashboard em Tempo Real:** Visualização rápida do status de disponibilidade das quadras.
* **Gestão de Clientes:** Cadastro, edição e exclusão de clientes com formatação automática de CPF e Telefone (Máscaras) e validação de regras de negócio.
* **Gestão de Quadras:** Suporte a múltiplos tipos de quadras (Futsal, Tênis, Society, Campo e Vôlei) com valores dinâmicos.
* **Agendamento Inteligente:** Sistema de reservas que calcula automaticamente o valor total com base nas horas selecionadas e no tipo da quadra.
* **Busca Dinâmica:** Barra de pesquisa nas tabelas que filtra os resultados em tempo real (Search Filter).

## 🛠️ Tecnologias e Conceitos Aplicados
* **Linguagem:** Java (JDK 21)
* **Interface Gráfica:** Java Swing (JFrame, JPanel, CardLayout, JFormattedTextField)
* **Arquitetura:** Padrão MVC (Model, View, Repository)
* **Armazenamento:** Persistência de dados em arquivos de texto (`.txt`)
* **POO na Prática:**
  * **Herança & Polimorfismo:** Classes específicas de quadras (ex: `QuadraFutsal`, `QuadraTenis`) herdando da classe abstrata `Quadra`.
  * **Encapsulamento:** Regras rigorosas de validação de CPF ocultas na camada de Modelo (`Pessoa.java`).
  * **Tratamento de Exceções:** Blocos `try/catch` para evitar quebra do sistema com inputs inválidos ou erros de leitura de arquivo.

## 🚀 Como Executar o Projeto

Como este é um projeto acadêmico, a execução é bastante simples. Requer o **Java 21** ou superior instalado na máquina.

**Opção 1: Pelo Arquivo Executável **
1. Faça o download da pasta do projeto.
2. Certifique-se de que o arquivo `ArenaGestor.jar` está na mesma pasta que os arquivos `clientes.txt`, `quadras.txt` e `reservas.txt`.
3. Dê um duplo clique no arquivo `ArenaGestor.jar` para iniciar o sistema.

**Opção 2: Pelo Terminal (CMD/Mac Terminal)**
1. Navegue até a pasta raiz do projeto.
2. Execute o comando:
   ```bash
   java -jar ArenaGestor.jar