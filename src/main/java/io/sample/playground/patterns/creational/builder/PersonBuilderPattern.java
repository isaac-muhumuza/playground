package io.sample.playground.patterns.creational.builder;

public class PersonBuilderPattern {

    private String firstname;
    private String surname;
    private String address;
    private int age;

    public PersonBuilderPattern(PersonBuilderPatternBuilder builder) {
        this.firstname = builder.firstname;
        this.surname = builder.surname;
        this.address = builder.address;
        this.age = builder.age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    static class PersonBuilderPatternBuilder {
        private String firstname;
        private String surname;
        private String address;
        private int age;

        public PersonBuilderPatternBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public PersonBuilderPatternBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilderPatternBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilderPatternBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilderPattern build() {
            return new PersonBuilderPattern(this);
        }
    }
}
