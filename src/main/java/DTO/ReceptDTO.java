package DTO;
/** @author Johansen, Mikkel s175194*/
public class ReceptDTO {
    public ReceptDTO(String recID, String recNavn){
        receptID = recID;
        receptNavn = recNavn;
    }

    public ReceptDTO(){}
    /**
     * recept id i området 1-99999999
     */
    String receptID;

    public String getReceptID() {
        return receptID;
    }

    public String getReceptNavn() {
        return receptNavn;
    }

    /**
     * Receptnavn min. 2 max. 20 karakterer
     */
    String receptNavn;
}
