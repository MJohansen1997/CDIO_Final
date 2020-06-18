package DTO;

/** @author s195485, Nikolai Kristensen (john.doe@example.com)*/

public class RaavareDTO
{
    String raavID;
    String raavNavn;
    String leverandor;

    public RaavareDTO(String raavID, String raavNavn, String leverandor)
    {
        this.raavID = raavID;
        this.raavNavn = raavNavn;
        this.leverandor = leverandor;
    }

    public String getRaavID()
    {
        return raavID;
    }

    public void setRaavID(String raavID)
    {
        this.raavID = raavID;
    }

    public String getRaavNavn()
    {
        return raavNavn;
    }

    public void setRaavNavn(String raavNavn)
    {
        this.raavNavn = raavNavn;
    }

    public String getLeverandor()
    {
        return leverandor;
    }

    public void setLeverandor(String leverandor)
    {
        this.leverandor = leverandor;
    }
}
