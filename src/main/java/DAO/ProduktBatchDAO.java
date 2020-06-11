package DAO;

import DTO.ProduktBatchDTO;

import java.util.List;
/** @author Hansen, Mads (john.doe@example.com)*/
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
