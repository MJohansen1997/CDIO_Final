package DAO;

import DTO.PrintDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintDAO implements IDAO.IPrintDAO{
    MySQLCon newCon;

    public PrintDAO() throws DALException {
        try {
            newCon = MySQLCon.getInstance();
        }
        catch (SQLException | ClassNotFoundException e){
            throw new DALException("kunne ikke forbinde til databasen");
        }
    }

    @Override
    public PrintDTO getPrint(String pbID, String status, String recID) throws DALException, SQLException, ClassNotFoundException {
        try {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery(
                    " SELECT * FROM ((prodbestilling NATURAL JOIN produktbatches)\n" +
                            "    NATURAL JOIN raavarebatches);");
            if (rs.next()) {
                return extractPrintFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private PrintDTO extractPrintFromResultSet(ResultSet rs) throws SQLException {
        PrintDTO print = new PrintDTO(rs.getString("pbID"),
                rs.getString("status"),
                rs.getString("recID"),
                rs.getString("rbID"),
                rs.getDouble("tara"),
                rs.getDouble("netto"),
                rs.getString("rbID"),
                rs.getTimestamp("startdato"),
                rs.getTimestamp("slutdato"),
                rs.getString("raavID"),
                rs.getDouble("maengde")
                );
        return print;
    }
}
