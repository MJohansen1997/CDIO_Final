package DTO;
/** @author Johansen, Mikkel s175194*/
public class ReceptDTO {
    private String receptID;
    private String receptNavn;

    public ReceptDTO(String recID, String recNavn){
        receptID = recID;
        receptNavn = recNavn;
    }

    public ReceptDTO(){}

    public String getReceptID() {
        return receptID;
    }

    public String getReceptNavn() {
        return receptNavn;
    }
}
