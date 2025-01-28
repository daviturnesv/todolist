 Este projeto é uma aplicação de Lista de Tarefas (To-Do List) desenvolvida com o framework Spring Boot, visando fornecer uma interface simples e eficiente para o gerenciamento de tarefas pessoais. A aplicação implementa funcionalidades de autenticação básica, criação, listagem e atualização de tarefas, além de gerenciamento de usuários.

**Principais Tecnologias e Bibliotecas Utilizadas:**

  **Spring Boot:** Facilita a criação de aplicações Java standalone, simplificando a configuração e o desenvolvimento.

  **Spring Data JPA:** Abstrai interações com bancos de dados relacionais, permitindo operações de CRUD de forma simplificada.

  **Jakarta Servlet:** Conjunto de APIs para manipulação de requisições e respostas HTTP, essencial para a construção de aplicações web.

  **BCrypt** (at.favre.lib.crypto.bcrypt): Utilizada para hashing de senhas, garantindo a segurança dos dados dos usuários.

  **Lombok:** Reduz a verbosidade do código, gerando automaticamente getters, setters e outros métodos comuns através de anotações.

  **Apache Maven:** Ferramenta de gerenciamento de projetos e automação de builds que utiliza o conceito de Project Object Model (POM). No contexto deste projeto, o Maven foi utilizado para gerenciar as dependências e facilitar o processo de build da aplicação. 

  **Docker**: Utilizado para containerizar a aplicação, facilitando sua distribuição e execução em diferentes ambientes.

**Estrutura do Projeto**

  A estrutura do projeto segue as convenções do Maven e do Spring Boot, organizada da seguinte forma:

    src/main/java: Contém o código-fonte da aplicação.

        br.com.daviturnesv.todolist: Pacote raiz da aplicação.
    
            exceptions: Contém classes para tratamento de exceções globais.
    
            filter: Inclui filtros para requisições HTTP, como o filtro de autenticação.
    
            task: Contém as classes relacionadas às tarefas, incluindo modelos, repositórios e controladores.
    
            user: Inclui as classes relacionadas aos usuários, como modelos, repositórios e controladores.
    
            utils: Contém classes utilitárias usadas na aplicação.

**Endpoints da API**

  A aplicação expõe os seguintes endpoints para interação:

**/users/ [POST]**: Cria um novo usuário.

**/tasks/ [POST]**: Cria uma nova tarefa para o usuário autenticado.

**/tasks/ [GET]**: Lista todas as tarefas do usuário autenticado.

**/tasks/{id} [PUT]**: Atualiza uma tarefa existente do usuário autenticado.

**Funcionalidades Implementadas:**

  **Autenticação Básica:**

   A aplicação utiliza um filtro (**FilterTaskAuth**) que intercepta requisições para as rotas de tarefas, realizando a autenticação básica através do cabeçalho "**Authorization**".
  As credenciais são decodificadas e verificadas utilizando o algoritmo **BCrypt** para comparação das senhas.

  **Gerenciamento de Tarefas:**
  
   O **TaskController** oferece endpoints para criação (**POST /tasks/**), listagem (**GET /tasks/**) e atualização (**PUT /tasks/{id}**) de tarefas.
  As tarefas são associadas a usuários específicos, garantindo que cada usuário gerencie apenas suas próprias tarefas.
  
  **Gerenciamento de Usuários:**
  
   O UserController permite a criação de novos usuários (**POST /users/**).
  Antes de salvar um novo usuário, o controlador verifica se o nome de usuário já existe e realiza o hashing da senha utilizando **BCrypt**.
  
  **Tratamento de Exceções:**
  
   A classe **ExceptionHandlerController** lida com exceções do tipo **HttpMessageNotReadableException**, retornando uma resposta adequada com status **HTTP 400** (Bad Request) e a mensagem específica da exceção.

**Testes e Implantação:**

  **Testes com Postman:**
  
   Para testar os endpoints da API, recomenda-se o uso do **[Postman](https://www.postman.com/downloads/)**, uma plataforma colaborativa para desenvolvimento de APIs que simplifica cada etapa do ciclo de vida das APIs e facilita a colaboração para criar 
  APIs melhores de forma mais rápida.
  ![image](https://github.com/user-attachments/assets/822a7592-b053-4903-bed6-8750e15dc055)

  
  **Implantação com Render.com:**
  
   A aplicação pode ser implantada na plataforma **[Render.com](https://render.com/)**, que permite construir, implantar e escalar aplicações com facilidade, de forma gratuita ou paga, dependendo da demanda.
  ![image](https://github.com/user-attachments/assets/810c75b1-2a66-4a3b-b8f6-fb7063db1cab)



Este projeto serve como uma base sólida para aplicações que requerem autenticação e operações CRUD, podendo ser expandido conforme as necessidades específicas do desenvolvedor.
