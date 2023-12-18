import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Admin {
    private Admin(){} //Can't make object of admin. Only contains static methods
    public static String addCustomer(Customer newCustomer) {
        Coach customersCoach = null;
        for (Coach coach : Gym.gym.coaches){
            if (coach.getId() == newCustomer.getCoachID()){
                customersCoach = coach;
                if (coach.getCustomers().size() < 10) break;
                else return "Coach List Full";
            }
        }
        if (customersCoach == null) return "Coach Not Found";
        customersCoach.addCustomer(newCustomer);
        Gym.gym.customers.add(newCustomer);
        Gym.gym.subscriptions.add(newCustomer.getSubscription());
        return "Customer Added Successfully";
    }

    public static void addCoach(Coach newCoach) {
        Gym.gym.coaches.add(newCoach);
    }

    public static void addEquipment(Equipment newEquipment) {
        Gym.gym.sportsEquipments.add(newEquipment);
    }

    public static String deleteCustomer(int customerID) {
        Customer customerToBeDeleted = null;
        Coach coachWithCustomer = null;
        for (Customer customer : Gym.gym.customers){
            if (customer.getId() == customerID){
                customerToBeDeleted = customer;
                break;
            }
        }

        for (Coach coach : Gym.gym.coaches){
            if (coach.getId() == customerToBeDeleted.getCoachID()){
                coachWithCustomer = coach;
                break;
            }
        }

        Gym.gym.customers.remove(customerToBeDeleted);
        coachWithCustomer.getCustomers().remove(customerToBeDeleted);
        return "Customer Deleted Successfully";
    }

    public static String deleteCoach(int coachID) {
        for (Coach coach : Gym.gym.coaches){
            if (coach.getId() == coachID){
                if (!coach.getCustomers().isEmpty()) return "Coach Has Customers. Not Allowed To Delete";
                Gym.gym.coaches.remove(coach);
                return "Coach Removed Successfully";
            }
        }
        return "Coach Not Found";
    }

    public static void deleteEquipment(int equipmentCode) {
        try {
            int index = -1;
            for (int i = 0; i < Gym.gym.sportsEquipments.size(); i++) {
                if (Gym.gym.sportsEquipments.get(i).getEquipmentCode() == equipmentCode) {
                    index = i;
                }
            }
            if (index != -1) {
                Gym.gym.sportsEquipments.remove(index);
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

    public static String editCustomerDetails(Customer newCustomer){//Gets passed to it a customer with the same subscription and ID
        Customer customerToBeEdited = null;
        for (Customer customer : Gym.gym.customers){
            if (customer.getId() == newCustomer.getId()) {
                customerToBeEdited = customer;
                break;
            }
        }
        if (customerToBeEdited == null) return "Customer Not Found";
        newCustomer.setSubscription(customerToBeEdited.getSubscription());
        newCustomer.setOldSubscription(customerToBeEdited.getOldSubscription());
        newCustomer.setInBodyInfo(customerToBeEdited.getInBodyInfo());
        Gym.gym.customers.set(Gym.gym.customers.indexOf(customerToBeEdited), newCustomer);
        return "Customer Details Edited Successfully";
    }

    public static String editCustomerSubscription(int customerID, Subscription newSubscription){//Gets passed to it customer id and new subscription
        Customer customerToBeEdited = null;

        //Searches for customer
        for (Customer customer : Gym.gym.customers){
            if (customer.getId() == customerID) {
                customerToBeEdited = customer;
                break;
            }
        }
        if (customerToBeEdited == null) return "Customer Not Found";

        //Checks if new coach has space
        Coach newCoach = null;
        Coach oldCoach = null;
        for (Coach coach : Gym.gym.coaches){
            if (coach.getId() == newSubscription.getAssignedCoachId()) {
                if (coach.getCustomers().size() > 9) return "Coach List Full";
                newCoach = coach;
            }
            if (coach.getId() == customerToBeEdited.getCoachID()){
                oldCoach = coach;
            }
        }
        if (newCoach == null) return "Coach Not Found";
        Gym gym = Gym.gym;
        //Setting New Subscription
        customerToBeEdited.updateSubscription(newSubscription);

        //Updating coaches' customer lists
        oldCoach.getCustomers().remove(customerToBeEdited);
        newCoach.getCustomers().add(customerToBeEdited);

        Gym.gym.subscriptions.remove(customerToBeEdited.getSubscription());
        Gym.gym.subscriptions.add(newSubscription);

        return "Subscription Edited Successfully";
    }

    public static void editCoach(int coachId, Coach newCoach) {
        try {
            boolean coachFound = false;

            for (Coach coach : Gym.gym.coaches) {
                if (coach.getId() == coachId) {
                    coachFound = true;

                    // Replace the old coach with the new one
                    int index = Gym.gym.coaches.indexOf(coach);
                    Gym.gym.coaches.set(index, newCoach);

                    break;
                }
            }

            if (!coachFound) {
                throw new IllegalArgumentException("Coach not found with ID: " + coachId);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void editEquipment(int equipmentCode, Equipment newEquipment) {
        try {
            boolean equipmentFound = false;

            for (Equipment equipment : Gym.gym.sportsEquipments) {
                if (equipment.getEquipmentCode() == equipmentCode) {
                    equipmentFound = true;

                    // Replace the old equipment with the new one
                    int index = Gym.gym.sportsEquipments.indexOf(equipment);
                    Gym.gym.sportsEquipments.set(index, newEquipment);

                    break;
                }
            }

            if (!equipmentFound) {
                throw new IllegalArgumentException("Equipment not found with Code: " + equipmentCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String displaySubscriptionHistory(int customerId) {
        String returnString = "";
        for (Customer customer : Gym.gym.customers) {
            if (customer.getId() == customerId) {
                returnString += ("Subscription history for Customer with ID " + customerId + ":");
                List<Subscription> customerSubscriptions = customer.getOldSubscription();
                if (customerSubscriptions.isEmpty()) return "No Subscription History";
                if (customerSubscriptions.isEmpty()) break;
                int i = 1;
                for (Subscription subscription : customerSubscriptions) {
                    returnString += ("Subscription Number: " + i);
                    returnString += ("Customer Id: " + customerId);
                    returnString += ("Assigned Coach Id: " + subscription.getAssignedCoachId());
                    returnString += ("Start Date: " + subscription.getMembershipPlan().getStartDate());
                    returnString += ("Membership Plan Type: " + subscription.getMembershipPlan().getPlanType());
                    returnString += ("Number of Months: " + subscription.getMembershipPlan().getNumberOfMonths());
                    returnString += ("Price: " + subscription.getMembershipPlan().getPrice());
                    i++;
                }
                return returnString;
            }

        }
        return "Customer Not Found";
    }

    public static String displayCustomersSubscribedInDay(int day,int month, int year) {
        String returnString = "";
            returnString += ("Customers Subscribed in " + month + "/" + year + ":");

            for (Customer customer : Gym.gym.customers) {
                MembershipPlan customerMembership = customer.getSubscription().getMembershipPlan();
                if (customerMembership != null) {
                    LocalDate subscriptionStartDate = customerMembership.getStartDate();
                    int subscriptionMonth = subscriptionStartDate.getMonthValue();
                    int subscriptionYear = subscriptionStartDate.getYear();
                    int subscriptionDay = subscriptionStartDate.getDayOfMonth();

                    // Check if the subscription starts in the specified month and year
                    if (subscriptionMonth == month && subscriptionYear == year && subscriptionDay == day) {
                        returnString += ("\nCustomer ID: " + customer.getId());
                        returnString += (", Customer Name: " + customer.getName());
                        returnString += (", Customer Phone Number: " + customer.getPhoneNumber());
                    }
                }
            }
        if (returnString.equals(("Customers Subscribed in " + month + "/" + year + ":"))) returnString =  "No Customers Found";
        return returnString;
    }

    public static String displayCustomersSubscribedInMonth(int month, int year) {
        String returnString = "";
            returnString += ("Customers Subscribed in " + month + "/" + year + ":");

            for (Customer customer : Gym.gym.customers) {
                MembershipPlan customerMembership = customer.getSubscription().getMembershipPlan();
                if (customerMembership != null) {
                    LocalDate subscriptionStartDate = customerMembership.getStartDate();
                    int subscriptionMonth = subscriptionStartDate.getMonthValue();
                    int subscriptionYear = subscriptionStartDate.getYear();

                    // Check if the subscription starts in the specified month and year
                    if (subscriptionMonth == month && subscriptionYear == year) {
                        returnString += ("\nCustomer ID: " + customer.getId());
                        returnString += (", Customer Name: " + customer.getName());
                        returnString += (", Customer Phone Number: " + customer.getPhoneNumber());
                    }
                }
            }
            if (returnString.equals(("Customers Subscribed in " + month + "/" + year + ":"))) returnString = "No Customers Found";
            return returnString;
    }

    public static String displayCustomersOfCoach(int coachId) {
        String returnString = "";

            for (Coach coach : Gym.gym.coaches) {
                if (coach.getId() == coachId) {
                    returnString += ("Customers of Coach with ID " + coachId + ":");
                    if (coach.getCustomers().isEmpty()) return "No Customers";
                    for (Customer customer : Gym.gym.customers) {
                        if (customer.getSubscription().getAssignedCoachId() == coachId) {
                            returnString += ("\nCustomer ID: " + customer.getId());
                            returnString += ("\nCustomer Name: " + customer.getName());
                            returnString += ("\nCustomer Phone Number: " + customer.getPhoneNumber());
                        }
                    }
                    return returnString;
                }
            }
        return "Coach Not Found";
    }

    public static double displayIncomeInMonth(LocalDate date) {
        double totalIncome = 0.0;

        for (Subscription subscription : Gym.gym.subscriptions) {
            LocalDate startDate = subscription.getMembershipPlan().getStartDate();
            LocalDate endDate = startDate.plusMonths(subscription.getMembershipPlan().getNumberOfMonths());

            // Check if the subscription falls within the specified month and year
            if (endDate.isAfter(date)) {
                // Calculate the income for the subscription
                double subscriptionIncome = subscription.getMembershipPlan().getPrice();
                totalIncome += subscriptionIncome;
            }
        }
        return totalIncome;
    }

    public static String SortCoachesByCustomers(List<Coach> coaches) {
        //Making a copy of coaches
        List<Coach> sortedList = new ArrayList<Coach>(Gym.gym.coaches.size());
        sortedList.addAll(Gym.gym.coaches);
        sortedList.sort(new SortByCustomers());
        String returnString = "";

        for (Coach coach : sortedList) {
            String customers = "";
            try {
                customers = Integer.toString(coach.getCustomers().size());
            } catch (NullPointerException e) {
                customers = "0";
            }
            returnString += "Name: " + coach.getName() + ", ID: " + coach.getId() + ", Customers: " + customers + "\n";
        }
        return returnString;
    }

}

class SortByCustomers implements Comparator<Coach> {
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

        if (o1Size < o2Size) return 1;
        else if (o1Size > o2Size) return -1;
        else return 0;
    }
}
