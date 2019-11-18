package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQuery(name = "FlightEntity.deleteAllRows", query = "DELETE from FlightEntity")
public class FlightEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AirportEntity departureLocation;
    @ManyToOne
    private AirportEntity arrivalLocation;
    @Temporal(TemporalType.DATE)
    private Date departureTime;
    @Temporal(TemporalType.DATE)
    private Date arrivalTime;
    private long flightDuration;
    private double price;
    private String airline;
    private String flightNumber;
    private String aircraftType;

    public FlightEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
    }
}
