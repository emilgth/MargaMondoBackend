package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SetupTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);

        persistFlightEntities(emf);
        persistUserEntities(emf);
        persistSearchLogEntities(emf);
        persistBookingLogEntities(emf);
    }

    private static void persistFlightEntities(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        AirportEntity airportEntity0 = new AirportEntity("Copenhagen", "CPH");
        AirportEntity airportEntity1 = new AirportEntity("Paris", "CDG");
        AirlineEntity airlineEntity0 = new AirlineEntity("SAS");
        AirlineEntity airlineEntity1 = new AirlineEntity("Air France");

        FlightEntity flightEntity0 = new FlightEntity();
        FlightEntity flightEntity1 = new FlightEntity();

        flightEntity0.setAirline(airlineEntity0);
        flightEntity1.setAirline(airlineEntity1);

        flightEntity0.setDepartureLocation(airportEntity0);
        flightEntity1.setDepartureLocation(airportEntity1);

        flightEntity0.setArrivalLocation(airportEntity1);
        flightEntity1.setArrivalLocation(airportEntity0);

        long depTime0 = 1575154800000L;
        long depTime1 = 1575759600000L;
        flightEntity0.setDepartureTime(new Date(depTime0));
        flightEntity1.setDepartureTime(new Date(depTime1));

        flightEntity0.setPrice(150);
        flightEntity1.setPrice(175);

        try {
            em.getTransaction().begin();
            em.persist(flightEntity0);
            em.persist(flightEntity1);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    private static void persistUserEntities(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

        User user = new User("user", "test");
        User admin = new User("admin", "test");
        User both = new User("user_admin", "test");

        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test"))
            throw new UnsupportedOperationException("You have not changed the passwords");

        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
        System.out.println("PW: " + user.getUserPass());
        System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");
    }

    private static void persistSearchLogEntities(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Date date_01_12 = new GregorianCalendar(2019, Calendar.DECEMBER, 1).getTime();
        Date date_05_12 = new GregorianCalendar(2019, Calendar.DECEMBER, 5).getTime();
        SearchLogEntity searchLogEntity0 = new SearchLogEntity("CPH", "CDG", date_01_12.getTime());
        SearchLogEntity searchLogEntity1 = new SearchLogEntity("CPH", "CDG", date_01_12.getTime(), date_05_12.getTime());

        try {
            em.getTransaction().begin();
            em.persist(searchLogEntity0);
            em.persist(searchLogEntity1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void persistBookingLogEntities(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        Date date_01_12 = new GregorianCalendar(2019, Calendar.DECEMBER, 1).getTime();
        Date date_05_12 = new GregorianCalendar(2019, Calendar.DECEMBER, 5).getTime();
        BookingLogEntity bookingLogEntity0 = new BookingLogEntity("CPH", "CDG", date_01_12.getTime(), null, 450.0, 2, 2, 7200000L);
        BookingLogEntity bookingLogEntity1 = new BookingLogEntity("CPH", "CDG", date_01_12.getTime(), date_05_12.getTime(), 140.0, 1, null, 8100000L);

        try {
            em.getTransaction().begin();
            em.persist(bookingLogEntity0);
            em.persist(bookingLogEntity1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
