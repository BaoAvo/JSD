package LibMan;

import common.PatronType;

import java.util.Date;
import java.util.Objects;

public class Patron {
    private String patronId = 1;
    private String name;
    private Date dob;
    private String email;
    private String phoneNumber;
    private PatronType patronType;

    public Patron(String name, Date dob, String email, String phoneNumber, PatronType patronType) {
        this.patronId = generatePatronID();
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patrons = (Patron) o;
        return Objects.equals(patronId, patrons.patronId) && Objects.equals(name, patrons.name) && Objects.equals(dob, patrons.dob) && Objects.equals(email, patrons.email) && Objects.equals(phoneNumber, patrons.phoneNumber) && patronType == patrons.patronType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patronId, name, dob, email, phoneNumber, patronType);
    }

    @Override
    public String toString() {
        return "Patrons{" +
                "patronId='" + patronId + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", patronType=" + patronType +
                '}';
    }

    //    Generate a unique ID for each Patron (e.g. P001, P002…)
    //    The Patron ID is generated automatically by the system using the formula: the letter P
    //    followed by a unique number, auto-incremented from 1. The number should be padded with
    //    zeros so that it contains at least 3 digits. For instance, the first patron will have the Patron ID
    //    of P001, the second patron will have P002, and so on. The 100th patron will have the ID of
    //    P100, zero padding is no longer required because the number already has 3 digits
    public void generatePatronID(){
        this.getPatronId++;
        return String.format("P%03d", this.getPatronId); 
    }
}
