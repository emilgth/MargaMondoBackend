package dtos;

import java.util.Date;

public class FlightSearchDTO {
    private String destination;
    private String arrival;
    private Date dateTime;

    public FlightSearchDTO(String destination, String arrival, Date dateTime) {
        this.destination = destination;
        this.arrival = arrival;
        this.dateTime = dateTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
