package DAO;

import DTO.RaavareDTO;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
/** @author Kristensen, Nikolai (S195485)*/
public class RaavareDAO implements IDAO.IRaavareDAO
{
    //Forbinder til databasen
    MySQLCon newCon;

    public RaavareDAO(){
        try{
            newCon = MySQLCon.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RaavareDTO getRaavare(String raavareId) throws DALException
    {
        //Få daten fra databasen angåene råvarer
            try
            {
                Statement stmt = newCon.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM raavarer WHERE raavID = '" + raavareId + "'");

                if (rs.next())
                {
                    return extractRavarerFromResultSet(rs);
                }
            }
            catch (SQLException ex)
            {
                throw new DALException("String message");
            }
        return null;
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws DALException
    {
        try
        {
            Statement stmt = newCon.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM raavarer");
            ArrayList<RaavareDTO> users = new ArrayList<>();
            while (rs.next())
            {
                RaavareDTO user = new RaavareDTO(rs.getString("raavID"), rs.getString("raavNavn"), rs.getString("leverandør"));
                users.add(user);
            }
            return users;
        }
        catch (SQLException ex)
        {
            throw new DALException("String messsage");
        }
        //return null;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException
    {
        try {
            PreparedStatement preparedStatement = newCon.createStatement("INSERT INTO raavarer " +
                    "(raavID, raavNavn, leverandør) VALUES (?, ?, ?);");

            preparedStatement.setString(1,raavare.getRaavID());
            preparedStatement.setString(2, raavare.getRaavNavn());
            preparedStatement.setString(3, raavare.getLeverandor());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement. : " + e.getMessage());
        }
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException
    {
        try
        {
            PreparedStatement preparedStatement = newCon.createStatement("UPDATE raavarer SET " +
                    "raavID = ?, raavNavn = ?, leverandør = ? WHERE raavID = ?");
            preparedStatement.setString(1, raavare.getRaavID());
            preparedStatement.setString(2, raavare.getRaavNavn());
            preparedStatement.setString(3, raavare.getLeverandor());
            preparedStatement.setString(4, raavare.getRaavID());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DALException("Encountered an error when executing given sql statement." + e.getMessage());
        }
    }
    public void deleteRaavarer(String raavID) throws DALException {

        try {
            PreparedStatement preparedStatement = newCon.createStatement("DELETE FROM raavarer WHERE raavID = ?;");
            preparedStatement.setString(1, raavID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Encountered an error when executing given sql statement.");
        }
    }


    private RaavareDTO extractRavarerFromResultSet(ResultSet rs) throws SQLException
    {
        RaavareDTO user = new RaavareDTO(
                rs.getString("raavID"),
                rs.getString("raavNavn"),
                rs.getString("leverandør"));

        return user;
    }
}
