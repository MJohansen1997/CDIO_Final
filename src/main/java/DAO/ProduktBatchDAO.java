package DAO;

import DTO.ProduktBatchDTO;

import java.util.List;
/** @author Hansen, Mads (john.doe@example.com)
 *
 * receptID
 * pbID
 * status
 *
 * (produkt bestilling tabellen)
 * DTO er et objekt som skal transportere data
 * Som forstås som en beholder.
 * N mængde
 * DTOEN BRUGES TIL AT STRUKTURERE DATAEN BEGGE VEJE (DATALAG, LOGIKLAG, PRÆSENTATIONLAG)
 * FÅ OG SENDE TIL DATABASEN. UDFYLD METODERNE
 * */
public class ProduktBatchDAO implements IDAO.IProduktBatchDAO {
    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

    }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {

    }
}
