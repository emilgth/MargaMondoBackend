package dtos;

import entities.BookingLogEntity;

import java.util.Objects;

public class BookingDTO {
    private Long id;
    private String departureAirport;
    private String arrivalAirport;
    private Long departureDate;
    private Long returnDate;
    private Double price;
    private Integer adults;
    private Integer children;
    private Long flightDuration;

    public BookingDTO(BookingLogEntity data) {
        this.id = data.getId();
        this.departureAirport = data.getDepartureAirport();
        this.arrivalAirport = data.getArrivalAirport();
        this.departureDate = data.getDepartureDate();
        this.returnDate = data.getReturnDate();
        this.price = data.getPrice();
        this.adults = data.getAdults();
        this.children = data.getChildren();
        this.flightDuration = data.getFlightDuration();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDTO)) return false;
        BookingDTO that = (BookingDTO) o;
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