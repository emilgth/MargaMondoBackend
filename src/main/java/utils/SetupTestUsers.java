package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Time;
import java.util.Date;

public class SetupTestUsers {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

//        generateUsers(em);
        AirportEntity airportEntity = new AirportEntity("Copenhagen", "CPH");
        AirportEntity airportEntity1 = new AirportEntity("Paris", "CDG");
        AirlineEntity airlineEntity = new AirlineEntity("SAS");

        FlightEntity flightEntity = new FlightEntity();
//        flightEntity.setAircraftType("Airbus 230");
        flightEntity.setAirline(airlineEntity);
        flightEntity.setArrivalLocation(airportEntity1);
        flightEntity.setDepartureLocation(airportEntity);
        long deptime = 1575154800000L;
        flightEntity.setDepartureTime(new Date(deptime));
        long arrTime = 1575154800000L + 10000000L;
//        flightEntity.setArrivalTime(new Date(arrTime));
//        flightEntity.setFlightDuration(flightEntity.getArrivalTime().getTime() - flightEntity.getDepartureTime().getTime());
//        flightEntity.setFlightNumber("AB1234");
        flightEntity.setPrice(1500);
        try {
            em.getTransaction().begin();
            em.persist(flightEntity);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }

    private static void generateUsers(EntityManager em) {
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

}
