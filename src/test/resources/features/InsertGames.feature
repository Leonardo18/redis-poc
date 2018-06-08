Feature: Inserir Games

  Scenario: A inserção de games favoritos no banco de dados redis ocorre com sucesso
    Given o id do game 1
    And o nome do game God of War
    And o genêro do game Ação
    And a note da análise pessoal 10
    And a observação da análise um jogo muito da hora
    When insiro um game
    Then deve retornar que o game foi inserido com sucesso
    And deve retorar uma resposta com status 200