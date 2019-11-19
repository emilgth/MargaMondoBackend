package facades;

import dtos.FlightDTO;

import java.util.List;

public interface IFlightFacade {

    List<FlightDTO> getAllFlights();
}
