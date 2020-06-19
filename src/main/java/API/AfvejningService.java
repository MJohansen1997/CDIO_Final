package API;

import DAO.*;
import DTO.*;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/afvejning")
public class AfvejningService {

    @POST
    @Path("/verifylab")
    public boolean verifyLaborant(@FormParam("userid") String labid){

        return true;
    }

    @POST
    @Path("/verifyprod")
    @Produces(MediaType.APPLICATION_JSON)
    public ReceptDTO verifyProdID(@FormParam("produktionsid") String prodID){
        try {
            ProduktBatchDAO PDAO = new ProduktBatchDAO();
            ProduktBatchDTO PB = PDAO.getProduktBatch(prodID);
            ReceptDAO RDAO = new ReceptDAO();
            return RDAO.getRecept(PB.getReceptID());
        }
        catch (DALException e){
            return null;
        }
    }

    @POST
    @Path("/loadprodkomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> loadprodKomps(@FormParam("produktionsid") String prodid){
        try {
            ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
            return dao.getProduktBatchKompList(prodid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/loadreckomps")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> loadKomps(@FormParam("receptid") String recid){
        try {
            ReceptKompDAO dao = new ReceptKompDAO();
            return dao.getReceptKompList(recid);
        } catch (DALException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/IdBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public String verifyIdBatch(@QueryParam("rid") String rid, @QueryParam("rbid") String rbid){
        String r;
        String s;
        try {
            RaavareBatchDAO rbdao = new RaavareBatchDAO();
            RaavareDAO rdao = new RaavareDAO();
            r = rdao.getRaavare(rid).getRaavNavn();
            s = String.valueOf(rid.equals(rbdao.getRaavareBatch(rbid).getRaavId()));
            return "{\"status\":\"" + s + "\",\"name\":\"" + r + "\"}";
        }catch (DALException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @PUT
    @Path("/updatestatus")
    public void changeStatus(@QueryParam("prodid") String prodid, @QueryParam("status") String status){
        try{
            ProduktBatchDAO dao = new ProduktBatchDAO();
            ProduktBatchDTO dto = dao.getProduktBatch(prodid);
            dto.setStatus(status);
            dao.updateProduktBatch(dto);

        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/createpbk")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createBruger(String jsonBody) throws SQLException, DALException, ClassNotFoundException {
        IncrementID incre = new IncrementID();
        ProduktBatchKompDAO dao = new ProduktBatchKompDAO();
        JSONObject json = new JSONObject(jsonBody);

        ProduktBatchKompDTO user = new ProduktBatchKompDTO(
                json.getString("pbId"),
                json.getString("rbId"),
                json.getDouble("tara"),
                json.getDouble("netto"),
                json.getString("labID")
        );
        dao.createProduktBatchKomp(user);
    }

    @GET
    @Path("/findraavid/{rbid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBruger(@PathParam("rbid") String rbid) throws ClassNotFoundException, DALException, SQLException {
        try {
            RaavareBatchDAO dao = new RaavareBatchDAO();
            return dao.getRaavareBatch(rbid).getRaavId();
        } catch (SQLException | ClassNotFoundException | DALException e) {
            e.printStackTrace();
            return "";
        }
    }
}
