package baseTests;

import baseSutup.BaseSetup;
import org.testng.annotations.Test;

public class NonExistentUserIDTest extends BaseSetup {


    @Test
    public void invalidUserIdDetailsTest(){

        invalidUserIdDetails();

    }
}
