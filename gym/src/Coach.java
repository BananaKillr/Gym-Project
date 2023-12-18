import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coach extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private static final int MAX_CUSTOMERS = 10;
    private int maxWorkingHoursPerDay;
    private List<Customer> customers = new ArrayList<>();

    public Coach(int id, String name, char gender, String address, String phoneNumber, String email, int maxWorkingHoursPerDay, char[] password) {
        super(id, name, gender, address, phoneNumber, email, password);
        this.maxWorkingHoursPerDay = maxWorkingHoursPerDay;
    }

    public int getMaxWorkingHoursPerDay() {
        return maxWorkingHoursPerDay;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    // Additional functionalities
    public void addCustomer(Customer customer) {
        if (customers.size() < MAX_CUSTOMERS) {
            customers.add(customer);
        }
    }

    public String showCustomerList() {
        String result = "";
        result += ("List of Coach's Customers:");
        for (Customer customer : customers) {
            result += ("\nCustomer ID: " + customer.getId() +
                    ", Name: " + customer.getName() + ", Number: " + customer.getPhoneNumber());
        }
        return result;
    }

    public String getInBodyHistory(int customerId) {
        List<InBody> inBodyList = null;
        String result = "";

        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                inBodyList = customer.getInBodyInfo();
                break;
            }
        }

        if (inBodyList != null) {
            result += "InBody History for Customer ID " + customerId + ":\n";

            for (int i = 0; i < inBodyList.size(); i++) {
                InBody inBody = inBodyList.get(i);
                result += "Date: " + inBody.getDateOfInBody() + "\n";
                result += "Height: " + inBody.getHeight() + "\n";
                result += "Total Weight: " + inBody.getTotalWeight() + "\n";
                result += "Body Fat Mass: " + inBody.getBodyFatMass() + "\n";
                result += "Minerals: " + inBody.getMinerals() + "\n";
                result += "Total Body Water: " + inBody.getTotalBodyWater() + "\n";
                result += "Protein: " + inBody.getProtein() + "\n";
                result += "---------------------\n";
            }
        } else {
            result += "No InBody history found for Customer ID " + customerId;
        }

        return result;
    }

    public String getCustomerDetailsByName(String customerName) {
        String result = "Customers with name " + customerName;

        for (Customer customer : Gym.gym.customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                result += ("\nCustomer ID: " + customer.getId() + ", Name: " + customer.getName());
            }
        }
        return result;
    }

    public String showFemaleCustomers() {
        String result = "";
        result += ("List of Female Customers:");
        for (Customer customer : customers) {
            if (customer.getGender() == 'F') {
                result += ("\nCustomer ID: " + customer.getId() + ", Name: " + customer.getName());
            }
        }
        return result;
    }

    public String showMaleCustomers() {
        String result = "";
        result += ("List of Male Customers:");
        for (Customer customer : customers) {
            if (customer.getGender() == 'M') {
                result += ("\nCustomer ID: " + customer.getId() + ", Name: " + customer.getName());
            }
        }
        return result;
    }

}
