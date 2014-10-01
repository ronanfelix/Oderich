package sistema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que abre a conexão com o banco de dados.
 * 
 */
public class Conexao {
    /**
     * @return Connection com o BD
     * @throws ClassNotFoundException exception lançada se não for encontrado o drive.
     * @throws SQLException exception lançada se alguma chamada ao BD falhar.
     */
    public static Connection get() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost/base_ponto", "postgres", "170606");
    }
}
