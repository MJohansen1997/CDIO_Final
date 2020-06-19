package DAO;

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
    public ProduktBatchDTO getProduktBatch(String pbId) throws DALException {
        /* Creating a try catch
         * Within creating a statement and then getting a returned resultset with the specifed query. */
        try {
            /* SQL Query */
            String query = "SELECT * FROM prodbestilling WHERE pbID = \'" + pbId + "\'";
            /* Statement to SQL */
            Statement stmt = newCon.createStatement();
            /* Resultset from the query */
            ResultSet rs = stmt.executeQuery(query);

            //Using custom method to extract the information needed from the resultset.
            if (rs.next()) {
                return extractPBLFromResultSet(rs);
            }
            throw new DALException("No results");

            /* Catching exception and throwing a new custom one */
        } catch (Exception e){
            throw new DALException("Failed to get the ProduktBatch" + e);
        }
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        try {
            /* SQL Query */
            String query = "SELECT * FROM prodbestilling";
            /* Statement to SQL */
            Statement stmt = newCon.createStatement();
            /* Resultset from the query */
            ResultSet rs = stmt.executeQuery(query);
            /* Arraylist needed to extract a full list of produktbatches */
            ArrayList<ProduktBatchDTO> pblist = new ArrayList<>();

            //Using custom method to extract the information needed from the resultset.
            if (rs.next()) {
                pblist.add(extractPBLFromResultSet(rs));
                return pblist;
            }
            throw new DALException("No results");
            /* Catching exception and throwing a new custom one */
        } catch (Exception e) {
            throw new DALException("Failed to get a ProduktBatch list " + e);
        }
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


    private ProduktBatchDTO extractPBLFromResultSet(ResultSet rs) throws SQLException {
        ProduktBatchDTO PBL = new ProduktBatchDTO(
                rs.getString("pbID"),
                rs.getString("status"),
                rs.getString("recID"));

        return PBL;
    }
}

