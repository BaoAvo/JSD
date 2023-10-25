package a1_190140180;

import common.PatronType;

public class Patron {
    private String patronId;
    private String name;
    private String dob;
    private String email;
    private String phoneNumber;
    private PatronType patronType;

    private static int patronIndex = 0;

    public Patron(String name, String dob, String email, String phoneNumber, PatronType patronType) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        String patronID = generatePatronID();
        this.setPatronId(patronID);
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
        String patronObject = String.format("Patrons[patronId = %s,name = %s,dob = %s,email = %s,phoneNumber = %s,patronType = %s]", patronId, name, dob, email, phoneNumber, phoneNumber);
        return patronObject;
    }

    public String generatePatronID(){
        this.patronIndex++;
        String patronIdIncrease = String.format("P%03d", this.patronIndex);
        return patronIdIncrease;
    }
}
