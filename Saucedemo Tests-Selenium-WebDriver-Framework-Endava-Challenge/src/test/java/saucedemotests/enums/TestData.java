package saucedemotests.enums;

public enum TestData {

    PASSWORD("secret_sauce");

    TestData(String propName) {
        value = propName;
    }

    public String getValue() {
        return value;
    }

    final String value;
}
