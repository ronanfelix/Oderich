package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import sistema.bean.Ponto;

/**
 *
 * @author ambiental.pelotas
 */
public class PontoDAO {

    private Connection conexao;

    public PontoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public Ponto retrieve(String add) throws SQLException {
        Ponto ponto = new Ponto();
        String sql = "SELECT pis, nsr, data, horario FROM ponto";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            ponto.setNsr(rs.getString("NSR"));
            ponto.setPis(rs.getString("PIS"));
            ponto.setData(rs.getString("DATA"));
            ponto.setHorario(rs.getString("HORARIO"));
        }
        rs.close();
        pst.close();
        return ponto;
    }

    public void inserir(Ponto add) throws SQLException {
        if (this.valida(add)) {
            String sql = "INSERT INTO ponto (nsr, data_horario, pis_funcionario, data) values ('" + add.getNsr() + "','" + add.getDataHorario() + "','" + add.getPis() + "','" + add.getData() + "')";
            //String sql = "INSERT INTO ponto (nsr, data_horario, pis_funcionario, data) values ('" + add.getNsr() + "','" + add.getDataHorario() + "','" + add.getPis() + "')";
            Statement st = this.conexao.createStatement();
            st.executeUpdate(sql);
        }

    }

    public List<Ponto> listaTodos() throws SQLException {
        List<Ponto> lista = new ArrayList<Ponto>();
        String sql = "SELECT nsr, pis_funcionario, data_horario FROM ponto";
        Statement st = this.conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Ponto ponto = new Ponto();
            ponto.setNsr(rs.getString("nsr"));
            ponto.setPis(rs.getString("pis_funcionario"));
            ponto.setDataHorario(rs.getTimestamp("data_horario"));
            lista.add(ponto);

        }
        rs.close();
        st.close();
        return lista;
    }

    public Ponto relatorioIntervalos(Ponto add) throws SQLException {
        Ponto ponto = new Ponto();
        String sql = "SELECT pis_funcionario, nsr, data_horario FROM ponto WHERE (pis_funcionario = '20471880729' AND data_horario = '2014-07-21') ORDER BY pis_funcionario";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        for (int i = 0; i < 500; i++) {
            if (rs.next()) {
                ponto.setNsr(rs.getString("NSR"));
                ponto.setPis(rs.getString("PIS_FUNCIONARIO"));
                ponto.setDataHorario(rs.getTimestamp("DATA_HORARIO"));

                Timestamp entrada1 = null;
                Timestamp entrada2 = null;
                Timestamp saida1 = null;
                Timestamp saida2 = null;

                entrada1 = ponto.getDataHorario();
                saida1 = ponto.getDataHorario();
                entrada2 = ponto.getDataHorario();
                saida2 = ponto.getDataHorario();

                //System.out.println("NSR: " + ponto.getNsr());
                System.out.println("PIS: " + ponto.getPis());
                System.out.println("DATA_HORÁRIO: " + ponto.getDataHorario());
                System.out.println("");
                System.out.println("");
                System.out.println("TESTE: " + entrada1);
                System.out.println("TESTE: " + saida1);
                System.out.println("TESTE: " + entrada2);
                System.out.println("TESTE: " + saida2);

            }
        }
        rs.close();
        pst.close();
        return ponto;
    }

    public Ponto TesterelatorioIntervalos(Ponto add) throws SQLException {
        Ponto ponto = new Ponto();
        String sql = "SELECT pis_funcionario, nsr, data_horario, nome FROM ponto INNER JOIN funcionario ON funcionario.pis = ponto.pis_funcionario order by pis_funcionario, data_horario";

        PreparedStatement pst = this.conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        String saida1 = null;
        String saida2 = null;
        String entrada1 = null;
        String entrada2 = null;
        String dataBase = null;
        String a = null;

        while (rs.next()) {
            dataBase = rs.getString("data_horario");
            a = dataBase.substring(0, 9);
            String pis = rs.getString("pis_funcionario");
            entrada1 = rs.getString("data_horario");
            rs.next();
            if (rs.getString("pis_funcionario").equals(pis)) {
                saida1 = rs.getString("data_horario");
                rs.next();

                if (rs.getString("pis_funcionario").equals(pis)) {
                    entrada2 = rs.getString("data_horario");
                    rs.next();

                    if (rs.getString("pis_funcionario").equals(pis)) {
                        saida2 = rs.getString("data_horario");
                    } else {
                        saida2 = entrada2;
                    }

                } else {
                    entrada2 = saida1;
                }

            } else {
                saida1 = entrada1;
            }

            String manha = entrada1 + " " + saida1;
            String tarde = entrada2 + " " + saida2;

            SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");

            Date e1 = null;
            Date s1 = null;
            Date e2 = null;
            Date s2 = null;

            try {
                e1 = null;//format.parse(entrada1);
                s1 = null;//format.parse(saida1);
                e2 = null;//format.parse(entrada2);
                s2 = null;//format.parse(saida2);

                //in milliseconds
                long diffManha = s1.getTime() - e1.getTime();
                long diffTarde = s2.getTime() - e2.getTime();

                long diffMinutesManha = diffManha / (60 * 1000) % 60;
                long diffHoursManha = diffManha / (60 * 60 * 1000) % 24;
                long diffMinutesTarde = diffTarde / (60 * 1000) % 60;
                long diffHoursTarde = diffTarde / (60 * 60 * 1000) % 24;

                System.out.println("Primeira Jornada: " + diffHoursManha + " horas, e " + diffMinutesManha + " minutos.");
                System.out.println("Segundo Jornada: " + diffHoursTarde + " horas, e " + diffMinutesTarde + " minutos.");

                if (diffHoursManha >= 5) {
                    System.out.println("Funcionário " + rs.getString("nome") + " trabalhou mais de cinco horas sem intervalo na primeira jornada.");
                }
                if (diffHoursTarde >= 5) {
                    System.out.println("Funcionário " + rs.getString("nome") + " trabalhou mais de cinco horas sem intervalo na segunda jornada.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(entrada1);
            System.out.println(saida1);
            System.out.println(entrada2);
            System.out.println(saida2);

        }

        rs.close();
        pst.close();
        return ponto;
    }

    public Ponto TesterelatorioHorasExtras(Ponto add) throws SQLException {
        Ponto ponto = new Ponto();
        //String sql = "SELECT pis_funcionario, nsr, data_horario, nome FROM ponto INNER JOIN funcionario ON funcionario.pis = ponto.pis_funcionario WHERE pis_funcionario = '10229287589' OR pis_funcionario = '10736750751' order by pis_funcionario, data_horario";
        String sql = "SELECT pis_funcionario, nsr, data_horario, nome FROM ponto INNER JOIN funcionario ON funcionario.pis = ponto.pis_funcionario order by pis_funcionario, data_horario";
        String sql2 = "select count(*) as total, pis_funcionario,data from ponto group by pis_funcionario,data order by total,data";

        PreparedStatement pst = this.conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        PreparedStatement pst2 = this.conexao.prepareStatement(sql2);
        ResultSet rs2 = pst2.executeQuery();

        String saida1 = null;
        String saida2 = null;
        String entrada1 = null;
        String entrada2 = null;
        String dataBase = null;
        String a = null;

        while (rs.next() && rs2.next()) {
            if (rs2.getInt("total") == 4) {
                dataBase = rs.getString("data_horario");
                a = dataBase.substring(0, 9);
                String pis = rs.getString("pis_funcionario");
                entrada1 = rs.getString("data_horario");
                rs.next();
                rs2.next();

                if (rs.getString("pis_funcionario").equals(pis)) {
                    saida1 = rs.getString("data_horario");
                    rs.next();
                    rs2.next();
                } else {
                    String erro = "Funcionário sem todas marcações.";
                }

                if (rs.getString("pis_funcionario").equals(pis)) {
                    entrada2 = rs.getString("data_horario");
                    rs.next();
                    rs2.next();
                } else {
                    String erro = "Funcionário sem todas marcações.";
                }

                if (rs.getString("pis_funcionario").equals(pis)) {
                    saida2 = rs.getString("data_horario");
                } else {
                    String erro = "Funcionário sem todas marcações.";
                }
            }

            String manha = entrada1 + " " + saida1;
            String tarde = entrada2 + " " + saida2;

            SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");

            Date e1 = null;
            Date s1 = null;
            Date e2 = null;
            Date s2 = null;

            try {
                e1 = null;//format.parse(entrada1);
                s1 = null;//format.parse(saida1);
                e2 = null;//format.parse(entrada2);
                s2 = null;//format.parse(saida2);

                //in milliseconds
                long diffManha = s1.getTime() - e1.getTime();
                long diffTarde = s2.getTime() - e2.getTime();
                long totalMili = (s1.getTime() - e1.getTime()) + (s2.getTime() - e2.getTime());

                long diffTotalDeMinutos = totalMili / (60 * 1000) % 60;
                long diffTotalDeHoras = totalMili / (60 * 60 * 1000) % 24;

                long diffMinutesManha = diffManha / (60 * 1000) % 60;
                long diffHoursManha = diffManha / (60 * 60 * 1000) % 24;
                long diffMinutesTarde = diffTarde / (60 * 1000) % 60;
                long diffHoursTarde = diffTarde / (60 * 60 * 1000) % 24;

                System.out.println("Primeira Jornada: " + diffHoursManha + " horas, e " + diffMinutesManha + " minutos.");
                System.out.println("Segundo Jornada: " + diffHoursTarde + " horas, e " + diffMinutesTarde + " minutos.");

                if (diffHoursManha + diffHoursTarde >= 8) {
                    System.out.println("Funcionário " + rs.getString("nome") + " trabalhou " + diffTotalDeHoras + " horas e " + diffTotalDeMinutos + " minutos.");

                    System.out.println("Total de Horas Extras: " + (diffTotalDeHoras - 8) + " horas e " + (diffTotalDeMinutos) + " minutos.");
                    //System.out.println("Total de Minutos:" + diffTotalDeMinutos + "Minutos Manha:" + diffMinutesManha + "Minutos Tarde:" + diffMinutesTarde);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(entrada1);
            System.out.println(saida1);
            System.out.println(entrada2);
            System.out.println(saida2);
            System.out.println("");

        }

        rs.close();
        pst.close();
        return ponto;
    }

    public Ponto TestandoRelatorios(Ponto add) throws SQLException {
        Ponto ponto = new Ponto();
        String sql = "SELECT pis_funcionario, nsr, data_horario FROM ponto ORDER BY pis_funcionario, data_horario";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        for (int i = 0; i < 500; i++) {
            while (rs.next()) {

                HashMap listDatas = new HashMap<String, Timestamp>();
                Set<String> keySetPis = listDatas.keySet();
                List<Date> listDatasFuncionario;
                Timestamp entrada;
                Timestamp saida;
                long tempoTrabalhadoEmMilissegundos = 0L;
                Map<String, Long> mapaPisTempoTrabalhado = new HashMap<String, Long>();
                for (String pis : keySetPis) {
                    listDatasFuncionario = (List<Date>) listDatas.get(pis);
                    //AQUI VOCÊ TERÁ A LISTA DE DATA DO FUNCIONARIO, AQUI DEVERÁ SER FEITO O CÁLCULO,
                    //ADMITINDO QUE SEMPRE TERÁ UMA ENTRADA SEGUIDA DE UMA SAIDA, SERIA ALGO ASSIM

                    mapaPisTempoTrabalhado.put(pis, tempoTrabalhadoEmMilissegundos);
                    System.out.println(mapaPisTempoTrabalhado.values());
                    System.out.println(listDatasFuncionario);
                    System.out.println(listDatas);
                }

            }
        }
        rs.close();
        pst.close();
        return ponto;
    }

    public boolean ponto_do_dia(int id_funcionario, Date data) throws SQLException {
        String sql = "SELECT * FROM ponto2 WHERE id_funcionario = ? AND (CAST(data_entrada1  AS date) = ?) OR (CAST(data_saida1 AS date) = ?) OR (CAST(data_entrada2  AS date) = ?) OR (CAST(data_saida2 AS date) = ? )";
        PreparedStatement pst = this.conexao.prepareStatement(sql);
        pst.setInt(1, id_funcionario);
        pst.setDate(2, data);
        pst.setDate(3, data);
        pst.setDate(4, data);
        pst.setDate(5, data);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            pst.close();
            rs.close();
            return true;
        } else {
            pst.close();
            rs.close();
            return false;
        }

    }

    public void insert_ponto(int id_funcionario, String campo_escala, boolean ponto_do_dia, Timestamp marcacao) throws SQLException {

        if (!ponto_do_dia) {
            String sql = "INSERT INTO ponto (id_funcionario, " + campo_escala + ") values (? ,?)";

            PreparedStatement pst = this.conexao.prepareStatement(sql);
            pst.setInt(1, id_funcionario);
            pst.setTimestamp(2, marcacao);
            pst.executeUpdate(sql);
        } else {
            String sql = "UPDATE ponto SET " + campo_escala + " = ? WHERE id_funcionario = ? AND (CAST(data_entrada1  AS date) = ?) OR CAST(data_saida1 AS date) = ? OR (CAST(data_entrada2  AS date) = ?) OR CAST(data_saida2 AS date) = ? )";
            PreparedStatement pst = this.conexao.prepareStatement(sql);
            pst.setTimestamp(1, marcacao);
            pst.setInt(2, id_funcionario);
            Date data = new Date(marcacao.getTime());
            pst.setDate(3, (java.sql.Date) data);
            pst.setDate(4, (java.sql.Date) data);
            pst.setDate(5, (java.sql.Date) data);
            pst.setDate(6, (java.sql.Date) data);
            pst.executeUpdate(sql);
        }

    }

    public boolean valida(Ponto ponto) {
        boolean ret = false;
        if (ponto != null) {
            ret = true;
        }
        return ret;
    }

}
