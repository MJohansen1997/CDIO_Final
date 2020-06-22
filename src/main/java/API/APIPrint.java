package API;

import DAO.DALException;
import DAO.ProduktBatchDAO;
import DAO.ReceptKompDAO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/Print")
public class APIPrint {

    @POST
    @Path("/pb")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProduktbatch(@QueryParam("recID") String recID, @QueryParam("pbID") String pbID){
        String r;
        String p;
        try {
            ReceptKompDAO rdao = new ReceptKompDAO();
            ProduktBatchDAO pdao = new ProduktBatchDAO();

            r = rdao.getReceptKomp(recID, pbID).getReceptID();
            p = pdao.getProduktBatch(pbID).getPbID();

            return "{\"receptID\":\"" + r + "\",\"produktID\":\"" + p + "\"}";
        }catch (DALException e) {
            e.printStackTrace();
            return "";
        }
    }


    //idk
    /*@POST
    @Path("/getReckompList")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getreckomplist (@PathParam(recID)String recID) throws DALException {
        ReceptKompDAO receptKompDAO = new ReceptKompDAO();
        try {
            receptKompDAO.getReceptKompList(recID);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }*/
}
