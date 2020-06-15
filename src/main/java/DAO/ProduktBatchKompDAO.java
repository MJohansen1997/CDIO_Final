package DAO;

import DTO.ProduktBatchKompDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hau, Shania (john.doe@example.com)
 */
public class ProduktBatchKompDAO implements IDAO.IProduktBatchKompDAO {

    MySQLCon newCon;
    public ProduktBatchKompDAO(){
        try{newCon = MySQLCon.getInstance();} catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(String pbId, String rbId) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produktbatches WHERE id = \'" + pbId + "\' AND \'" + rbId+"\'");
        } catch (SQLException ex) {
            throw new DALException("kunne ikke finde ønsket information");
        }
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(String pbId) throws DALException {

        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produktbatches WHERE pbid = '" + pbId + "'");

            List<ProduktBatchKompDTO> alleprodukter = new ArrayList<ProduktBatchKompDTO>();

            while (rs.next()) {
                alleprodukter.add(extractProduktBatchKompListFromResultSet(rs));
            }
            return alleprodukter;

        } catch (SQLException ex) {
            throw new DALException("kunne ikke finde ønsket information");
        }

    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produktbatches");

            List<ProduktBatchKompDTO> alleprodukter = new ArrayList<ProduktBatchKompDTO>();

            while (rs.next()) {
                alleprodukter.add(extractProduktBatchKompListFromResultSet(rs));
            }

        } catch (SQLException ex) {
            throw new DALException("kunne ikke finde ønsket information");
        }
        return null;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery
                    ("insert into produktbatches values ("
                            + pbk.getPbId() + ","
                            + pbk.getRbId() + ","
                            + pbk.getLabID() + ","
                            + pbk.getTara() + ","
                            + pbk.getNetto() + ");");


        } catch (SQLException ex) {
            throw new DALException("kunne ikke finde ønsket information");
        }

    }

    public void updateProduktBatchKomp(ProduktBatchKompDTO pbk) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            stmt.executeQuery("update produktbatches set " +
                    "pbID = " + pbk.getPbId() +
                    ", rbID = " + pbk.getRbId() +
                    ", laborant = " + pbk.getLabID() +
                    ", tara = " + pbk.getTara() +
                    ", netto = " + pbk.getNetto() +
                    "where pbID =" + pbk.getPbId());

        } catch (SQLException ex) {
            throw new DALException("kunne ikke finde ønsket information");
        }
    }

    private ProduktBatchKompDTO extractProduktBatchKompListFromResultSet(ResultSet rs) throws SQLException {
        ProduktBatchKompDTO produktbatchkompList = new ProduktBatchKompDTO(rs.getString("pbID"), rs.getString("rbID"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getString("laborant"));
        return produktbatchkompList;
    }

}