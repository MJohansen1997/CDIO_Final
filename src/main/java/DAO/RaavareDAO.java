package DAO;

import DTO.BrugerDTO;
import DTO.RaavareDTO;

import java.util.List;

public class RaavareDAO implements IDAO.IRaavareDAO {
    @Override
    public RaavareDTO getRaavare(int raavareId) throws DALException {
        return null;
    }

    @Override
    public List<BrugerDTO> getRaavareList() throws DALException {
        return null;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {

    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {

    }
}
