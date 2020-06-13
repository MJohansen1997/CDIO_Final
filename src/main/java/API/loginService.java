package API;

import DAO.BrugerDAO;
import DAO.DALException;
import DTO.BrugerDTO;

import javax.ws.rs.*;
import java.sql.SQLException;

@Path("/login")
public class loginService{

    @POST
    @Path("/verify")
    public boolean verification(@FormParam("Username") String name, @FormParam("Password") String password)
            throws SQLException, ClassNotFoundException, DALException {
        BrugerDAO BDAO = new BrugerDAO();
        //BrugerDTO user = BDAO.getBruger(name);
        BrugerDTO user = new BrugerDTO("B0001", "mikkel","MJ" ,"000000-0000", "0000");
        return user.getBrugerNavn().equals(name) && user.getPassword().equals(password);
    }
}
