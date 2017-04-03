package ua.dzms.users;

import java.time.LocalDate;
import java.util.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        new User(0, firstName, lastName, dateOfBirth);
    }

    public User(int id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
