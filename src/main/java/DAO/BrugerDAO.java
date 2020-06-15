package DAO;



import DTO.BrugerDTO;

import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/


public class BrugerDAO implements IDAO {
    MySQLCon newCon = MySQLCon.getInstance();
    IncrementID IDCreate = new IncrementID();

    public BrugerDAO() throws SQLException, ClassNotFoundException, DALException {
        try {
            newCon.setupCon();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DALException("Kan ikke oprette en connection til serveren");
        }
    }

    @Override
    public BrugerDTO getBruger(String brugerID) throws DALException, SQLException, ClassNotFoundException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brugerer WHERE brugerID = \'" + brugerID + "\'");
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
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM brugerer");
            ArrayList<BrugerDTO> users = new ArrayList<>();
            while (rs.next()) {
                BrugerDTO user = new BrugerDTO(rs.getString("brugerID"), rs.getString("brugerNavn"),
                        rs.getString("ini"), rs.getString("cpr"), rs.getString("rolle"), rs.getString("password"));
                users.add(user);
            }
            return users;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        try {
            PreparedStatement preparedStatement = newCon.connection.prepareStatement("INSERT INTO brugerer " +
                    "(brugerID, brugerNavn, ini, cpr, rolle, password) VALUES (?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1,opr.getBrugerID());
            preparedStatement.setString(2, opr.getBrugerNavn());
            preparedStatement.setString(3, opr.getIni());
            preparedStatement.setString(4, opr.getCpr());
            preparedStatement.setString(5, opr.getRolle());
            preparedStatement.setString(6, opr.getPassword());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement. : " + e.getMessage());
        }
    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

        try {
            PreparedStatement preparedStatement = newCon.connection.prepareStatement("UPDATE brugerer SET " +
                    "brugerID = ?, brugerNavn = ?, ini = ?, cpr = ?, rolle = ?, password = ? WHERE brugerID = ?");
            preparedStatement.setString(1, opr.getBrugerID());
            preparedStatement.setString(2, opr.getBrugerNavn());
            preparedStatement.setString(3, opr.getIni());
            preparedStatement.setString(4, opr.getCpr());
            preparedStatement.setString(5, opr.getRolle());
            preparedStatement.setString(6, opr.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement." + e.getMessage());
        }
    }

    public boolean verifyUser(String username, String password) throws DALException {
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT brugerNavn, password FROM brugerer " +
                    "WHERE brugerNavn = \'" + username + "\'");
            if (rs.next()) {
                return rs.getString("brugerNavn").equals(username)
                        && rs.getString("password").equals(password);
            }
        } catch (SQLException ex) {
            throw new DALException("Forkert brugernavn eller adgangskode");
        }
        return false;
    }

    private BrugerDTO extractUserFromResultSet(ResultSet rs) throws SQLException {
        BrugerDTO user = new BrugerDTO(rs.getString("brugerID"), rs.getString("brugerNavn"),
                rs.getString("ini"), rs.getString("cpr"), rs.getString("rolle"), rs.getString("password"));
        return user;
    }
}
