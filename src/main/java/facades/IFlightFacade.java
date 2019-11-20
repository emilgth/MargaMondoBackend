package facades;

import dtos.FlightDTO;

import java.util.Date;
import java.util.List;

public interface IFlightFacade {

    List<FlightDTO> getAllFlights();

    String flightSearch(String dest, String dep, Date date);

}
