# Acerte o Número

O **Acerte o Número** é um aplicativo Android desenvolvido em Kotlin, como projeto final da disciplina de Desenvolvimento de Aplicativos Móveis.

A ideia é: testar a capacidade de raciocínio e adivinhação do jogador em um jogo no qual ele precisa descobrir o número secreto de cada desafio. A cada tentativa, o aplicativo dá uma dica, informando se o número escolhido é maior ou menor que o número secreto. O objetivo é acertar!

O projeto foi desenvolvido em equipe, buscando colocar em prática os conhecimentos que aprendemos durante a disciplina, principalmente sobre organização do código, persistência de dados, navegação e integração com serviços externos.

**Equipe:**
- Isabela Selbmann Nascimento
- Gabriela Selbmann Nascimento
- Letícia Silva Braz

## Como funciona o aplicativo?

O aplicativo possui duas áreas principais:

**Área do Jogador**
- Permite escolher um jogador cadastrado.
- Apresenta os desafios disponíveis.
- Permite selecionar um desafio e começar uma partida.
- Mostra o intervalo de números e as dicas durante o jogo.
- Informa o resultado quando o jogador acerta ou termina a partida.

**Área do Administrador**
- Possui um painel administrativo.
- Permite cadastrar, visualizar, editar e excluir desafios.
- Permite cadastrar, visualizar, editar e excluir jogadores.
- Possui a opção de sincronizar os desafios com o Firebase.

## Telas do aplicativo

O projeto possui 8 destinos de navegação:

1. **Início:** escolha entre a área do jogador e a área administrativa.
2. **Selecionar Jogador:** escolha de um jogador cadastrado.
3. **Desafios:** visualização e seleção dos desafios disponíveis.
4. **Jogo:** realização dos palpites e recebimento das dicas.
5. **Resultado:** apresentação do resultado da partida.
6. **Painel Administrativo:** acesso às funcionalidades do administrador.
7. **Gerenciar Desafios:** cadastro, edição, consulta e exclusão de desafios.
8. **Gerenciar Jogadores:** cadastro, edição, consulta e exclusão de jogadores.

##  Tecnologias utilizadas

- **Kotlin:** linguagem utilizada no desenvolvimento.
- **Jetpack Compose:** criação das telas e componentes visuais.
- **Material 3:** componentes e elementos da interface.
- **Navigation Compose:** navegação entre as telas.
- **Room:** armazenamento local dos dados.
- **Kotlin Coroutines e Flow:** operações assíncronas e observação dos dados.
- **ViewModel e StateFlow:** gerenciamento dos estados e das regras do aplicativo.
- **Firebase Firestore:** armazenamento e sincronização dos desafios na nuvem.

##  Organização e arquitetura

Para manter o projeto organizado e facilitar a manutenção, utilizamos o padrão **MVVM (Model-View-ViewModel)**.

A estrutura separa as telas, os dados e as regras de funcionamento do aplicativo.

**ViewModels utilizados:**
- `JogoViewModel`: controla a partida, os palpites, as tentativas e os resultados.
- `AdminViewModel`: gerencia as operações relacionadas aos desafios.
- `JogadorViewModel`: gerencia o cadastro e a manutenção dos jogadores.
- `PerfilViewModel`: controla o perfil selecionado no aplicativo.

Também utilizamos `ViewModelFactory` para a criação dos ViewModels que precisam receber dependências.

## 💾 Banco de dados e entidades

O aplicativo utiliza duas entidades principais:

**DesafioEntity (Room e Firebase)**

Armazena as informações dos desafios:
- `id`
- `titulo`
- `numeroMinimo`
- `numeroMaximo`
- `numeroSecreto`
- `dificuldade`
- `ativo`

Os desafios são armazenados localmente com Room e também podem ser sincronizados com o Firebase Firestore.

**JogadorEntity (Room)**

Armazena as informações dos jogadores:
- `id`
- `nome`
- `apelido`
- `ativo`

Os dados dos jogadores ficam armazenados localmente no dispositivo.

Utilizamos também `Flow` para acompanhar as alterações nos dados e atualizar as listas do aplicativo.

## 🔄 Funcionalidades e conceitos aplicados

Durante o desenvolvimento, colocamos em prática diferentes conceitos trabalhados na disciplina:

- Arquitetura MVVM.
- Separação de responsabilidades por meio de ViewModels e Repositories.
- CRUD completo para desafios e jogadores.
- Persistência local com Room.
- Integração com Firebase Firestore.
- Tratamento de exceções.
- Listas dinâmicas utilizando `LazyColumn`.
- Filtros e ordenação de dados.
- Navegação global entre as telas.
- Controle de perfil entre Administrador e Jogador.

## 🚀 Como executar o projeto

1. Faça o clone deste repositório ou baixe os arquivos do projeto.
2. Abra o projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Configure o Firebase do projeto e coloque o arquivo `google-services.json` na pasta `app`.
5. Verifique se o projeto possui as configurações e dependências necessárias para o Firebase.
6. Conecte um dispositivo Android com a depuração USB ativada ou inicie um emulador.
7. Clique em **Run** no Android Studio para executar o aplicativo.

## 📂 Organização das pastas

A estrutura principal do projeto está organizada da seguinte forma:

- `ui`: telas, componentes visuais e tema do aplicativo.
- `ui.theme.navigation`: navegação entre as telas.
- `data.local`: entidades, DAOs e banco de dados Room.
- `data.remote`: integração com o Firebase.
- `data.repository`: comunicação entre as fontes de dados e os ViewModels.
- `viewmodel`: ViewModels, estados da interface e ViewModelFactory.

---

Link Canva:  **https://canva.link/n95mxrxhhex0spx**

Equipe:
- Gabriela Selbmann Nascimento
- Isabela Selbmann Nascimento
- Leticia Silva Braz