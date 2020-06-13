package DTO;
public class ProduktBatchDTO {

    /* Constructor */
    public ProduktBatchDTO(String pbID, int status, String receptID) {
        this.pbID = pbID;
        this.status = status;
        this.receptID = receptID;
    }

    public String getPbID() {
        return pbID;
    }

    public int getStatus() {
        return status;
    }

    public String getReceptID() {
        return receptID;
    }

    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    int status;
    // Recept id
    String receptID;


}
