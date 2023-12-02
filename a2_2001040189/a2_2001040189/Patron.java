public class Patron {
    int dbId;
    String id;
    String name;
    String dob;
    String email;
    String phoneNumber;
    PatronType patronType;
    static int patronCountIndex = 1;

    public Patron(String name, String dob, String email, String phoneNumber, PatronType patronType) {
        this.id = generatePatronID();
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        this.dbId = 0;
    }

    public Patron() {}

    private String generatePatronID() {
        String id = String.format("P%03d", patronCountIndex);
        patronCountIndex++;
        return id;
    }

    public String getId() {
        return id;
    }

    public String getDbId() {
        return String.valueOf(this.dbId);
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    public static int getPatronCountIndex() {
        return patronCountIndex;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
}
