package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistema.bean.Usuario;

/**
 *
 * @author ambiental.pelotas
 */
public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Usuario retrieve(String login) throws SQLException {
        Usuario user = new Usuario();
        String sql = "SELECT COD,LOGIN,SENHA FROM USUARIO WHERE LOGIN=?";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        pst.setString(1, login);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            user.setCod(rs.getInt("COD"));
            user.setLogin(rs.getString("LOGIN"));
            user.setSenha(rs.getString("SENHA"));
        }
        rs.close();
        pst.close();
        return user;
    }

    public List<Usuario> listaTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<Usuario>();
        String sql = "SELECT cod, login FROM usuario ORDER BY cod";
        Statement st = this.conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Usuario user = new Usuario();
            user.setCod(rs.getInt("COD"));
            user.setLogin(rs.getString("LOGIN"));
            lista.add(user);
        }
        rs.close();
        st.close();
        return lista;
    }

}
