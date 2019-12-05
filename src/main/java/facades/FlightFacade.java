package facades;

import dtos.BookingDTO;
import dtos.FlightDTO;
import dtos.SearchLogDTO;
import entities.BookingLogEntity;
import entities.FlightEntity;
import entities.SearchLogEntity;

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

    @Override
    public void searchLogger(SearchLogEntity data) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void bookingLogger(BookingLogEntity data) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<SearchLogDTO> getAllSearchLogs() {
        EntityManager em = emf.createEntityManager();

        try {
            List<SearchLogDTO> searchLogDTOS = new ArrayList<>();
            List<SearchLogEntity> searchLogEntities = em.createNamedQuery("SearchLogEntity.getAllRows", SearchLogEntity.class).getResultList();
            searchLogEntities.forEach(searchLogEntity -> searchLogDTOS.add(new SearchLogDTO(searchLogEntity)));
            return searchLogDTOS;
        } finally {
            em.close();
        }
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        EntityManager em = emf.createEntityManager();

        try {
            List<BookingDTO> bookingDTOS = new ArrayList<>();
            List<BookingLogEntity> bookingLogEntities = em.createNamedQuery("BookingLogEntity.getAllRows", BookingLogEntity.class).getResultList();
            bookingLogEntities.forEach(bookingLogEntity -> bookingDTOS.add(new BookingDTO(bookingLogEntity)));
            return bookingDTOS;
        } finally {
            em.close();
        }
    }
}
