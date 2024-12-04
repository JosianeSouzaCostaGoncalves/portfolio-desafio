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

### **7° Commit**
- Adicionada funcionalidade para buscar e exibir imagens de uma fonte alternativa (sizes.sizeL.url) presente no objeto Image.
- Modificada a exibição de imagens na NewsCard, priorizando o uso da nova fonte alternativa para exibir as imagens, caso a principal não esteja disponível.

## Explicação sobre a dificuldade enfrentada na busca da imagem e a solução aplicada

Durante o desenvolvimento, identifiquei um problema relacionado à exibição de imagens no aplicativo. A URL principal da imagem fornecida pela API não estava sendo renderizada corretamente, o que resultava em falhas visuais nos itens da lista.
Para resolver esse problema, revisei a resposta da API e descobri que ela disponibilizava imagens alternativas em diferentes tamanhos no campo sizes, dentro do objeto Image. Com isso, ajustei a lógica de exibição de imagens no aplicativo para priorizar o tamanho "L" (grande) disponível no campo sizeL.url.
Essa abordagem assegura que, mesmo quando a imagem principal estiver ausente ou apresentar falhas, o aplicativo ainda será capaz de exibir uma imagem válida, garantindo uma experiência visual mais consistente para o usuário.

### **8° Commit**
- Correção do comportamento de "Pull to Refresh", permitindo que os usuários atualizem a lista de notícias manualmente.
Pois foi identificada uma falha no funcionamento do "Pull to Refresh", onde o método de atualização do ViewModel não estava sendo acionado corretamente e os estados de atualização (isRefreshing e isLoadingMore) não estavam sendo gerenciados de forma eficaz.

### **9° Commit**
- Implementação do componente TabRow para navegação entre as abas do aplicativo("Home", "Economia" e "Menu") somente layout, a logica sera implementada posteriormente.

### **10° Commit**
- A separação da MainActivity em funções menores tem como objetivo melhorar a legibilidade, manutenção e reutilização do código sem alterar a lógica existente.
- Cada componente foi isolado para realizar apenas uma tarefa específica.
- O código foi modularizado, permitindo alterações futuras sem impactar outras áreas.

### **11° Commit**
- Ajuste na requisição pois esta passando o parametro sem necessidade.
- Ajuste lógica do refresh( aparecimento do looading quando fazemos uma nova requisição )

### **12° Commit**
- Configurando a busca da requisição na aba de economia, possibilitando a visualização do carregamento das informações da guia de economia.

### **13° Commit**
- Logica para controlar a exibição das abas (Home, Economia, Menu). Quando for uma webView essas opções ficarão ocultas.
- Adicionar MenuScreen com lista de itens contendo títulos e URLs associados.
- Implementar navegação ao clicar em um item do menu, abrindo a WebView correspondente.
- Configurar WebViewScreen para exibir o conteúdo das URLs fornecidas pelo MenuScreen.

### **14° Commit**
- Ajuste na cor do aplicativo.

### **15° Commit**
- Ajuste de logica para recebimento de paginação correta.
- Implementação do layout para aparecimento do loading.
- Adicionado nulaveis nas variaveis de modelo.

### **16° Commit**
- indentação do código e remoção de importes que não estavam sendo usados.