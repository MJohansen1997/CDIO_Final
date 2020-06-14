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
        //BrugerDTO user = new BrugerDTO("B0001", "mikkel","MJ" ,"000000-0000",
        //        "Farmaceut", "0000" );
        return BDAO.verifyUser(name, password);
    }
}
