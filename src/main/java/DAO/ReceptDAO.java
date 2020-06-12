package DAO;

import DTO.BrugerDTO;
import DTO.ReceptDTO;
import DAO.MySQLCon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/** @author Johansen, Mikkel (john.doe@example.com)*/
public class ReceptDAO implements IDAO.IReceptDAO {
    MySQLCon newCon = new MySQLCon();

    public ReceptDAO() throws DALException {
        try {
            newCon.setupCon();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new DALException("kunne ikke forbinde til databasen");
        }
    }

    @Override
    public ReceptDTO getRecept(int receptId) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Recepter WHERE recID = " + receptId);
            if (rs.next()) {
                return udtagReceptFraResultat(rs);
            }
            throw new DALException("ingen resultater fundet");

        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }
    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Recepter");
            List<ReceptDTO> liste = new ArrayList<>();

            while (rs.next()) {
                liste.add(udtagReceptFraResultat(rs));
            }
            return liste;

        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }

    }

    @Override
    public void createRecept(ReceptDTO recept) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("Insert Into Recepter Values (" +
                    recept.getReceptID() + ", " +
                    recept.getReceptNavn()
                    );
        } catch (SQLException e) {
            throw new DALException("Fejl ved indsættelse af element");
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE Recepter Set " +
                    "recNAVN" + recept.getReceptNavn() + ", " +
                    "WHERE recID = " + recept.getReceptID()
            );

        } catch (SQLException e) {
            throw new DALException("Fejl søgning");
        }

    }

    private ReceptDTO udtagReceptFraResultat(ResultSet rs) throws SQLException {
        ReceptDTO recept = new ReceptDTO(rs.getString("recID"), rs.getString("recNavn"));
        return recept;
    }
}
