package DTO;

public class ProduktBatchKompDTO {

    public ProduktBatchKompDTO(String pbId, String rbId, double tara, double netto, String labID) {
        this.pbId = pbId;
        this.rbId = rbId;
        this.tara = tara;
        this.netto = netto;
        this.labID = labID;
    }

    /**
     * produkt batch id i området 1-99999999. Vælges af brugerne
     */
    String pbId;

    public String getPbId() {
        return pbId;
    }

    public void setPbId(String pbId) {
        this.pbId = pbId;
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

    /**
     * raavare batch id i området 1-99999999. Vælges af brugerne
     */
    String rbId;
    /**
     * tara i kg
     */
    double tara;
    /**
     * afvejet nettomængde i kg
     */
    double netto;
    /**
     * Laborant-identifikationsnummer
     */
    String labID;

}
