package DTO;
/** @author Johansen, Mikkel s175194*/
public class ReceptKompDTO {

    public ReceptKompDTO(){}
    /**
     * recept id i området 1-99999999
     */
    private String receptID;
    /**
     * raavare id i området 1-99999999 vælges af brugerne
     */
    private String raavareID;
    /**
     * nominel nettomængde i området 0,05 - 20,0 kg
     */
    private double nonNetto;
    /**
     * tolerance i området 0,1 - 10,0 %
     */
    private double tolerance;

    public ReceptKompDTO(String recID, String raavID, double netto, double tol){
        receptID = recID;
        raavareID = raavID;
        nonNetto = netto;
        tolerance = tol;
    }

    public String getReceptID() {
        return receptID;
    }

    public String getRaavareID() {
        return raavareID;
    }

    public double getNonNetto() {
        return nonNetto;
    }

    public double getTolerance() {
        return tolerance;
    }


}
