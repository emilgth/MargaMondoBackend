package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "search_log")
public class SearchLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "departure_airport")
    private String departureAirport;
    @Column(name = "arrival_airport")
    private String arrivalAirport;
    @Column(name = "departure_date")
    private Long departureDate;
    @Column(name = "return_date")
    private Long returnDate;

    public SearchLogEntity() {
    }

    public SearchLogEntity(String departureAirport, String arrivalAirport, Long departureDate) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = null;
    }

    public SearchLogEntity(String departureAirport, String arrivalAirport, Long departureDate, Long returnDate) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLogEntity)) return false;
        SearchLogEntity that = (SearchLogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(departureAirport, that.departureAirport) &&
                Objects.equals(arrivalAirport, that.arrivalAirport) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureAirport, arrivalAirport, departureDate, returnDate);
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureLocation) {
        this.departureAirport = departureLocation;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalLocation) {
        this.arrivalAirport = arrivalLocation;
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Long departureTime) {
        this.departureDate = departureTime;
    }

    public Long getArrivalTime() {
        return returnDate;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.returnDate = arrivalTime;
    }
}
