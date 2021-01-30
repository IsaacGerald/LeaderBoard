package com.example.volley.models;

public class Entries {
    private String email;
    private String firstName;
    private String lastName;
    private String link;

    public Entries() {

    }

    public Entries(String firstName, String lastName, String email, String link) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.link = link;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLink() {
        return link;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Item{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
