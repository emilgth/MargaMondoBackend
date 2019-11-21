package rest;

import dtos.FlightDTO;
import dtos.FlightSearchDTO;
import entities.AirlineEntity;
import entities.AirportEntity;
import entities.FlightEntity;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
class FlightResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    private static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static FlightDTO flightDTO1, flightDTO2, flightDTO3;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
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

//        flight1.setAircraftType("Airbus 230");
//        flight2.setAircraftType("Boing 777");
//        flight3.setAircraftType("Airbus a330");

        long depTime = 1575154800000L;
        long arrTime = depTime + 10000000L;
        long dayInMilliseconds = 86400000L;

        flight1.setDepartureTime(new Date(depTime));
        flight2.setDepartureTime(new Date(depTime + dayInMilliseconds));
        flight3.setDepartureTime(new Date(depTime + 2 * dayInMilliseconds));

//        flight1.setArrivalTime(new Date(arrTime));
//        flight2.setArrivalTime(new Date(arrTime + dayInMilliseconds));
//        flight3.setArrivalTime(new Date(arrTime + 2 * dayInMilliseconds));
//
//        flight1.setFlightDuration(flight1.getArrivalTime().getTime() - flight1.getDepartureTime().getTime());
//        flight2.setFlightDuration(flight2.getArrivalTime().getTime() - flight2.getDepartureTime().getTime());
//        flight3.setFlightDuration(flight3.getArrivalTime().getTime() - flight3.getDepartureTime().getTime());

//        flight1.setFlightNumber("AB1234");
//        flight2.setFlightNumber("XY9876");
//        flight3.setFlightNumber("QW4567");

        flight1.setPrice(252.40);
        flight2.setPrice(319.99);
        flight3.setPrice(295.95);

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

        flightDTO1 = new FlightDTO(flight1);
        flightDTO2 = new FlightDTO(flight2);
        flightDTO3 = new FlightDTO(flight3);
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("SELECT flight FROM FlightEntity flight", FlightEntity.class).getResultList().forEach((em::remove));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterAll
    static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @Test
    void demo() {
    }

    @Test
    void getAllFlightsSize() {
        given()
                .contentType("application/json")
                .get("flights/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("", hasSize(3));
    }

    @Test
    void getAllFlightsContent() {
        List<FlightDTO> flightDTOS = given()
                .contentType("application/json")
                .get("flights/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .body().jsonPath().getList("", FlightDTO.class);

        assertThat(flightDTOS, containsInAnyOrder(flightDTO1, flightDTO2, flightDTO3));
    }

    @Test
    @Disabled
    void flightSearchSize() throws ParseException {
        given()
                .body("{ \"destination\": \"Paris\", \"departure\": \"Copenhagen\", \"dateTime\": \"2019-12-02\" }")
                .contentType("application/json")
                .post("flights/search").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("", hasSize(1));
    }

    @Test
    @Disabled
    void flightSearchContent() {
        List<FlightDTO> flightDTOS = given()
                .body("{ \"destination\": \"Paris\", \"departure\": \"Copenhagen\", \"dateTime\": \"2019-12-02\" }")
                .contentType("application/json")
                .post("flights/search").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract()
                .body().jsonPath().getList("", FlightDTO.class);

        assertEquals(flightDTOS.get(0), flightDTO2);
    }
}