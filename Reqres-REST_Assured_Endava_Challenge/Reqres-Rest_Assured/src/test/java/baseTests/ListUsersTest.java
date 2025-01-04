package baseTests;

import baseSutup.BaseSetup;
import org.testng.annotations.Test;

public class ListUsersTest extends BaseSetup {


    @Test
    public void testGetAllUsers() {

        getListUsersPage1();
        getListUsersPage2();
        combineAndSortUsers();

    }
}
