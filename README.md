# 🏟️ Arena Gestor - Sistema de Controle de Quadras v1.0

Este é um sistema desktop desenvolvido em **Java** com interface gráfica **Swing** para gerenciar o aluguel de quadras esportivas. O projeto foca na aplicação prática de conceitos de **Programação Orientada a Objetos (POO)**, **Análise e Projeto de Sistemas** e **UI/UX Design**.

## 🚀 O que foi desenvolvido nesta versão

O sistema passou por uma refatoração completa para separar as responsabilidades (Arquitetura MVC/Repository), garantindo que a regra de negócio fique blindada e a interface do usuário (UI) seja amigável e à prova de erros (Poka-yoke).

### 🛠️ Core e Backend (Regras de Negócio)
* **Polimorfismo Real:** O cálculo do valor da reserva é feito dinamicamente com base na classe específica da quadra (ex: `QuadraFutsal`, `QuadraTenis`), lida diretamente do arquivo `.txt`.
* **Validação de Choque de Horários (RF004):** O `ReservaRepository` impede que duas reservas sejam feitas para a mesma quadra no mesmo horário.
* **Persistência Segura:** Leitura e escrita em arquivos `.txt` (`clientes.txt`, `quadras.txt`, `reservas.txt`) com tratamento de exceções para ignorar linhas corrompidas e evitar o "crash" do sistema.

---

## 🖥️ Guia de Telas e Usabilidade

O sistema foi padronizado com uma interface moderna (Flat Design), utilizando cores semânticas (Azul para salvar, Vermelho para excluir/cancelar) e tabelas (`JTable`) com clique inteligente.

### 1. Tela de Clientes
* **O que faz:** Gerencia o cadastro, edição e exclusão de clientes.
* **Como usar:** * Para **cadastrar**, preencha os dados e clique em "Cadastrar".
  * Para **editar ou excluir**, basta clicar no nome do cliente na tabela (grid) inferior. Os dados subirão automaticamente para os campos de texto. Altere o que for necessário e clique na ação desejada.
* **Regra:** O CPF é a chave única e possui validação estrita de 11 dígitos.

### 2. Tela de Quadras
* **O que faz:** Gerencia as quadras disponíveis no complexo esportivo.
* **Como usar:** Funciona com a mesma lógica de clique na tabela da tela de clientes.
* **Destaque:** O campo "Tipo" agora é uma caixa de seleção (Futsal, Tênis, Campo, etc.), garantindo que o objeto correto seja instanciado no backend na hora de calcular o preço. O valor é formatado automaticamente para Reais (R$).

### 3. Tela de Agendamento
* **O que faz:** O coração do sistema. Une o Cliente à Quadra escolhida gerando uma Reserva.
* **Como usar:**
  * **Atalho de Cliente (+):** Se o cliente for novo, não é preciso trocar de aba. Clique no botão `+` ao lado da seleção de clientes, preencha o popup, e ele já aparecerá selecionado.
  * **Data Inteligente:** Digite apenas o dia e o mês (ex: `17/04`). O sistema injeta o ano atual automaticamente.
  * **Horários:** Utiliza caixas de seleção (06:00 às 23:00) para impedir a digitação de textos inválidos.
  * **Valor Automático:** O sistema calcula o valor baseado no polimorfismo da quadra escolhida e exibe antes de salvar.
  * **Cancelamento:** Clique na reserva na tabela inferior e clique no botão vermelho "Cancelar Reserva".

### 4. Status das Quadras (Dashboard)
* **O que faz:** Um painel de controle em tempo real para a recepção.
* **Como funciona:** O sistema lê a hora do relógio do computador e cruza com os agendamentos do dia.
* **Dinamismo:** * Se a quadra estiver vazia, exibe um Card com borda **Verde** ("LIVRE").
  * Se houver jogo no exato momento, exibe um Card com borda **Vermelha** ("OCUPADA"), mostrando o nome do cliente e o horário da partida.
  * Possui um **Temporizador (Timer)** oculto que atualiza a tela automaticamente a cada 30 segundos, dispensando a necessidade de apertar F5.

---

## 👥 Integrantes do Grupo
* Carlos Henrique
* Guilherme Jorge
* Jeferson Santos
* Lucas Sena
* Luigi Lima
* Matheus Madeira
* Pedro Guimarães

## ⚙️ Como Executar
1. Importe o projeto no Eclipse IDE.
2. Certifique-se de estar utilizando o Java 21.
3. Execute a classe `TelaPrincipal.java` (botão direito > Run As > Java Application).
4. Os arquivos `.txt` serão gerados automaticamente na raiz do projeto ao realizar os primeiros cadastros.