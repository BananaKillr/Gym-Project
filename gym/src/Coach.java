import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coach extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private static final int MAX_CUSTOMERS = 10;
    private int maxWorkingHoursPerDay;
    private List<Customer> customers;

    public Coach(int id, String name, char gender, String address, String phoneNumber, String email, String password, int maxWorkingHoursPerDay) {
        super(id, name, gender, address, phoneNumber, email, password);
    }

    public int getMaxWorkingHoursPerDay() {
        return maxWorkingHoursPerDay;
    }

    public void setMaxWorkingHoursPerDay(int maxWorkingHoursPerDay) {
        this.maxWorkingHoursPerDay = maxWorkingHoursPerDay;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    // Additional functionalities
    public void addCustomer(Customer customer) {
        if (customers.size() < MAX_CUSTOMERS) {
            customers.add(customer);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Cannot add more customers. Maximum limit reached.");
        }
    }

    public void showCustomerList() {
        System.out.println("List of Coach's Customers:");
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getId() +
                    ", Name: " + customer.getName());
        }
    }

    public void getInBodyHistory(int customerId) {
        List<InBody> inBodyList = null;

        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                inBodyList = customer.getInBodyInfo();
                break;
            }
        }

        if (inBodyList != null) {
            System.out.println("InBody History for Customer ID " + customerId + ":");
            for (int i = 0; i < inBodyList.size(); i++) {
                InBody inBody = inBodyList.get(i);
                System.out.println("Date: " + inBody.getDateOfInBody());
                System.out.println("Height: " + inBody.getHeight());
                System.out.println("Total Weight: " + inBody.getTotalWeight());
                System.out.println("Body Fat Mass: " + inBody.getBodyFatMass());
                System.out.println("Minerals: " + inBody.getMinerals());
                System.out.println("Total Body Water: " + inBody.getTotalBodyWater());
                System.out.println("Protein: " + inBody.getProtein());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No InBody history found for Customer ID " + customerId);
        }
    }

    public Customer getCustomerDetailsByName(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                return customer;
            }
        }
        return null;
    }

    public void showFemaleCustomers() {
        System.out.println("List of Female Customers:");
        for (Customer customer : customers) {
            if (customer.getGender() == 'F') {
                System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName());
            }
        }
    }

    public void showMaleCustomers() {
        System.out.println("List of Male Customers:");
        for (Customer customer : customers) {
            if (customer.getGender() == 'M') {
                System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName());
            }
        }
    }



}
