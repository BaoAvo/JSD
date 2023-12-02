package model;

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

    public String generatePatronID(){
        this.index++;
        return String.format("P%03d", this.index);
    }
}
