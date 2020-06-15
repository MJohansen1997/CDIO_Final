package DAO;

import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/** @author Chistensen, Jacob Kjærby (s174130@student.dtu.dk)*/

public class IncrementID {
    MySQLCon newCon;


    public IncrementID() throws SQLException, ClassNotFoundException, DALException {
        try {
            newCon = MySQLCon.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DALException("Kan ikke oprette en connection til serveren");
        }
    }


    public static void main(String[] args) throws SQLException, DALException, ClassNotFoundException {
        IncrementID incre = new IncrementID();
        incre.returnID("Recepter", "recID");
    }


    public String returnID(String tableName, String columnIDName) throws DALException {
        ArrayList<String> IDS = autoIncrementIDs(tableName, columnIDName);
        String IDNumbers = null;
        String[] part = new String[0];
        String formatString = null;
        int ID;

        for(int i = 0; i < IDS.size(); i++){
            part = IDS.get(i).split("(?<=\\D)(?=\\d)");
            ID = (Integer.parseInt(part[1]) + 1);
            String formattingString = String.format("%05d", ID);
            IDNumbers = part[0] + formattingString;
            System.out.println(IDNumbers);
        }
        return IDNumbers;
    }



    public ArrayList<String> autoIncrementIDs(String tableName, String columnIDName) throws DALException {
        try {
            ResultSet rs;
            PreparedStatement st = newCon.connection.prepareStatement("SELECT * FROM " + tableName + " ORDER BY " + columnIDName + " DESC LIMIT 1");
            rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            ArrayList<String> IDS = new ArrayList<String>();
            while(rs.next()){
                for(int i = 1; i <= columnsNumber; i++){
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(i);
                    if(columnName.contains("ID")){
                        IDS.add(columnValue);
                    }
                }

            }
            return IDS;

        } catch (SQLException throwables) {
                throw new DALException("Du har indtastet forkert tableNavn eller kollonneID navn");
        }
    }
}
