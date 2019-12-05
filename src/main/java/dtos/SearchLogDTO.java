package dtos;

import entities.SearchLogEntity;

import java.util.Objects;

public class SearchLogDTO {
    private String destination;
    private String departure;
    private String dateTime;
    private String returnDate;

    public SearchLogDTO() {
    }

    public SearchLogDTO(SearchLogEntity data) {
        this.destination = data.getArrivalAirport();
        this.departure = data.getDepartureAirport();
        this.dateTime = data.getDepartureDate().toString();
        if (data.getReturnDate() == null) {
            this.returnDate = "0";
        } else {
            this.returnDate = data.getReturnDate().toString();
        }
    }

    public SearchLogDTO(String destination, String departure, String dateTime, String returnDate) {
        this.destination = destination;
        this.departure = departure;
        this.dateTime = dateTime;
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchLogDTO)) return false;
        SearchLogDTO that = (SearchLogDTO) o;
        return Objects.equals(destination, that.destination) &&
                Objects.equals(departure, that.departure) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, departure, dateTime, returnDate);
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
