package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FlightDTO;
import dtos.FlightSearchDTO;
import facades.FlightFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("flights")
public class FlightResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final FlightFacade FLIGHT_FACADE = FlightFacade.getFlightFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllFlights() {
        List<FlightDTO> flights = FLIGHT_FACADE.getAllFlights();
        return GSON.toJson(flights);
    }

    @Path("search")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String flightSearch(String flightSearch) {
        FlightSearchDTO flightSearchDTO = GSON.fromJson(flightSearch, FlightSearchDTO.class);
        String dest = flightSearchDTO.getDestination();
        String dep = flightSearchDTO.getDeparture();
        Date date = flightSearchDTO.getDateTime();
        return GSON.toJson(FLIGHT_FACADE.flightSearch(dest, dep, date));
    }

}
