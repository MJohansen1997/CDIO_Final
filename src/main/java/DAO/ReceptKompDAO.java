package DAO;

import DTO.ReceptDTO;
import DTO.ReceptKompDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/** @author Doe, John (john.doe@example.com)*/

public class ReceptKompDAO implements IDAO.IReceptKompDAO {

    MySQLCon newCon;

    public ReceptKompDAO() throws DALException {
        try {
            newCon = MySQLCon.getInstance();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new DALException("kunne ikke forbinde til databasen");
        }
    }

    @Override
    public ReceptKompDTO getReceptKomp(String receptId, String raavareId) throws DALException {
        try{
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reckomp WHERE recID = " + receptId
            + " AND raavID = " + raavareId
            );
            if (rs.next()) {
                return udtagReceptFraResultat(rs);
            }
            throw new DALException("ingen resultater fundet");

        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(String receptId) throws DALException {
        try{
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reckomp WHERE recID = '" + receptId+"'");
            List<ReceptKompDTO> liste = new ArrayList<>();
            while (rs.next()) {
                liste.add(udtagReceptFraResultat(rs));
            }
            return liste;
        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {
        try{
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reckomp");
            List<ReceptKompDTO> liste = new ArrayList<>();
            while (rs.next()) {
                liste.add(udtagReceptFraResultat(rs));
            }
            return liste;
        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }
    }

    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("Insert Into reckomp Values (" +
                    receptkomponent.getReceptID() + ", " +
                    receptkomponent.getRaavareID() + ", " +
                    receptkomponent.getNonNetto() + ", " +
                    receptkomponent.getTolerance()
            );
        } catch (SQLException e) {
            throw new DALException("Fejl ved indsættelse af element");
        }
    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE Recepter Set " +
                    "nonNetto" + receptkomponent.getNonNetto() + ", " +
                    "tolerance" + receptkomponent.getTolerance() + ", " +
                    "WHERE recID = " + receptkomponent.getReceptID() + " AND raavID = " +
                    receptkomponent.getRaavareID()
            );

        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }
    }

    private ReceptKompDTO udtagReceptFraResultat(ResultSet rs) throws SQLException {
        ReceptKompDTO recept = new ReceptKompDTO(rs.getString("recID"), rs.getString("raavID"),
                rs.getDouble("nonNetto"), rs.getDouble("tolerance"));
        return recept;
    }
}
