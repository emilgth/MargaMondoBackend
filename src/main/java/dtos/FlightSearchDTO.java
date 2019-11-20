package dtos;

public class FlightSearchDTO {
    private String destination;
    private String arrival;
    private String dateTime;

    public FlightSearchDTO(String destination, String arrival, String dateTime) {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
