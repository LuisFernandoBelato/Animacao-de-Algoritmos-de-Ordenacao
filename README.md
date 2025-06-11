# 📊 Animação de Métodos de Ordenação (JavaFX) \ 5º Termo - Pesquisa e Ordenação

Este projeto consiste em uma atividade prática de implementação em JavaFX para visualizar de forma intuitiva o funcionamento de dois algoritmos de ordenação: **Comb Sort** e **Merge Sort (1ª Implementação)**. A animação busca demonstrar graficamente os passos de comparação e troca de elementos, auxiliando na compreensão desses métodos.

## Visão Geral

O objetivo principal é criar uma representação visual dinâmica de como os elementos são rearranjados durante o processo de ordenação. O projeto é dividido em dois sub-projetos Maven, cada um dedicado a um algoritmo específico, conforme a especificação do trabalho.

## Funcionalidades e Requisitos Implementados

A animação para cada algoritmo atende aos seguintes requisitos:

1.  **Geração Aleatória de Valores:** Ao iniciar a animação, os valores a serem ordenados são gerados aleatoriamente, garantindo que o algoritmo trabalhe com diferentes cenários.
2.  **Movimentação de Objetos na Tela:** Os elementos (representados por `javafx.scene.control.Button` com seus valores) são visualmente movidos na tela para simular permutações ou realocações de dados, tornando o processo de ordenação tangível.

## Algoritmos Implementados

O projeto implementa os dois algoritmos de ordenação a seguir:

*   **Comb Sort:** Conhecido por ser uma melhoria do Bubble Sort, que elimina "tartarugas" (pequenos valores no final de uma lista quase ordenada) usando um "gap" (lacuna) que diminui a cada iteração.
*   **Merge Sort (1ª Implementação):** Um algoritmo de divisão e conquista que divide a lista em duas metades, ordena cada metade recursivamente e depois as funde (merge) para produzir uma lista ordenada. A "1ª Implementação" pode se referir a uma abordagem específica (ex: não recursiva completa ou uma variação inicial).

## Tecnologias Utilizadas

*   **Linguagem de Programação:** Java
*   **Framework de UI:** JavaFX
*   **Sistema de Build:** Apache Maven

## Estrutura do Projeto

O projeto é organizado em dois sub-diretórios, cada um contendo uma implementação separada:

```
TRABALHO DE ANIMACOES - MERGE1 & COMB

├── CombSort/  (Projeto da Animação do Comb Sort)
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/combsortanimacaopo/
│   │       │       ├── HelloApplication.java  (Lógica principal e animação do Comb Sort)
│   │       │       └── HelloController.java   (Controlador para a interface gráfica)
│   │       └── resources/
│   │           └── com/example/combsortanimacaopo/
│   │               └── hello-view.fxml        (Arquivo de layout da interface gráfica - JavaFX)
│   ├── pom.xml                                (Arquivo de configuração do projeto Maven)
│   ├── mvnw                                   (Maven Wrapper para Linux/macOS)
│   └── mvnw.cmd                               (Maven Wrapper para Windows)
│
├── Merge1Sort/  (Projeto da Animação do Merge Sort - 1ª Implementação)
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/merge1taskthreadanimacao/
│   │       │       ├── HelloApplication.java  (Lógica principal e animação do Merge Sort)
│   │       │       └── HelloController.java   (Controlador para a interface gráfica)
│   │       └── resources/
│   │           └── com/example/merge1taskthreadanimacao/
│   │               └── hello-view.fxml        (Arquivo de layout da interface gráfica - JavaFX)
│   ├── pom.xml                                (Arquivo de configuração do projeto Maven)
│   ├── mvnw                                   (Maven Wrapper para Linux/macOS)
│   └── mvnw.cmd                               (Maven Wrapper para Windows)
```
