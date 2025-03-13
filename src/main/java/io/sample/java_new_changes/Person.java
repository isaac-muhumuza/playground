package io.sample.java_new_changes;

public record Person(String firstname, String lastname, int age, String phone) {
    public Person {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Valid human age cannot be " + age);
        }
    }
}
