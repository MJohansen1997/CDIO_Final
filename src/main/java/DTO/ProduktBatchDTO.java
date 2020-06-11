package DTO;

public class ProduktBatchDTO {
    /** @author Hansen, Mads (john.doe@example.com)
     **/

    /**
     * produkt batch id i området 1-99999999. Vælges af brugerne
     * PB00001
     */
    String pbID;
    /**
     * status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet
     */
    int status;
    /**
     * recept id i området 1-99999999. Vælges af brugerne
     */
    String receptID;
}
