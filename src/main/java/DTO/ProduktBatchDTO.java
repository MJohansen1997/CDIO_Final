package DTO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ProduktBatchDTO {

    /* Constructor */
    public ProduktBatchDTO(String pbID, String status, String recID, Date startdato) {
        this.pbID = pbID;
        this.status = status;
        this.recID = recID;
        this.startdato = startdato;
    }

    public ProduktBatchDTO(String pbID, String status, String recID, Date startdato, Date slutdato) {
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

    public Date getStartdato() {
        return startdato;
    }

    public Date getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(Date slutdato) {
        this.slutdato = slutdato;
    }



    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    String status;
    // Recept id
    String recID;

    Date startdato;
    Date slutdato;

}
