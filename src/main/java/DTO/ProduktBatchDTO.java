package DTO;
public class ProduktBatchDTO {
    //Produktbatch id
    String pbID;
    // Status 0: Ikke påbegyndt, 1: Under produktion, 2: Afsluttet
    int status;
    // Recept id
    String receptID;

    public ProduktBatchDTO(String pbID, int status, String receptID){

    }
}
