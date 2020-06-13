package DAO;

import DTO.ProduktBatchKompDTO;

import static org.junit.jupiter.api.Assertions.*;

class ProduktBatchKompDAOTest {

    private ProduktBatchKompDTO pbk = new ProduktBatchKompDTO("pb001","rb001", 02.25, 04.25, "lab001");

    @org.junit.jupiter.api.Test
    void getProduktBatchKomp() {
        assertEquals("lab001",pbk.getLabID());
        assertNotEquals("lab01",pbk.getLabID());
    }

    @org.junit.jupiter.api.Test
    void getProduktBatchKompList() {

    }

    @org.junit.jupiter.api.Test
    void testGetProduktBatchKompList() {
    }

    @org.junit.jupiter.api.Test
    void createProduktBatchKomp() {
    }

    @org.junit.jupiter.api.Test
    void updateProduktBatchKomp() {
    }
}