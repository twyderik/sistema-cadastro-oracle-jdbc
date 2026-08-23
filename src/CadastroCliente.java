import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class CadastroCliente {
   static{

       System.setProperty("oracle.net.tns_admin", "TNS_ADMIN");
       try {
           Class.forName("oracle.jdbc.OracleDriver");
       } catch (ClassNotFoundException e) {
           System.err.println("Driver jdbc Não encontrado!");
       }
   }
    private static final String URL = "jdbc:oracle:thin:@seu_servico_high";
    private static final String USUARIO = "SEU_USUARIO";
    private static final String SENHA = "SUA_SENHA";

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
        System.out.println("\n--- SISTEMA DE CADASTRO DE CLIENTES ---");
        System.out.println("1. Cadastrar novo cliente");
        System.out.println("2. Listar clientes cadastrados");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastrarCliente(scanner);
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                System.out.println("saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        }
        scanner.close();
    }
    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("\nDigite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        String sql = "INSERT INTO CLIENTES (NOME, EMAIL, TELEFONE) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente cadastrado com sucesso no banco de dados!");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou inserir no banco: " + e.getMessage());
        }
    }

    private static void listarClientes(){
        String sql = "SELECT id, NOME, EMAIL, TELEFONE FROM CLIENTES ORDER BY id";

        System.out.println("\n---------- LISTA DE CLIENTES ----------");

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                System.out.printf("ID: %-3d | NOME: %-20s | EMAIL: %-25s | TEL: %s\n", id, nome, email, telefone);
            }
            System.out.println("-------------------------------");
        } catch (SQLException e) {
            System.err.println("Erro ao consultar o banco de dados: " + e.getMessage());
        }
     }
}
