package dtos;

import java.util.Date;

public class FlightSearchDTO {
    private String destination;
    private String departure;
    private Date dateTime;

    public FlightSearchDTO(String destination, String departure, Date dateTime) {
        this.destination = destination;
        this.departure = departure;
        this.dateTime = dateTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
