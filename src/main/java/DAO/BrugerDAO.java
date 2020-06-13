package DAO;



import DTO.BrugerDTO;

import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/


public class BrugerDAO implements IDAO {
    MySQLCon newCon = new MySQLCon();

    public BrugerDAO() throws SQLException, ClassNotFoundException {
    }

    @Override
    public BrugerDTO getBruger(String brugerID) throws DALException, SQLException, ClassNotFoundException {
        newCon.setupCon();
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brugere WHERE id=" + brugerID);
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

    }

    private BrugerDTO extractUserFromResultSet(ResultSet rs) throws SQLException {
        BrugerDTO user = new BrugerDTO(rs.getString("brugerID"), rs.getString("brugerNavn"), rs.getString("ini"), rs.getString("cpr"),rs.getString("password"));
        return user;
    }
}
