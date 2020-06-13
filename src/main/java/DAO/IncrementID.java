package DAO;

import java.sql.ResultSet;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IncrementID {
    MySQLCon newCon = new MySQLCon();


    public IncrementID() throws SQLException, ClassNotFoundException, DALException {
        try {
            newCon.setupCon();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DALException("Kan ikke oprette en connection til serveren");
        }
    }

    ArrayList<String> IDS = autoIncrementIDs("brugerer", "brugerID");
    String IDs = returnID(IDS);

    public String returnID(ArrayList<String> IDS){
        String[] IDNumbers = new String[IDS.size()];
        String[] part = new String[0];
        String formatString = null;
        int ID;

        for(int i = 0; i < IDS.size(); i++){
            part = IDS.get(i).split("(?<=\\D)(?=\\d)");
            ID = (Integer.parseInt(part[1]) + 1);
            String formattingString = String.format("%05d", ID);
            IDNumbers[i] = part[0] + formattingString;
            System.out.println(IDNumbers[i]);
        }
        return part[0] + formatString;
    }



    public ArrayList<String> autoIncrementIDs(String tableName, String columnIDName){
        try {
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs;
            PreparedStatement st = newCon.connection.prepareStatement("SELECT * FROM ? ORDER BY ? DESC LIMIT 1");
            st.setString(1,tableName);
            st.setString(2, columnIDName);
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
            throwables.printStackTrace();
        }
        return null;
    }

}
