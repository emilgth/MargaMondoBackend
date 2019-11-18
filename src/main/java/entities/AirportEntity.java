package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "AirportEntity.deleteAllRows", query = "DELETE from AirportEntity ")
public class AirportEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airportName;
    private String airportCode;

    public AirportEntity() {
    }

    public AirportEntity(String airportName, String airportCode) {
        this.airportName = airportName;
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
