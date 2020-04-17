package com.company;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public int yearBirth;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getYearBirth() {
        return yearBirth;
    }


    public Contact(String firstName, String lastName, String phoneNumber, int yearBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.yearBirth = yearBirth;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearBirth=" + yearBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getYearBirth() == contact.getYearBirth() &&
                getFirstName().equals(contact.getFirstName()) &&
                getLastName().equals(contact.getLastName()) &&
                getPhoneNumber().equals(contact.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPhoneNumber(), getYearBirth());
    }
}
