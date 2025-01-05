package baseTests;

import baseSutup.BaseSetup;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseSetup {


    @Test
    public void createUniqueUserTest() {

        createUser();

    }
}
