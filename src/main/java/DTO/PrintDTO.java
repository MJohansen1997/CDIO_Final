package DTO;

import java.sql.Timestamp;

public class PrintDTO {
    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    String status;
    // Recept id
    String recID;

    //raavare batch id i området 1-99999999. Vælges af brugerne
    String rbId;
    //tara i kg
    double tara;
    //afvejet nettomængde i kg
    double netto;
    //Laborant-identifikationsnummer
    String labID;

    Timestamp startdato;
    Timestamp slutdato;

    String raavID;
    double maengde;



    public PrintDTO(String pbID, String status, String recID, String rbId, double tara, double netto, String labID, Timestamp startdato, Timestamp slutdato, String raavID, double maengde) {
        this.pbID = pbID;
        this.status = status;
        this.recID = recID;
        this.rbId = rbId;
        this.tara = tara;
        this.netto = netto;
        this.labID = labID;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.raavID = raavID;
        this.maengde = maengde;
    }

    public String getPbID() {
        return pbID;
    }

    public void setPbID(String pbID) {
        this.pbID = pbID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public String getRbId() {
        return rbId;
    }

    public void setRbId(String rbId) {
        this.rbId = rbId;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public String getLabID() {
        return labID;
    }

    public void setLabID(String labID) {
        this.labID = labID;
    }

    public Timestamp getStartdato() {
        return startdato;
    }

    public void setStartdato(Timestamp startdato) {
        this.startdato = startdato;
    }

    public Timestamp getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(Timestamp slutdato) {
        this.slutdato = slutdato;
    }

    public String getRaavID() {
        return raavID;
    }

    public void setRaavID(String raavID) {
        this.raavID = raavID;
    }

    public double getMaengde() {
        return maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }
}
