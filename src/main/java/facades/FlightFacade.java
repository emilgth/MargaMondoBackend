package facades;

import dtos.FlightDTO;
import entities.FlightEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightFacade implements IFlightFacade {

    private static EntityManagerFactory emf;
    private static FlightFacade instance;

    private FlightFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static FlightFacade getFlightFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FlightFacade();
        }
        return instance;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        EntityManager em = emf.createEntityManager();
        try {
            List<FlightDTO> flightDTOS = new ArrayList<>();
            List<FlightEntity> flightEntities = em.createNamedQuery("FlightEntity.getAllRows", FlightEntity.class).getResultList();
            flightEntities.forEach(flightEntity -> flightDTOS.add(new FlightDTO(flightEntity)));
            return flightDTOS;
        } finally {
            em.close();
        }
    }

    public List<FlightDTO> flightSearch(String dest, String dep, Date date) {
        EntityManager em = emf.createEntityManager();
        Date nextDay = new Date(date.getTime() + 86400000);
        try {
            List<FlightDTO> flightDTOS = new ArrayList<>();
            List<FlightEntity> flightEntities = em.createQuery("select f from FlightEntity f where f.departureLocation.airportName = :dep and  f.arrivalLocation.airportName = :arr and f.departureTime >= :date and f.departureTime < :nextDay", FlightEntity.class)
                    .setParameter("dep", dep)
                    .setParameter("arr", dest)
                    .setParameter("date", date)
                    .setParameter("nextDay", nextDay) // sets the next day, so all the flights of the current day return
                    .getResultList();
            flightEntities.forEach(f -> flightDTOS.add(new FlightDTO(f)));
            return flightDTOS;
        } finally {
            em.close();
        }
    }
}
