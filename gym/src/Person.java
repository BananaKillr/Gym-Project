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
    private char[] password;
    protected char[] getPassword() {
        return password;
    }

    public Person(int id, String name, char gender, String address,
                  String phoneNumber, String email, char[] password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public char getGender() {
        return gender;
    }


    public String getAddress() {return address;}


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getEmail() {
        return email;
    }

}
