package dtos;

import java.util.Objects;

public class BookingLogDTO {
    private Long id;
    private String departureAirportName;
    private String departureAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCode;
    private String departureTime;
    private long departureTimeMS;
    private String arrivalTime;
    private long arrivalTimeMS;
    private String flightDuration;
    private long flightDurationMS;
    private double price;
    private String airline;
    private String flightClass;
    private int numberOfAdults;
    private int numberOfChildren;

    public BookingLogDTO() {
    }

    public BookingLogDTO(Long id, String departureAirportName, String departureAirportCode, String arrivalAirportName, String arrivalAirportCode, String departureTime, long departureTimeMS, String arrivalTime, long arrivalTimeMS, String flightDuration, long flightDurationMS, double price, String airline, String flightClass, int numberOfAdults, int numberOfChildren) {
        this.id = id;
        this.departureAirportName = departureAirportName;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureTime = departureTime;
        this.departureTimeMS = departureTimeMS;
        this.arrivalTime = arrivalTime;
        this.arrivalTimeMS = arrivalTimeMS;
        this.flightDuration = flightDuration;
        this.flightDurationMS = flightDurationMS;
        this.price = price;
        this.airline = airline;
        this.flightClass = flightClass;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingLogDTO)) return false;
        BookingLogDTO that = (BookingLogDTO) o;
        return departureTimeMS == that.departureTimeMS &&
                arrivalTimeMS == that.arrivalTimeMS &&
                flightDurationMS == that.flightDurationMS &&
                Double.compare(that.price, price) == 0 &&
                numberOfAdults == that.numberOfAdults &&
                numberOfChildren == that.numberOfChildren &&
                Objects.equals(id, that.id) &&
                Objects.equals(departureAirportName, that.departureAirportName) &&
                Objects.equals(departureAirportCode, that.departureAirportCode) &&
                Objects.equals(arrivalAirportName, that.arrivalAirportName) &&
                Objects.equals(arrivalAirportCode, that.arrivalAirportCode) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(flightDuration, that.flightDuration) &&
                Objects.equals(airline, that.airline) &&
                Objects.equals(flightClass, that.flightClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureAirportName, departureAirportCode, arrivalAirportName, arrivalAirportCode, departureTime, departureTimeMS, arrivalTime, arrivalTimeMS, flightDuration, flightDurationMS, price, airline, flightClass, numberOfAdults, numberOfChildren);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public long getDepartureTimeMS() {
        return departureTimeMS;
    }

    public void setDepartureTimeMS(long departureTimeMS) {
        this.departureTimeMS = departureTimeMS;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getArrivalTimeMS() {
        return arrivalTimeMS;
    }

    public void setArrivalTimeMS(long arrivalTimeMS) {
        this.arrivalTimeMS = arrivalTimeMS;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    public long getFlightDurationMS() {
        return flightDurationMS;
    }

    public void setFlightDurationMS(long flightDurationMS) {
        this.flightDurationMS = flightDurationMS;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
