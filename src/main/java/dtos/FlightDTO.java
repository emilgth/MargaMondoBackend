package dtos;

import entities.FlightEntity;

import java.util.Date;

public class FlightDTO {
    private Date departureTime;
    private Date arrivalTime;
    private long flightDuration;
    private String departureAirportName;
    private String departureAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCode;
    private String airline;
    private String flightNumber;
    private String aircraftType;
    private double price;

    public FlightDTO() {
    }

    public FlightDTO(FlightEntity flightEntity) {
        this.departureTime = flightEntity.getDepartureTime();
        this.arrivalTime = flightEntity.getArrivalTime();
        this.flightDuration = flightEntity.getFlightDuration();
        this.departureAirportName = flightEntity.getDepartureLocation().getAirportName();
        this.departureAirportCode = flightEntity.getDepartureLocation().getAirportCode();
        this.arrivalAirportName = flightEntity.getArrivalLocation().getAirportName();
        this.arrivalAirportCode = flightEntity.getArrivalLocation().getAirportCode();
        this.airline = flightEntity.getAirline().getAirlineName();
        this.flightNumber = flightEntity.getFlightNumber();
        this.aircraftType = flightEntity.getAircraftType();
        this.price = flightEntity.getPrice();
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", flightDuration=" + flightDuration +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", arrivalAirportCode='" + arrivalAirportCode + '\'' +
                ", airline='" + airline + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", aircraftType='" + aircraftType + '\'' +
                ", price=" + price +
                '}';
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
