## 1. Leitura e Análise
- Li o desafio atentamente e fiz anotações detalhadas para entender os requisitos e objetivos.
- Analisei a API utilizando o Postman, para mapear os dados disponíveis e planejar como consumi-los no app.

---

## 2. Configuração Inicial
- Fiz o fork do repositório e configurei o projeto na minha máquina local.
- Criei o projeto no Android Studio, configurei as dependências iniciais e adicionei a permissão de internet no `AndroidManifest.xml`.

---

## 3. Estrutura do Projeto (MVVM)

### **Pacote `data`**
- **`remote/api`:**
    - Contém o `NewsApiService`, responsável pelas chamadas à API utilizando Retrofit.
- **`remote/model`:**
    - Modelos utilizados para mapear os dados retornados pela API, como:
        - `Chapeu`
        - `Content`
        - `Falkor`
        - `Feed`
        - `Item`
        - `NewsResponse` (modelo principal para as notícias).
- **`repository`:**
    - Contém a implementação do `NewsRepository`, que centraliza a lógica de acesso à API e organiza os dados para a ViewModel.

### **Pacote `network`**
- **`NetworkProviders`:**
    - Classe responsável por fornecer as instâncias de Retrofit, reutilizáveis em todo o projeto.

### **Pacote `di`**
- **`serviceModule.kt`:**
    - Configuração de dependências do Koin, como:
        - Instâncias do Retrofit.
        - Repositório (`NewsRepository`).
- **`viewModelModule.kt`:**
    - Configuração do Koin para injeção das ViewModels.

### **Pacote `ui`**
- **`theme`:**
    - Configuração do tema do aplicativo, como cores, fontes e tipografia:
        - `Color.kt`
        - `Theme.kt`
        - `Type.kt`
- **`view`:**
    - Contém a `MainActivity`, responsável por iniciar o app e gerenciar a interface principal.

### **Pacote `viewModel`**
- Contém a `NewsViewModel`, que gerencia a lógica de consumo do repositório e organiza os dados para a interface do usuário.

### **Pacote `useCase`**
- Preparado para centralizar lógicas específicas que podem ser reutilizadas em diferentes partes do app.

### **Pacote `utils`**
- Preparado para conter utilitários e constantes globais que auxiliam no desenvolvimento.

---

## 4. Commits

### **1º Commit**
- Criação do projeto no Android Studio.
- Adição da permissão de internet no `AndroidManifest.xml`.

### **2º Commit**
- Organização da estrutura de pastas seguindo o padrão MVVM.
- Configuração camada de dados.

### **3º Commit**
- Criação do layout principal.
- Configuração do ViewModel na MainActivity para buscar dados da API.
- Implementação da lógica para consumir os dados da API e popular a tela (sem buscar ainda a imagem).
- Criação do arquivo comments.md.

### **4º Commit**
- Melhoria no layout: Ajustes para melhor apresentação do nome do aplicativo, proporcionando maior espaçamento e clareza visual.
- Remoção do ícone de refresh: Substituído por um gesto de Pull to Refresh(arrastar para baixo na lista), seguindo padrões mais modernos de usabilidade.
- Implementação da lógica de refresh.

### **5º Commit**
- Ajustado o filtro para exibir apenas os itens classificados como "básico" ou "materia" no feed principal.
- Implementada a funcionalidade de paginação. O aplicativo agora realiza requisições para carregar a próxima página do feed conforme o usuário faz a rolagem.

### **6° Commit**

- Implementação da funcionalidade de navegação para uma nova tela ao clicar em um item, exibindo o conteúdo da matéria em um WebView.
- Configuração do WebView para carregar a URL do item clicado usando o campo "url".
- Adicionada a funcionalidade de voltar para a tela inicial através de um botão "Voltar" na nova tela.