package DAO;

import DTO.ProduktBatchKompDTO;

import java.util.List;
/** @author Hau, Shania (john.doe@example.com)*/
public class ProduktBatchKompDAO implements IDAO.IProduktBatchKompDAO {
    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {

    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {

    }
}
