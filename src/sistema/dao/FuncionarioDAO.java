package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import sistema.bean.Funcionario;

/**
 *
 * @author ambiental.pelotas
 */
public class FuncionarioDAO {

    private Connection conexao;

    public FuncionarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Funcionario retrieve(Funcionario func) throws SQLException {
        Funcionario funcRet = null;
        String sql = "SELECT cod, nome FROM funcionario WHERE cod=?";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        pst.setInt(1, func.getCod());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            funcRet = new Funcionario();
            funcRet.setCod(rs.getInt("COD"));
            funcRet.setNome(rs.getString("NOME"));
        }
        rs.close();
        pst.close();
        return funcRet;
    }

    public void inserir(Funcionario addFunc) throws SQLException {
        if (this.valida(addFunc)) {
            String sql = "INSERT INTO funcionario (nome, sexo, dtnascimento, endereco, complemento, bairro, cidade, cpf, rg, dtadmissao, funcao, pis) values ('" + addFunc.getNome() + "', '" + addFunc.getSexo() + "','" + addFunc.getDtNascimento() + "','" + addFunc.getEndereco() + "','" + addFunc.getComplemento() + "','" + addFunc.getBairro() + "','" + addFunc.getCidade() + "','" + addFunc.getCpf() + "','" + addFunc.getRg() + "','" + addFunc.getDtAdmissao() + "','" + addFunc.getFuncao() + "','" + addFunc.getPis() + "')";
            Statement st = this.conexao.createStatement();
            st.executeUpdate(sql);
            st.close();
        }

    }

    public void update(Funcionario func) throws SQLException {
        if (this.valida(func)) {
            String sql = "UPDATE funcionario SET nome=? WHERE  cod=?";
            PreparedStatement pst = this.conexao.prepareStatement(sql);
            pst.setString(1, func.getNome());
            pst.setInt(7, func.getCod());
            pst.executeUpdate();
            pst.close();
        }
    }

    public void delete(Funcionario func) throws SQLException {
        String sql = "DELETE FROM FUNCIONARIO WHERE COD=?";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        pst.setInt(1, func.getCod());
        pst.executeUpdate();
        pst.close();
    }

    public List<Funcionario> listaTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT cod, nome, rg, cpf, pis FROM funcionario ORDER BY cod";
        Statement st = this.conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Funcionario func = new Funcionario();
            func.setCod(rs.getInt("COD"));
            func.setNome(rs.getString("NOME"));
            func.setRg(rs.getString("RG"));
            func.setCpf(rs.getString("CPF"));
            func.setPis(rs.getString("PIS"));
            lista.add(func);
        }
        rs.close();
        st.close();
        return lista;
    }

    public boolean valida(Funcionario func) {
        boolean ret = false;
        if (func != null) {
            ret = true;
        }
        return ret;
    }

    public Integer buscaPis(String pis) throws SQLException {
        Integer id = null;
        Funcionario funcRet = null;
        String sql = "select * from funcionario where pis = ?";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        pst.setString(1, pis);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("cod");
        }
        rs.close();
        pst.close();
        return id;

    }
    
    public Integer verifica_escala(int id_funcionario, Timestamp data_hora) throws SQLException {
        return null;
        
                        
    }

}
