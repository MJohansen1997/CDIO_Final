package DAO;

import DTO.RaavareBatchDTO;

import java.sql.ResultSet;
import java.sql.*;
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
    public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException, SQLException, ClassNotFoundException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ravaarebatches WHERE rbId=" + rbId);
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
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ravaarebatches");
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
    public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws SQLException, ClassNotFoundException, DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
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
        /*try {
            PreparedStatement preparedStatement = newCon.connection.prepareStatement("INSERT INTO Raavarebatches (rbId, raavareID, maengde) VALUES (? ,? ,?)");

            ArrayList<String> ar1 = IDCreate.autoIncrementIDs("Raavarebatches" ,"rbId");
            ArrayList<String> ar2 = IDCreate.autoIncrementIDs("Raavarebatches" ,"rbId");

            preparedStatement.setString(1, raavarebatch.getRbID());
            preparedStatement.setString(2, raavarebatch.getRaavareId());
            preparedStatement.setDouble(3, raavarebatch.getMaengde());

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }*/
    }

    @Override
    public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {

    }

    public RaavareBatchDTO extractRaavareBacth(ResultSet rs) throws SQLException {

        RaavareBatchDTO raavareBacth = new RaavareBatchDTO(rs.getString("rbID"), rs.getString("raavareId"),
                rs.getDouble("maengde"));

        return raavareBacth;
    }

}
