package entities;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


@Entity
@NamedQueries(value = {
        @NamedQuery(name = "FlightEntity.getAllRows", query = "SELECT Flight FROM FlightEntity Flight"),
        @NamedQuery(name = "FlightEntity.deleteAllRows", query = "DELETE FROM FlightEntity")
})
public class FlightEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AirportEntity departureLocation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AirportEntity arrivalLocation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    private long flightDuration;
    private double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AirlineEntity airline;
    private String flightNumber;
    private String aircraftType;

    public FlightEntity() {
    }

    public AirportEntity getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(AirportEntity departureLocation) {
        this.departureLocation = departureLocation;
    }

    public AirportEntity getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(AirportEntity arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(long flightDuration) {
        this.flightDuration = flightDuration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AirlineEntity getAirline() {
        return airline;
    }

    public void setAirline(AirlineEntity airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
