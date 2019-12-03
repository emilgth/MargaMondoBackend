package facades;

import dtos.BookingLogDTO;
import dtos.FlightDTO;
import dtos.SearchLogDTO;
import entities.BookingLogEntity;
import entities.SearchLogEntity;

import java.util.Date;
import java.util.List;

public interface IFlightFacade {

    List<FlightDTO> getAllFlights();

    List<FlightDTO> flightSearch(String dest, String dep, Date date);

    void searchLogger(SearchLogEntity data);

    void bookingLogger(BookingLogEntity data);

}
