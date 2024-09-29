import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import ru.kholmogorova.Kotiki.Controller.Application;
import ru.kholmogorova.Kotiki.Controller.Owner;
import ru.kholmogorova.Kotiki.DAO.EntityManagerFactoryConfig;

import java.util.List;

public class KotikiTest {
    private static EntityManagerFactory emf;
    private final Application application = new Application();

    @BeforeAll
    public static void InitTest() {
        emf = EntityManagerFactoryConfig.getEntityManagerFactory();
    }

//    @Test
//    public void AddOwnerTest() {
//        Owner owner1 = application.CreateOwner("Alyona1", "2004-05-05");
//        Owner owner2 = application.CreateOwner("Alyona2", "2004-05-05");
//        List<Owner> owners = application.GetAllOwners();
//        Assertions.assertEquals(owners.size(), 2);
//        Assertions.assertEquals(owners.get(0).getName(), "Alyona1");
//        Assertions.assertEquals(owners.get(1).getName(), "Alyona2");
//    }

}
