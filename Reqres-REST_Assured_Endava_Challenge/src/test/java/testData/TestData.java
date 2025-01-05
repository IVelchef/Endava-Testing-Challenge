package testData;

import java.util.List;
import java.util.Map;

public class TestData {

    public  String randomName;
    public static String userName;
    public static String firstName;
    public static List<Map<String, Object>> userNamesPage1;
    public static List<Map<String, Object>> userNamesPage2;
    public static int userId;
    public static int invalidId = 23;
    public static int createdUniqueUserID;


    public static List<Map<String, Object>> getUserNamesPage1() {
        return userNamesPage1;
    }

    public static void setUserNamesPage1(List<Map<String, Object>> userNames) {
        userNamesPage1 = userNames;
    }

    public static List<Map<String, Object>> getUserNamesPage2() {
        return userNamesPage2;
    }

    public static void setUserNamesPage2(List<Map<String, Object>> userNames) {
        userNamesPage2 = userNames;
    }

    public TestData() {
        UniqueNameGenerator generator = UniqueNameGenerator.getInstance();
        this.randomName = generator.generateUniqueName();

    }
}
