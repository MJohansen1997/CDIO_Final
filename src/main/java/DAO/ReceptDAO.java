package DAO;

import DTO.ReceptDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/** @author Johansen, Mikkel (john.doe@example.com)*/
public class ReceptDAO implements IDAO.IReceptDAO {
    MySQLCon newCon;

    public ReceptDAO() throws DALException {
        try {
            newCon = MySQLCon.getInstance();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new DALException("kunne ikke forbinde til databasen");
        }
    }

    @Override
    public ReceptDTO getRecept(String receptId) throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Recepter WHERE recID = '" + receptId + "'");
            if (rs.next()) {
                return udtagReceptFraResultat(rs);
            }
            throw new DALException("ingen resultater fundet");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException("Fejl søgning");
        }
    }

    @Override
    public List<ReceptDTO> getReceptList() throws DALException {
        try {
            Statement stmt = newCon.createStatement();
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
            PreparedStatement preparedStatement = newCon.createStatement("INSERT INTO recepter " +
                    "(recID, recNavn) VALUES (?, ?);");

            preparedStatement.setString(1,recept.getReceptID());
            preparedStatement.setString(2, recept.getReceptNavn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement. : " + e.getMessage());
        }

    }

    @Override
    public void updateRecept(ReceptDTO recept) throws DALException {
        try {
            PreparedStatement preparedStatement = newCon.createStatement("UPDATE recepter SET " +
                    "recID = ?, recNavn = ? WHERE recID = ?");
            preparedStatement.setString(1, recept.getReceptID());
            preparedStatement.setString(2, recept.getReceptNavn());
            preparedStatement.setString(3, recept.getReceptID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement." + e.getMessage());
        }
    }

    public static void main(String[] args) throws DALException {
        ReceptDTO upda = new ReceptDTO("REC00002","Vand Salt");

        ReceptDAO dbAc = new ReceptDAO();

        dbAc.updateRecept(upda);
    }

    public void deleteRecept(String receptID) throws DALException {

        try {
            PreparedStatement preparedStatement = newCon.createStatement("DELETE FROM recepter WHERE recID = ?;");
            preparedStatement.setString(1, receptID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement.");
        }
    }

    private ReceptDTO udtagReceptFraResultat(ResultSet rs) throws SQLException {
        ReceptDTO recept = new ReceptDTO(rs.getString("recID"), rs.getString("recNavn"));
        return recept;
    }
}
