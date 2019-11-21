package facades;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//Uncomment the line below, to temporarily disable this test
//@Disabled
class ScraperFacadeTest {

    private static EntityManagerFactory emf;
    private static ScraperFacade scraperFacade;

    ScraperFacadeTest() {
    }

    @BeforeAll
    static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        scraperFacade = ScraperFacade.getScraperFacade(emf);
    }
}