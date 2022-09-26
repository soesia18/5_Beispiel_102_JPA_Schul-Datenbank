package at.kaindorf.console;

import at.kaindorf.bl.Floor;
import at.kaindorf.io.IOAccess;
import at.kaindorf.pojo.Classname;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class DataHandler {
    private EntityManagerFactory emf;
    private EntityManager em;

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        dataHandler.open();
        dataHandler.importTables();

        //dataHandler.em.createNamedQuery("Classname.findByFloor", Classname.class).setParameter("floor", Floor.FIRST).getResultList().forEach(System.out::println);


        dataHandler.close();
    }



    private void open() {
        emf = Persistence.createEntityManagerFactory("PU_102_jpa_schooldb");
        em = emf.createEntityManager();
    }

    private void importTables() {
        IOAccess.importClassname().forEach(em::persist);

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    private void close() {
        em.close();
        emf.close();
    }
}
