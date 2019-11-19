package facades;

import dtos.FlightDTO;
import entities.FlightEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
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
}
