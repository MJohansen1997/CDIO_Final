package DAO;

import DTO.BrugerDTO;
import DTO.ProduktBatchDTO;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
/** @author Hansen, Mads Østerlund (s195456@student.dtu.dk) **/
public class ProduktBatchDAO implements IDAO.IProduktBatchDAO {
    MySQLCon newCon;

    public ProduktBatchDAO () throws DALException {
        try {newCon = MySQLCon.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DALException("Cannot establish a connection to the server! " + e);
        }
    }


    @Override
    public ProduktBatchDTO getProduktBatch(String pbID) throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prodbestilling WHERE pbID = \'" + pbID + "\'");
            if (rs.next()) {
                return extractPBLFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM prodbestilling");
            ArrayList<ProduktBatchDTO> pblist = new ArrayList<>();
            while (rs.next()) {
                ProduktBatchDTO user = extractPBLFromResultSet(rs);
                pblist.add(user);
            }
            return pblist;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
        try {

            /* Statement to SQL */
            Statement stmt = newCon.createStatement();
            /* SQL Query to insert values */
            String query = "INSERT INTO prodbestilling VALUES (" +
                    "'" + produktbatch.getPbID() + "'" + ", " +
                    "'" + produktbatch.getReceptID() + "'" + ", " +
                    "'" + produktbatch.getStatus() + "'";

            /* Resultset from the query */
            ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            throw new DALException("Error! Couldn't insert desired values");
        }
    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
        try {

            /* SQL Statement & Query to update values */
            PreparedStatement statusQuery = newCon.createStatement
                    ("UPDATE prodbestilling SET status = ? WHERE pbID = ?");

            statusQuery.setString(1, produktbatch.getStatus());
            statusQuery.setString(2, produktbatch.getPbID());
            statusQuery.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("Error! Couldn't update desired values");
        }
    }


    public void deleteProduktBatch(String pbID) throws DALException {

        try {
            PreparedStatement preparedStatement = newCon.createStatement("DELETE FROM prodbestilling WHERE pbID = ?;");
            preparedStatement.setString(1, pbID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql delete statement.");
        }
    }

    private ProduktBatchDTO extractPBLFromResultSet(ResultSet rs) throws SQLException {
        ProduktBatchDTO PBL = new ProduktBatchDTO(
                rs.getString("pbID"),
                rs.getString("status"),
                rs.getString("recID"));

        return PBL;
    }
}

