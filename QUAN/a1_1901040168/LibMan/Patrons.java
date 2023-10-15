package LibMan;

import common.PatronType;

import java.util.Date;
import java.util.Objects;

public class Patrons {
    private String patronId;
    private String name;
    private Date dob;
    private String email;
    private String phoneNumber;
    private PatronType patronType;

    public Patrons(String patronId, String name, Date dob, String email, String phoneNumber, PatronType patronType) {
        this.patronId = patronId;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
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
        Patrons patrons = (Patrons) o;
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
}
