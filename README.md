#  Sistema de Cadastro de Clientes - Java & Oracle Cloud (JDBC)

Aplicação desenvolvida em **Java** para gerenciamento de clientes, integrada diretamente ao banco de dados **Oracle Autonomous Database (Cloud)** utilizando a API **JDBC**.

O projeto foi construído para praticar a persistência e manipulação de dados em bancos relacionais, aplicando conceitos de segurança contra SQL Injection e estruturação de conexão em nuvem com Oracle Wallet.

---

##  Tecnologias e Ferramentas Utilizadas

* **Linguagem:** Java (JDK 21)
* **Banco de Dados:** Oracle Autonomous Database (Oracle Cloud)
* **Conectividade:** JDBC (Java Database Connectivity)
* **Driver & Segurança:**
    * `ojdbc11.jar`
    * `oraclepki.jar`
    * `osdt_core.jar`
    * `osdt_cert.jar`
* **IDE:** IntelliJ IDEA

---

##  Funcionalidades

- [x] **Cadastrar Cliente:** Insere nome, e-mail e telefone no banco de dados Oracle.
- [x] **Listar Clientes:** Consulta e exibe no terminal todos os registros cadastrados com formatação organizada.
- [x] **Geração Automática de ID:** Utilização da cláusula `GENERATED ALWAYS AS IDENTITY` no Oracle.
- [x] **Prevenção contra SQL Injection:** Uso de `PreparedStatement` para tratamento seguro de parâmetros.
- [x] **Gerenciamento de Recursos:** Utilização da estrutura `try-with-resources` para fechamento automático de conexões.

---

## ️ Estrutura do Banco de Dados

Para a execução deste projeto, foi criada a seguinte tabela no Oracle Database:


CREATE TABLE clientes (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    telefone VARCHAR2(20)
);

 Como Executar o Projeto
Pré-requisitos
 * JDK 11 ou superior instalado.
 * Uma instância do Oracle Database (Local ou Oracle Cloud com Wallet).
 * Dependências JDBC e de segurança da Oracle vinculadas ao projeto.
Passos para Configuração
 * Clonar o repositório:
   git clone https://github.com/twyderik/sistema-cadastro-oracle-jdbc.git

 * Configurar as Credenciais e Wallet:
   * Descompacte o arquivo .zip da sua Oracle Wallet em um diretório local.
   * Na classe CadastroCliente.java, configure o caminho da Wallet e suas credenciais de acesso:
   static {
    System.setProperty("oracle.net.tns_admin", "CAMINHO_PARA_SUA_PASTA_WALLET");
}

private static final String URL = "jdbc:oracle:thin:@seu_servico_high";
private static final String USUARIO = "SEU_USUARIO";
private static final String SENHA = "SUA_SENHA";

 * Executar a Aplicação:
   Abra o projeto na sua IDE de preferência e execute a classe CadastroCliente.java.
 Segurança de Dados
Este repositório segue boas práticas de segurança:
 * Senhas e chaves de acesso reais não são expostas no código-fonte.
 * Arquivos de certificados (.sso, .p12, .ora) e compilações locais estão ignorados via .gitignore.
Desenvolvido por Kawã Derik | Conecte-se comigo no LinkedIn: www.linkedin.com/in/kawa-derik-costa-macedo-6ab16a3a9
