package DTO;

public class RaavareDTO
{
    public RaavareDTO(String rID, String rN, String lev) {
        raavareID = rID;
        raavareNavn = rN;
        leverandoer = lev;
    }

    public RaavareDTO(){}

    //raavare id i området 1-99999999 vælges af brugerne
    String raavareID;

    // min. 2 max. 20 karakterer
    String raavareNavn;

    // min. 2 max. 20 karakterer
    String leverandoer;

    public String getRaavareID() {
        return raavareID;
    }

    public void setRaavareID(String raavareID) {
        this.raavareID = raavareID;
    }

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }
}

