package DTO;

public class ProduktBatchKompDTO {
    /**
     * produkt batch id i området 1-99999999. Vælges af brugerne
     */
    String pbId;
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
