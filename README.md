O sistema segue o padrão MVC (Model-View-Controller).

1. O usuário preenche o formulário HTML com as informações do produto e envia os dados.
2. O Controller recebe a requisição HTTP e converte os dados do formulário em um objeto ProdutoModel.
3. O Controller encaminha o objeto para a camada Service.
4. O Service executa as regras de negócio e validações, como a verificação de estoque negativo.
5. Após a validação, o Service utiliza o Repository para realizar a persistência dos dados.
6. O Repository utiliza Spring Data JPA e Hibernate para mapear o objeto Java para a tabela correspondente no PostgreSQL.
7. Os dados são armazenados no banco de dados.
8. Após a operação ser concluída, o Controller redireciona o usuário para a página de listagem e exibe uma mensagem de confirmação.

Fluxo resumido:
HTML → Controller → Service → Repository → PostgreSQL
