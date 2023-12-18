import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
//gym and admin
public class Gym implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // Attributes
    private String name;
    private String address;
    private String phoneNumber;
    protected List<Equipment> sportsEquipments = new ArrayList<>();
    protected List<Coach> coaches = new ArrayList<>();
    protected List<Customer> customers = new ArrayList<>();
    protected List<Subscription> subscriptions = new ArrayList<>();
    private final String ADMIN_NAME = "admin";
    private final char[] ADMIN_PASSWORD = {'a','d','m','i','n'};
    Person currentPerson; // Person currently logged in
    public static Gym gym; // Gym that is currently loaded

    public Gym(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static void setGym(Gym gym){Gym.gym = gym;}

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Equipment> getSportsEquipments() {
        return this.sportsEquipments;
    }

    public void setSportsEquipments(List<Equipment> sportsEquipments) {
        this.sportsEquipments = sportsEquipments;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Subscription> getSubscriptions() {
        return this.subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public static void saveData(String fileName) { //Saves data to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(gym);
            System.out.println("Data saved");
        } catch (Exception e) {
            System.out.println("Saving error");;
        }
    }

    public static void saveData() { //Saves data to file called data.ser
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(gym);
            System.out.println("Data saved");
        } catch (Exception e) {
            System.out.println("Saving error");;
        }
    }

    public static Gym getDataFromFile(String fileName){ //puts data in the static Gym object, then returns the handle
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Gym.gym = (Gym) ois.readObject();
            System.out.println("Data read successfully");
        } catch (Exception e) {
            System.out.println("Read error");
        }
        return gym;
    }

    public static Gym getDataFromFile(){
        if (Gym.gym == null) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            Gym.gym = (Gym) ois.readObject();
            System.out.println("Data read successfully");
        } catch (Exception e) {
            System.out.println("Read error");
        }
        }
        return Gym.gym;
    }

    public boolean AdminLogin(String username, char[] password){
        return username.equals(this.ADMIN_NAME) && Arrays.equals(password, this.ADMIN_PASSWORD);
    }

    public boolean CustomerLogin(String email, char[] password){
        for (Customer customer : customers){
            String customerEmail = customer.getEmail();
            char[] customerPassword = customer.getPassword();
            if (customerEmail.equals(email) && Arrays.equals(customerPassword, password)){
                currentPerson = customer;
                return true;
            }
        }
        return false;
    }

    public boolean CoachLogin(String email, char[] password){
        for (Coach coach : coaches){
            if (coach.getEmail().equals(email) && Arrays.equals(coach.getPassword(), password)){
                currentPerson = coach;
                return true;
            }
        }
        return false;
    }

    public String getEquipmentList(){
        String result = "";
        for (Equipment equipment : sportsEquipments){
            result += "Name: " + equipment.getEquipmentName() + "\t Amount: " + equipment.getQuantity() + "\n";
        }
        return result;
    }

}
