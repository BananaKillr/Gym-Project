import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
//gym and admin
public class Gym implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    static Gym gym;
    // Attributes
    private String name;
    private String address;
    private String phoneNumber;
    protected List<Equipment> sportsEquipments = new ArrayList<>();
    protected List<Coach> coaches = new ArrayList<>(); // Initialize the list
    protected List<Customer> customers = new ArrayList<>();
    protected List<Subscription> subscriptions = new ArrayList<>();
    private static final String ADMIN_NAME = "admin";
    private static final char[] ADMIN_PASSWORD = {'a','d','m','i','n'};
    Person currentPerson;

    public Gym(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public static Gym getGym() {//
        if (Gym.gym == null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
                gym = (Gym) ois.readObject();
                System.out.println("Deserialization complete.");
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        return Gym.gym;
    }

    public static void setGym(Gym gym) {//TODO remove for final production
        Gym.gym = gym;
    }

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

    public void displayCustomersOfCoach(int coachId) {
        try {
            System.out.println("Customers of Coach with ID " + coachId + ":");
            boolean coachFound = false;
            for (Coach coach : coaches) {
                if (coach.getId() == coachId) {
                    coachFound = true;
                    for (Customer customer : customers) {
                        if (customer.getSubscription().getAssignedCoachId() == coachId) {
                            System.out.println("Customer ID: " + customer.getId());
                            System.out.println("Customer Name: " + customer.getName());
                            System.out.println("Customer Phone Number: " + customer.getPhoneNumber());
                            System.out.println(); // Add a line break for better readability
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public void addCoach(Coach newCoach) {
        coaches.add(newCoach);
    }

    public void addEquipment(Equipment newEquipment) {
        sportsEquipments.add(newEquipment);
    }

    public void deleteCustomer(int customerID) {
        try {
            int index = -1;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId() == customerID) {
                    index = i;
                }
            }
            if (index != -1) {
                customers.remove(index);
                System.out.println("Customer with ID " + customerID + " deleted successfully.");
            } else {
                throw new IllegalArgumentException("Customer not found with ID: " + customerID);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteCoach(int coachID) {
        try {
            int index = -1;
            for (int i = 0; i < coaches.size(); i++) {
                if (coaches.get(i).getId() == coachID) {
                    index = i;
                }
            }
            if (index != -1) {
                coaches.remove(index);
                System.out.println("Coach with ID " + coachID + " deleted successfully.");
            } else {
                throw new IllegalArgumentException("Coach not found with ID: " + coachID);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteEquipment(int equipmentCode) {
        try {
            int index = -1;
            for (int i = 0; i < sportsEquipments.size(); i++) {
                if (sportsEquipments.get(i).getEquipmentCode() == equipmentCode) {
                    index = i;
                }
            }
            if (index != -1) {
                sportsEquipments.remove(index);
                System.out.println("Equipment with Code " + equipmentCode + " deleted successfully.");
            } else {
                throw new IllegalArgumentException("Equipment not found with Code: " + equipmentCode);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public double displayIncomeInMonth(int month, int year) {
        try {
            System.out.println("GYM Income for " + month + " " + year + ":");

            double totalIncome = 0.0;

            for (Subscription subscription : this.subscriptions) {
                LocalDate startDate = subscription.getMembershipPlan().getStartDate();
                int monthValue = startDate.getMonthValue();
                int yearValue = startDate.getYear();
                // Check if the subscription falls within the specified month and year
                if (monthValue == month && yearValue == year) {
                    // Calculate the income for the subscription
                    double subscriptionIncome = subscription.getMembershipPlan().getPrice();
                    totalIncome += subscriptionIncome;
                }
            }
            System.out.println("Total Income: " + totalIncome);
            return totalIncome;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }

    public String SortCoachesByCustomers(List<Coach> coaches) {
        List<Coach> sortedList = coaches;
        sortedList.sort(new SortByCustomers());
        String returnString = "";

        for (Coach coach : sortedList) {
            String customers = "";
            try {
                customers = Integer.toString(coach.getCustomers().size());
            } catch (NullPointerException e) {
                customers = "0";
            }
            returnString += "Name: " + coach.getName() + " ID: " + coach.getId() + " Customers: " + customers + "\n";
        }
        return returnString;
    }

    public void displayCustomersSubscribedInMonth(int month, int year) {
        try {
            System.out.println("Customers Subscribed in " + month + "/" + year + ":");

            for (Customer customer : customers) {
                MembershipPlan customerMembership = customer.getSubscription().getMembershipPlan();
                if (customerMembership != null) {
                    LocalDate subscriptionStartDate = customerMembership.getStartDate();
                    int subscriptionMonth = subscriptionStartDate.getMonthValue();
                    int subscriptionYear = subscriptionStartDate.getYear();

                    // Check if the subscription starts in the specified month and year
                    if (subscriptionMonth == month && subscriptionYear == year) {
                        System.out.println("Customer ID: " + customer.getId());
                        System.out.println("Customer Name: " + customer.getName());
                        System.out.println("Customer Phone Number: " + customer.getPhoneNumber());
                        System.out.println(); // Add a line break for better readability
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(gym);
            System.out.println("Serialization complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean AdminLogin(String username, char[] password){
        if (username.equals(Gym.ADMIN_NAME) && Arrays.equals(password, Gym.ADMIN_PASSWORD)) return true;
        return false;
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
}
class SortByCustomers implements Comparator<Coach>  {
    @Override
    public int compare(Coach o1, Coach o2) {
        int o1Size, o2Size;

        if (o1.getCustomers() == null) {
            o1Size = 0;
        } else {
            o1Size = o1.getCustomers().size();
        }

        if (o2.getCustomers() == null) {
            o2Size = 0;
        } else {
            o2Size = o2.getCustomers().size();
        }

        if (o1Size > o2Size) return 1;
        else if (o1Size < o2Size) return -1;
        else return 0;
    }
}