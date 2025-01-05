package testData;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class UniqueNameGenerator {

    private static UniqueNameGenerator instance;
    private Set<String> usedNames = new HashSet<>();
    private Faker faker = new Faker();

    private UniqueNameGenerator() {}

    public static UniqueNameGenerator getInstance() {
        if (instance == null) {
            instance = new UniqueNameGenerator();
        }
        return instance;
    }

    public String generateUniqueName() {
        String newName;
        do {
            newName = faker.name().firstName();
        } while (usedNames.contains(newName));

        usedNames.add(newName);
        return newName;

    }
}
