package DAO;

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
    public RaavareBatchDTO getRaavareBatch(String rbId) throws DALException, SQLException, ClassNotFoundException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM raavarebatches WHERE rbID='" + rbId+"'");
            if (rs.next()) {
                return extractRaavareBacth(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList() throws SQLException, ClassNotFoundException, DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM raavarebatches");
            ArrayList<RaavareBatchDTO>  list = new ArrayList<>();
            while (rs.next()) {
               list.add(extractRaavareBacth(rs));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList(String raavareId) throws SQLException, ClassNotFoundException, DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ravaarebatches WHERE raavareId=" + raavareId);
            ArrayList<RaavareBatchDTO>  list = new ArrayList<>();
            while (rs.next()) {
                list.add(extractRaavareBacth(rs));
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws SQLException, ClassNotFoundException, DALException {
        try {
            PreparedStatement preparedStatement = newCon.createStatement("INSERT INTO Raavarebatches (rbId, raavareID, maengde) VALUES (? ,? ,?)");

            preparedStatement.setString(1, raavarebatch.getRbID());
            preparedStatement.setString(2, raavarebatch.getRaavareId());
            preparedStatement.setDouble(3, raavarebatch.getMaengde());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {

    }

    public RaavareBatchDTO extractRaavareBacth(ResultSet rs) throws SQLException {

        RaavareBatchDTO raavareBacth = new RaavareBatchDTO(rs.getString("rbID"), rs.getString("raavID"),
                rs.getDouble("maengde"));

        return raavareBacth;
    }

}
