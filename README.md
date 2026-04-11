# 🏟️ Arena Gestor - Sistema de Controle de Quadras

Este é um sistema desktop desenvolvido em **Java** com interface gráfica **Swing** para gerenciar o aluguel de quadras esportivas. O projeto foca na aplicação prática de conceitos de **Programação Orientada a Objetos (POO)** e **Análise e Projeto de Sistemas**.

## 👥 Integrantes do Grupo
* Carlos Henrique
* Guilherme Jorge
* Jeferson Santos
* Lucas Sena
* Luigi Lima
* Matheus Madeira
* Pedro Guimarães

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21
* **IDE:** Eclipse IDE 2026
* **Interface:** Java Swing (WindowBuilder)
* **Persistência:** Arquivos de texto locais (.txt)

## 📂 Estrutura do Projeto
O código está organizado em pacotes para facilitar a manutenção e seguir as boas práticas de arquitetura:
* `model`: Contém as classes de dados e a "inteligência" do sistema. Aqui aplicamos conceitos como **Herança**, **Classes Abstratas** e **Polimorfismo** (ex: diferentes cálculos de preço para cada tipo de quadra).
* `view`: Contém todas as janelas (JFrames) e painéis (JPanels) visuais criados no WindowBuilder.
* `repository`: Classes responsáveis exclusivamente pela persistência de dados, realizando a leitura e gravação nos arquivos `.txt`.

## 📖 Guia para o Grupo

### Como editar as telas (Interface)
1. No Eclipse, clique com o botão direito em uma classe dentro do pacote `view`.
2. Selecione **Open With** > **WindowBuilder Editor**.
3. Use a aba **Design** (na parte inferior do editor) para ajustar botões, cores e layouts visualmente. Use a aba **Source** para ajustar o código manualmente.

### Como entender o fluxo do código
* **Entidades:** Comece olhando o pacote `model` para entender como um Cliente ou uma Quadra são estruturados.
* **Persistência:** Veja o `ClienteRepository` para entender como transformamos um objeto Java em uma linha de texto no arquivo `clientes.txt`.
* **Interação:** Na classe `TelaPrincipal`, verifique os métodos `addActionListener` dos botões para entender como a interface chama as funções de salvar e trocar de tela.

---
*Projeto acadêmico desenvolvido para as disciplinas de Programação Orientada a Objetos e Análise e Projeto de Sistemas - 2026.*