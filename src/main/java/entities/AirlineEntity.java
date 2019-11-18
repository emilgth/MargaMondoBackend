package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "AirlineEntity.deleteAllRows", query = "DELETE from AirlineEntity ")
public class AirlineEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String AirlineName;

    public AirlineEntity() {
    }

    public AirlineEntity(String airlineName) {
        AirlineName = airlineName;
    }

    public String getAirlineName() {
        return AirlineName;
    }

    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }
}
