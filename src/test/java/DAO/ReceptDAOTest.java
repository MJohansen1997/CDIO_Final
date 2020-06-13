package DAO;

import DTO.ReceptDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceptDAOTest {

    private ReceptDTO recept2 = new ReceptDTO("rec002","recNavn");


    @Test
    void getRecept() throws DALException {

        ReceptDAO DAO = new ReceptDAO();
        ReceptDTO recept = new ReceptDTO("rec001","receptNavn");

        DAO.createRecept(recept);
        DAO.getRecept("rec001");



        assertEquals("rec001",recept.getReceptID());
        assertNotEquals("lab001",recept.getReceptID());

        assertEquals("receptNavn",recept.getReceptNavn());
        assertNotEquals("lab001",recept.getReceptNavn());
    }

    @Test
    void getReceptList() throws DALException {
        ReceptDAO DAO = new ReceptDAO();
        for (ReceptDTO dto : DAO.getReceptList()) {
            System.out.println(dto.getReceptID());
            System.out.println(dto.getReceptNavn());
        }


    }

    @Test
    void createRecept() throws DALException {
        ReceptDAO DAO = new ReceptDAO();
        ReceptDTO recept3 = new ReceptDTO("rec003","receptNavn");

        DAO.createRecept(recept3);
        DAO.getRecept("rec003");

    }

    @Test
    void updateRecept() throws DALException {
        ReceptDAO DAO = new ReceptDAO();

        DAO.updateRecept(recept2);
    }
}