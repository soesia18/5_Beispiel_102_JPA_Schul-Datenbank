package at.kaindorf.console;

import at.kaindorf.bl.Floor;
import at.kaindorf.io.IOAccess;
import at.kaindorf.pojo.ClassTeacher;
import at.kaindorf.pojo.Classname;
import at.kaindorf.pojo.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;


public class DataHandler {
    private EntityManagerFactory emf;
    private EntityManager em;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        dataHandler.open();
        dataHandler.importTables();


        System.out.print("classes imported: ");
        dataHandler.em.createNamedQuery("Classname.countAll", Long.class).getResultList().forEach(System.out::println);

        System.out.print("rooms imported: ");
        dataHandler.em.createNamedQuery("Room.countAll", Long.class).getResultList().forEach(System.out::println);

        System.out.print("teachers imported: ");
        dataHandler.em.createNamedQuery("ClassTeacher.countAll", Long.class).getResultList().forEach(System.out::println);

        dataHandler.findByFloor();
        dataHandler.findByClassName();
        dataHandler.findByName();
        dataHandler.findByClassname();
        dataHandler.findByGrade();
        dataHandler.findByLastName();

        dataHandler.close();
    }


    public void open() {
        emf = Persistence.createEntityManagerFactory("PU_classdb");
        em = emf.createEntityManager();
    }

    public void importTables() {
        IOAccess.importClassname().forEach(em::persist);

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void close() {
        scanner.close();
        em.close();
        emf.close();
    }

    public void findByFloor() {
        System.out.println("------------------------------");
        System.out.println("[1] Ground Floor");
        System.out.println("[2] First Floor");

        int choice = scanner.nextInt();

        Floor floor = (choice == 1 ? Floor.GROUND : Floor.FIRST);

        System.out.println("Get all " + floor + " Floor classes");

        em
                .createNamedQuery("Classname.findByFloor", Classname.class)
                .setParameter("floor", floor)
                .getResultList()
                .forEach(System.out::println);

        System.out.println("Get all " + floor + " Floor rooms");

        em
                .createNamedQuery("Room.findByFloor", Room.class)
                .setParameter("floor", floor)
                .getResultList()
                .forEach(System.out::println);

    }

    public void findByClassName() {
        System.out.println("------------------------------");
        System.out.println("Classname: ");

        String classname = scanner.next();
        System.out.println("Class: " + classname);

        em
                .createNamedQuery("Room.findByClassName", Room.class)
                .setParameter("className", classname)
                .getResultList()
                .forEach(System.out::println);
    }

    public void findByName () {
        System.out.println("------------------------------");
        System.out.println("Classname: ");

        String classname = scanner.next();
        System.out.println("Class: " + classname);

        em
                .createNamedQuery("Classname.findByName", Classname.class)
                .setParameter("name", classname)
                .getResultList()
                .forEach(System.out::println);
    }

    public void findByClassname () {
        System.out.println("------------------------------");
        System.out.println("Classname: ");

        String classname = scanner.next();
        System.out.println("Teacher from : " + classname);

        em
                .createNamedQuery("ClassTeacher.findByClassname", ClassTeacher.class)
                .setParameter("className", classname)
                .getResultList()
                .forEach(System.out::println);
    }

    public void findByGrade () {
        System.out.println("------------------------------");
        System.out.println("Grade: ");

        int grade = scanner.nextInt();
        System.out.println("Teacher from : " + grade);

        em
                .createNamedQuery("ClassTeacher.findByGrade", ClassTeacher.class)
                .setParameter("grade", grade)
                .getResultList()
                .forEach(System.out::println);
    }

    public void findByLastName () {
        System.out.println("------------------------------");
        System.out.println("Lastname: ");

        String lastname = scanner.next();

        em
                .createNamedQuery("ClassTeacher.findByName", ClassTeacher.class)
                .setParameter("lastname", lastname)
                .getResultList()
                .forEach(System.out::println);
    }
}
