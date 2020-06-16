package API;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/HomePage")
public class APIRaavare
{
    @POST
    @Path("form")
    public String ADD(@FormParam("IDName") String IDName, @FormParam("rName") String rName, @FormParam("lName") String lName)
    {
        //RaavareDAO RaaDAO = new RaavareDAO();


        //return System.out.println(IDName, rName,lName);
        return ADD(IDName,rName,lName);

        //return null;
    }



}
