package DTO;
import java.sql.Timestamp;


/**
 * @author Hansen, Mads Østerlund (s195456@student.dtu.dk)
 **/
public class ProduktBatchDTO {
    private String pbID;
    private String status;
    private String recID;
    private Timestamp startdato;
    private Timestamp slutdato;

    /* Constructor */
    public ProduktBatchDTO(String pbID, String status, String recID, Timestamp startdato) {
        this.pbID = pbID;
        this.status = status;
        this.recID = recID;
        this.startdato = startdato;
    }

    public ProduktBatchDTO(String pbID, String status, String recID, Timestamp startdato, Timestamp slutdato) {
        this.pbID = pbID;
        this.status = status;
        this.recID = recID;
        this.startdato = startdato;
        this.slutdato = slutdato;
    }

    public String getPbID() {
        return pbID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceptID() {
        return recID;
    }

    public Timestamp getStartdato() {
        return startdato;
    }

    public Timestamp getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(Timestamp slutdato) {
        this.slutdato = slutdato;
    }

}
