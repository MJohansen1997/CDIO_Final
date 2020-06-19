package DTO;
public class ProduktBatchDTO {

    /* Constructor */
    public ProduktBatchDTO(String pbID, String status, String recID) {
        this.pbID = pbID;
        this.status = status;
        this.recID = recID;
    }

    public String getPbID() {
        return pbID;
    }

    public String getStatus() {
        return status;
    }

    public String getReceptID() {
        return recID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    String status;
    // Recept id
    String recID;


}
