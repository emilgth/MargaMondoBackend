package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dtos.BookingLogDTO;
import dtos.FlightDTO;
import dtos.FlightSearchDTO;
import entities.BookingLogEntity;
import entities.SearchLogEntity;
import facades.FlightFacade;
import facades.ScraperFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("flights")
public class FlightResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            EMF_Creator.DbSelector.DEV,
            EMF_Creator.Strategy.CREATE);
    private static final FlightFacade FLIGHT_FACADE = FlightFacade.getFlightFacade(EMF);
    private static final ScraperFacade SCRAPER_FACADE = ScraperFacade.getScraperFacade(EMF);
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

    @Path("global")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getGlobalFlights() throws ExecutionException, InterruptedException {
        StringBuilder jsonStr = new StringBuilder();
        for (String json : SCRAPER_FACADE.getAllApiData()) {
            jsonStr.append(json);
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement data = jsonParser.parse(jsonStr.toString());
        return GSON.toJson(data);
    }

    @Path("log")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getLog() {
        return null;
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

    @Path("search/log/single")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void searchLogSingle(String data) {
        SearchLogEntity searchLogEntity = GSON.fromJson(data, SearchLogEntity.class);

    }

    @Path("search/log/return")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void searchLogReturn(String data) {

    }

    @Path("booking/log")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String bookingLog(String data) {
        Type listType = new TypeToken<ArrayList<BookingLogDTO>>(){}.getType();
        List<BookingLogDTO> tripData = GSON.fromJson(data, listType);
        FLIGHT_FACADE.bookingLogger(new BookingLogEntity(tripData));
        return "Booking logged " + new Date(System.currentTimeMillis()).toString();
    }
}
