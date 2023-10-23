package a1_1901040168;

import common.PatronType;

public class Patron {
    private String patronId;
    private String name;
    private String dob;
    private String email;
    private String phoneNumber;
    private PatronType patronType;

    private static int index = 0;

    public Patron(String name, String dob, String email, String phoneNumber, PatronType patronType) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        this.setPatronId(generatePatronID());
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
    public String toString() {
        return "Patrons[" +
                "patronId = '" + patronId + '\'' +
                ", name = '" + name + '\'' +
                ", dob = " + dob +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", patronType = " + patronType +
                ']';
    }

    //    Generate a unique ID for each Patron (e.g. P001, P002…)
    //    The Patron ID is generated automatically by the system using the formula: the letter P
    //    followed by a unique number, auto-incremented from 1. The number should be padded with
    //    zeros so that it contains at least 3 digits. For instance, the first patron will have the Patron ID
    //    of P001, the second patron will have P002, and so on. The 100th patron will have the ID of
    //    P100, zero padding is no longer required because the number already has 3 digits
    public String generatePatronID(){
        this.index++;
        return String.format("P%03d", this.index);
    }
}
