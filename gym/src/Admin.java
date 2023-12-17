import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Admin {
    private Admin(){} //Can't make object of admin. Only contains static methods
    //TODO move functions from gym/gui to here
    public void addCustomer(Customer newCustomer) {
        Gym.gym.customers.add(newCustomer);
    }

    public void addCoach(Coach newCoach) {
        Gym.gym.coaches.add(newCoach);
    }

    public void addEquipment(Equipment newEquipment) {
        Gym.gym.sportsEquipments.add(newEquipment);
    }

    public void deleteCustomer(int customerID) {
        try {
            int index = -1;
            for (int i = 0; i < Gym.gym.customers.size(); i++) {
                if (Gym.gym.customers.get(i).getId() == customerID) {
                    index = i;
                }
            }
            if (index != -1) {
                Gym.gym.customers.remove(index);
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
            for (int i = 0; i < Gym.gym.coaches.size(); i++) {
                if (Gym.gym.coaches.get(i).getId() == coachID) {
                    index = i;
                }
            }
            if (index != -1) {
                Gym.gym.coaches.remove(index);
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

    public void editCustomer(int customerId, Customer newCustomer) {
        try {
            boolean customerFound = false;

            for (Customer customer : Gym.gym.customers) {
                if (customer.getId() == customerId) {
                    customerFound = true;

                    // Replace the old customer with the new one
                    int index = Gym.gym.customers.indexOf(customer);
                    Gym.gym.customers.set(index, newCustomer);

                    System.out.println("Customer with ID " + customerId + " updated successfully.");
                    break;
                }
            }

            if (!customerFound) {
                throw new IllegalArgumentException("Customer not found with ID: " + customerId);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void editCoach(int coachId, Coach newCoach) {
        try {
            boolean coachFound = false;

            for (Coach coach : Gym.gym.coaches) {
                if (coach.getId() == coachId) {
                    coachFound = true;

                    // Replace the old coach with the new one
                    int index = Gym.gym.coaches.indexOf(coach);
                    Gym.gym.coaches.set(index, newCoach);

                    System.out.println("Coach with ID " + coachId + " updated successfully.");
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

    public void editEquipment(int equipmentCode, Equipment newEquipment) {
        try {
            boolean equipmentFound = false;

            for (Equipment equipment : Gym.gym.sportsEquipments) {
                if (equipment.getEquipmentCode() == equipmentCode) {
                    equipmentFound = true;

                    // Replace the old equipment with the new one
                    int index = Gym.gym.sportsEquipments.indexOf(equipment);
                    Gym.gym.sportsEquipments.set(index, newEquipment);

                    System.out.println("Equipment with Code " + equipmentCode + " updated successfully.");
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

    public void displaySubscriptionHistory(int customerId) {
        try {
            System.out.println("Subscription history for Customer with ID " + customerId + ":");

            for (Customer customer : Gym.gym.customers) {
                if (customer.getId() == customerId) {
                    List<Subscription> customerSubscriptions = customer.getOldSubscription();
                    int i = 1;
                    for (Subscription subscription : customerSubscriptions) {
                        System.out.println("Subscription Number: " + i );
                        System.out.println("Customer Id: " + customerId );
                        System.out.println("Assigned Coach Id: " + subscription.getAssignedCoachId() );
                        System.out.println("Start Date: " + subscription.getMembershipPlan().getStartDate());
                        System.out.println("Membership Plan Type: " + subscription.getMembershipPlan().getPlanType());
                        System.out.println("Number of Months: " + subscription.getMembershipPlan().getNumberOfMonths());
                        System.out.println("Price: " + subscription.getMembershipPlan().getPrice());
                        System.out.println(); // Add a line break for better readability
                        i++;
                    }
                    return; // Exit the loop once the customer is found
                }
            }

            // If the loop completes without finding the customer, print an error message
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayCustomersSubscribedInDay(int day,int month, int year) {
        try {
            System.out.println("Customers Subscribed in " + month + "/" + year + ":");

            for (Customer customer : Gym.gym.customers) {
                MembershipPlan customerMembership = customer.getSubscription().getMembershipPlan();
                if (customerMembership != null) {
                    LocalDate subscriptionStartDate = customerMembership.getStartDate();
                    int subscriptionMonth = subscriptionStartDate.getMonthValue();
                    int subscriptionYear = subscriptionStartDate.getYear();
                    int subscriptionDay = subscriptionStartDate.getDayOfMonth();

                    // Check if the subscription starts in the specified month and year
                    if (subscriptionMonth == month && subscriptionYear == year && subscriptionDay == day) {
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

    public void displayCustomersSubscribedInMonth(int month, int year) {
        try {
            System.out.println("Customers Subscribed in " + month + "/" + year + ":");

            for (Customer customer : Gym.gym.customers) {
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

    public void displayCustomersOfCoach(int coachId) {
        try {
            System.out.println("Customers of Coach with ID " + coachId + ":");
            boolean coachFound = false;
            for (Coach coach : Gym.gym.coaches) {
                if (coach.getId() == coachId) {
                    coachFound = true;
                    for (Customer customer : Gym.gym.customers) {
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

    public double displayIncomeInMonth(int month, int year) {
        try {
            System.out.println("GYM Income for " + month + " " + year + ":");

            double totalIncome = 0.0;

            for (Subscription subscription : Gym.gym.subscriptions) {
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
            returnString += "Name: " + coach.getName() + " ID: " + coach.getId() + " Customers: " + customers + "\n";
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

        if (o1Size > o2Size) return 1;
        else if (o1Size < o2Size) return -1;
        else return 0;
    }
}
