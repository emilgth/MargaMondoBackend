package entities;

import dtos.BookingLogDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "booking_log")
public class BookingLogEntity implements Serializable {

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
    @Column(name = "price")
    private Double price;
    @Column(name = "adults")
    private Integer adults;
    @Column(name = "children")
    private Integer children;
    @Column(name = "flight_duration")
    private Long flightDuration;

    public BookingLogEntity() {
    }

    public BookingLogEntity(List<BookingLogDTO> data) {
        this.departureAirport = data.get(0).getDepartureAirportCode();
        this.arrivalAirport = data.get(0).getArrivalAirportCode();
        this.departureDate = data.get(0).getDepartureTimeMS();
        this.returnDate = arraySizeCheck(data);
        this.price = calcPrice(data);
        this.adults = data.get(0).getNumberOfAdults();
        this.children = data.get(0).getNumberOfChildren();
        this.flightDuration = data.get(0).getFlightDurationMS();
    }

    private static Long arraySizeCheck(List<BookingLogDTO> data) {
        if (data.size() > 1) {
            return data.get(1).getDepartureTimeMS();
        } else {
            return null;
        }
    }

    private static double calcPrice(List<BookingLogDTO> data) {
        if (data.size() > 1) {
            double outTotal = data.get(0).getPrice() * data.get(0).getNumberOfAdults() + (data.get(0).getPrice() / 2) * data.get(0).getNumberOfChildren();
            double returnTotal = data.get(1).getPrice() * data.get(1).getNumberOfAdults() + (data.get(1).getPrice() / 2) * data.get(1).getNumberOfChildren();
            return outTotal + returnTotal;
        } else {
            return data.get(0).getPrice() * data.get(0).getNumberOfAdults() + (data.get(0).getPrice() / 2) * data.get(0).getNumberOfChildren();
        }
    }

    public BookingLogEntity(String departureAirport, String arrivalAirport, Long departureDate, Long returnDate, Double price, Integer adults, Integer children, Long flightDuration) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.adults = adults;
        this.children = children;
        this.flightDuration = flightDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingLogEntity)) return false;
        BookingLogEntity that = (BookingLogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(departureAirport, that.departureAirport) &&
                Objects.equals(arrivalAirport, that.arrivalAirport) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                Objects.equals(price, that.price) &&
                Objects.equals(adults, that.adults) &&
                Objects.equals(children, that.children) &&
                Objects.equals(flightDuration, that.flightDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureAirport, arrivalAirport, departureDate, returnDate, price, adults, children, flightDuration);
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Long departureDate) {
        this.departureDate = departureDate;
    }

    public Long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Long returnDate) {
        this.returnDate = returnDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Long getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(Long flightDuration) {
        this.flightDuration = flightDuration;
    }
}
