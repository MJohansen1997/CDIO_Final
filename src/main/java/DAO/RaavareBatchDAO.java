package DAO;

import DTO.ProduktBatchKompDTO;
import DTO.RaavareBatchDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/** @author Luxhøj, Stefan (s195467@student.dtu.dk)*/
public class RaavareBatchDAO implements IDAO.IRaavareBatchDAO {
    MySQLCon newCon;

    public RaavareBatchDAO() throws SQLException, ClassNotFoundException, DALException {
        try {
            newCon = MySQLCon.getInstance();;
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new DALException("Kunne ikke oprette forbindelse");
        }
    }
    @Override
    public RaavareBatchDTO getRaavareBatch(String rbId) throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM raavarebatches WHERE rbID = '" + rbId + "'");
            if (rs.next()) {
                return extractRaavareBacth(rs);
            }
        } catch (SQLException ex) {
            throw new DALException("Kunne ikke oprette forbindelse til ");
        }
        return null;
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM raavarebatches");
            ArrayList<RaavareBatchDTO>  list = new ArrayList<>();
            while (rs.next()) {
               list.add(extractRaavareBacth(rs));
            }
            return list;
        } catch (SQLException ex) {
            throw new DALException("Kunne ikke oprette forbindelse til ");
        }
    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws SQLException, ClassNotFoundException, DALException {
        try {
            PreparedStatement preparedStatement = newCon.createStatement("INSERT INTO raavarebatches (rbID, raavID, maengde) VALUES (? ,? ,?)");

            preparedStatement.setString(1, raavarebatch.getRbID());
            preparedStatement.setString(2, raavarebatch.getRaavId());
            preparedStatement.setDouble(3, raavarebatch.getMaengde());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
    {
        try
        {
            PreparedStatement preparedStatement = newCon.createStatement("UPDATE raavarebatches SET " +
                    "rbID = ?, raavID = ?, maengde = ? WHERE rbID = ?");
            preparedStatement.setString(1, raavarebatch.getRbID());
            preparedStatement.setString(2, raavarebatch.getRaavId());
            preparedStatement.setDouble(3, raavarebatch.getMaengde());
            preparedStatement.setString(4, raavarebatch.getRbID());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DALException("Encountered an error when executing given sql statement." + e.getMessage());
        }
    }

    public RaavareBatchDTO deleteRB(String rbID) throws DALException {

        try {
            PreparedStatement preparedStatement = newCon.createStatement("DELETE FROM raavarebatches WHERE rbID = ?;");
            preparedStatement.setString(1, rbID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement.");
        }
        return null;
    }


    private RaavareBatchDTO extractRaavareBacth(ResultSet rs) throws SQLException {

        RaavareBatchDTO raavareBacth = new RaavareBatchDTO(rs.getString("rbID"), rs.getString("raavID"), rs.getDouble("maengde"));

        return raavareBacth;
    }

}
