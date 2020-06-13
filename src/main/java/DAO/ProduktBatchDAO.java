package DAO;

import DTO.ProduktBatchDTO;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
/** @author Hansen, Mads (john.doe@example.com)
 *
 * receptID
 * pbID
 * status
 *
 * (produkt bestilling tabellen)
 * DTO er et objekt som skal transportere data
 * Som forstås som en beholder.
 * N mængde
 * DTOEN BRUGES TIL AT STRUKTURERE DATAEN BEGGE VEJE (DATALAG, LOGIKLAG, PRÆSENTATIONLAG)
 * FÅ OG SENDE TIL DATABASEN. UDFYLD METODERNE
 * */
public class ProduktBatchDAO implements IDAO.IProduktBatchDAO {
    MySQLCon newCon = new MySQLCon();

    public ProduktBatchDAO () throws DALException, SQLException, ClassNotFoundException {
        try {
            newCon.setupCon();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DALException("Kan ikke oprette forbindelse til serveren");
        }
    }



    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException, SQLException, ClassNotFoundException {
        //Query to get produktbatch
        String query = "SELECT * FROM prodbestilling";

        /* Creating a try catch
         * Within creating a statement and then getting a returned resultset with the specifed query. */
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<ProduktBatchDTO> list = new ArrayList<>();

            //Iterating through the query and getting the essentials from the database
            while (rs.next()) {
                String pbID = rs.getString("pbId");
                String receptID = rs.getString("recID");
                int status = rs.getInt("status");
                //list.add();

            }
            stmt.close();
        } catch (Exception e){
            throw new DALException("Failed to get specified ProduktBatch" + e);
        }
        
        return null;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {


        //Trigger til selv at sætte status op
    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

    }


    private ProduktBatchDTO extractProduktBatchListFromResultSet(ResultSet rs) throws SQLException {
        ProduktBatchDTO produktbatchList = new ProduktBatchDTO(rs.getString("pbID"), rs.getInt("status"), rs.getString("recID"));
        ;
        return produktbatchList;
    }
}


