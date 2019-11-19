package entities;

import javax.persistence.*;
import java.io.Serializable;
//karl check
@Entity
@NamedQuery(name = "AirlineEntity.deleteAllRows", query = "DELETE from AirlineEntity ")
@Table(name = "airlineentity")
public class AirlineEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "airlinename")
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
