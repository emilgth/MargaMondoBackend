package facades;

import dtos.FlightDTO;
import entities.AirlineEntity;
import entities.AirportEntity;
import entities.FlightEntity;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

import java.util.Date;
import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
class FlightFacadeTest {

    private static EntityManagerFactory emf;
    private static FlightFacade flightFacade;
    private static FlightDTO flightDTO1, flightDTO2, flightDTO3;

    FlightFacadeTest() {
    }

    @BeforeAll
    static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        flightFacade = FlightFacade.getFlightFacade(emf);
    }

    @AfterAll
    static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
        AirportEntity airportEntity1 = new AirportEntity("Copenhagen", "CPH");
        AirportEntity airportEntity2 = new AirportEntity("Paris", "CDG");

        AirlineEntity airlineEntity1 = new AirlineEntity("SAS");
        AirlineEntity airlineEntity2 = new AirlineEntity("KLM");
        AirlineEntity airlineEntity3 = new AirlineEntity("Air France");

        FlightEntity flight1 = new FlightEntity();
        FlightEntity flight2 = new FlightEntity();
        FlightEntity flight3 = new FlightEntity();

        flight1.setDepartureLocation(airportEntity1);
        flight2.setDepartureLocation(airportEntity1);
        flight3.setDepartureLocation(airportEntity2);

        flight1.setArrivalLocation(airportEntity2);
        flight2.setArrivalLocation(airportEntity2);
        flight3.setArrivalLocation(airportEntity1);

        flight1.setAirline(airlineEntity1);
        flight2.setAirline(airlineEntity2);
        flight3.setAirline(airlineEntity3);

        flight1.setAircraftType("Airbus 230");
        flight2.setAircraftType("Boing 777");
        flight3.setAircraftType("Airbus a330");

        long depTime = 1575154800000L;
        long arrTime = depTime + 10000000L;
        long dayInMilliseconds = 86400000L;

        flight1.setDepartureTime(new Date(depTime));
        flight2.setDepartureTime(new Date(depTime + dayInMilliseconds));
        flight3.setDepartureTime(new Date(depTime + 2 * dayInMilliseconds));

        flight1.setArrivalTime(new Date(arrTime));
        flight2.setArrivalTime(new Date(arrTime + dayInMilliseconds));
        flight3.setArrivalTime(new Date(arrTime + 2 * dayInMilliseconds));

        flight1.setFlightDuration(flight1.getArrivalTime().getTime() - flight1.getDepartureTime().getTime());
        flight2.setFlightDuration(flight2.getArrivalTime().getTime() - flight2.getDepartureTime().getTime());
        flight3.setFlightDuration(flight3.getArrivalTime().getTime() - flight3.getDepartureTime().getTime());

        flight1.setFlightNumber("AB1234");
        flight2.setFlightNumber("XY9876");
        flight3.setFlightNumber("QW4567");

        flight1.setPrice(252.40);
        flight2.setPrice(319.99);
        flight3.setPrice(295.95);

        flightDTO1 = new FlightDTO(flight1);
        flightDTO2 = new FlightDTO(flight2);
        flightDTO3 = new FlightDTO(flight3);

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flight1);
            em.persist(flight2);
            em.persist(flight3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("FlightEntity.deleteAllRows");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void getAllFlights() {
        List<FlightDTO> flights = flightFacade.getAllFlights();
        assertThat(flights, containsInAnyOrder(flightDTO1, flightDTO2, flightDTO3));
        assertEquals(3, flights.size());
    }
}