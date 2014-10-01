package sistema.bean;

import java.sql.Timestamp;

/**
 * Essa classe representa a entidade Ponto, a qual vai receber os dados de um
 * arquivo txt.
 *
 * @author ambiental.pelotas
 */
public class Ponto {

    private String nsr;
    private String pis;
    private String data;
    private String horario;
    private Timestamp dataHorario;

    public Timestamp getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(Timestamp dataHorario) {
        this.dataHorario = dataHorario;
    }

    public String getNsr() {
        return nsr;
    }

    public void setNsr(String nsr) {
        this.nsr = nsr;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
