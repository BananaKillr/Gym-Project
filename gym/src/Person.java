import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
    // Attributes
    private int id;
    private String name;
    private char gender;
    private String address;
    private String phoneNumber;

    private String email;
    private String password;

    public Person(int id, String name, char gender, String address,
                  String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}