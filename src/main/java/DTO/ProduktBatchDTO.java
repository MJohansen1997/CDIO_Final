package DTO;
public class ProduktBatchDTO {

    /* Constructor */
    public ProduktBatchDTO(String pbID, String status, String receptID) {
        this.pbID = pbID;
        this.status = status;
        this.receptID = receptID;
    }

    public ProduktBatchDTO(){}

    public String getPbID() {
        return pbID;
    }

    public String getStatus() {
        return status;
    }

    public String getReceptID() {
        return receptID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    String status;
    // Recept id
    String receptID;


}
