package entities;

import dtos.SearchLogDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "SearchLogEntity.getAllRows", query = "SELECT Log FROM SearchLogEntity Log"),
        @NamedQuery(name = "SearchLogEntity.deleteAllRows", query = "DELETE FROM SearchLogEntity")
})
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

    public SearchLogEntity(SearchLogDTO data) {
        this.departureAirport = data.getDeparture();
        this.arrivalAirport = data.getDestination();
        this.departureDate = dateParser(data.getDateTime());
        this.returnDate = dateParser(data.getReturnDate());
    }

    public SearchLogEntity(String departureAirport, String arrivalAirport, Long departureDate) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = 0L;
    }

    public SearchLogEntity(String departureAirport, String arrivalAirport, Long departureDate, Long returnDate) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    private static long dateParser(String input) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        long result = 0L;
        if (input == null) {
            return result;
        }
        try {
            result = parser.parse(input).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
