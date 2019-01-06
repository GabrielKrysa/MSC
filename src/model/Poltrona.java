package model;

public class Poltrona {

    private int poltronaId;
    private int salaId;
    private String status;
    private int horarioId;

    public Poltrona(int poltronaId, int salaId, String status, int horarioId) {
        super();
        this.poltronaId = poltronaId;
        this.salaId = salaId;
        this.status = status;
        this.horarioId = horarioId;
    }

    public int getPoltronaId() {
        return poltronaId;
    }

    public void setPoltronaId(int poltronaId) {
        this.poltronaId = poltronaId;
    }

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHorario() {
        return this.horarioId;
    }

    public void setHorario(int horario) {
        this.horarioId = horario;
    }
}
