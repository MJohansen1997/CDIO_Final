package DAO;

import DTO.BrugerDTO;
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
                Statement stmt = newCon.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM raavarer WHERE id = '" + raavareId + "'");

                if (rs.next())
                {
                    return extractUserFromResultSet(rs);
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
            Statement stmt = newCon.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM userinformations");
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
        try
        {
            Statement stmt = newCon.connection.createStatement();
            stmt.executeQuery("INSERT INTO raavarer VALUES "
                    + raavare.getRaavareID()
                    + raavare.getRaavareNavn()
                    + raavare.getLeverandoer());
        }
        catch (SQLException throwables)
        {
            throw new DALException("String messsage");
        }
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException
    {
        try
        {

            //Forbinder og laver en variable sql statment for Råvare Navn
            PreparedStatement pSNavn = newCon.connection.prepareStatement
                    ("UPDATE raavarer SET raavNavn = ? WHERE userID = ?");

             pSNavn.setString(1, raavare.getRaavareNavn());
             pSNavn.setString(2, raavare.getRaavareID());

            //laver en variable sql statment for Leverandør
           PreparedStatement pSLev = newCon.connection.prepareStatement
                   ("UPDATE raavarer SET levenrandør = ? WHERE userID = ?");

            pSLev.setString(1, raavare.getLeverandoer());
            pSLev.setString(2, raavare.getRaavareID());
        }
        catch (SQLException throwables)
        {
            throw new DALException("String messsage");
        }
    }

    private RaavareDTO extractUserFromResultSet(ResultSet rs) throws SQLException
    {
        RaavareDTO user = new RaavareDTO(
                rs.getString("raavID"),
                rs.getString("raavNavn"),
                rs.getString("leverandør"));

        return user;
    }
}
