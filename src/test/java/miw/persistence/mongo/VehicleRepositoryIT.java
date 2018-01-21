package miw.persistence.mongo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class VehicleRepositoryIT {

    @Autowired
    private VehicleRepository vehicleRepository;

    Vehicle vehicle = new Vehicle("1234 zzz");

    @Before
    public void populate() {
        this.vehicle = new Vehicle("1234 zzz");
        this.vehicleRepository.save(vehicle);
    }

    @Test
    public void testFindOne() {
        assertEquals(this.vehicle.getId(), vehicleRepository.findByregister("1234 zzz").getId());
    }

    @After
    public void deleteAll() {
        vehicleRepository.delete(vehicle);
    }

}
