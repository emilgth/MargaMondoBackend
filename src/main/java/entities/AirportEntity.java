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
    private String AirportName;

    public AirportEntity() {
    }

    public AirportEntity(String airportName) {
        AirportName = airportName;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }
}
