import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//    Creating Example gym & Saving data to file
        Gym gym = new Gym("X Gym", "123 Main St", "555-1234");

//        New Customers
        InBody B = new InBody(LocalDate.now(), 180.0, 80.0, 50, 20, 20, 20);
        ArrayList<InBody> BB = new ArrayList<>();
        BB.add(B);
        MembershipPlan A = new MembershipPlan(LocalDate.now(), PlanType.THREE_DAYS_PER_WEEK, 3);
        Subscription AA = new Subscription(1, 1, A);
        Subscription AAA = new Subscription(2, 1, A);
        Customer customer1 = new Customer(1, "Bob Customer", 'M', "123 Birch", "123-456-789", "bob.customer@example.com", new char[] {'1','2','3','4','5'});
        customer1.setSubscription(AA);
        customer1.setInBodyInfo(BB);
        Customer customer2 = new Customer(2, "Boba Customer", 'F', "123 Birch", "123-456-789", "boba.customer@example.com", new char[] {'1','2','3','4','5'});
        customer2.setSubscription(AAA);
        ArrayList<Customer> customerList1 = new ArrayList<Customer>();
        ArrayList<Customer> customerList2 = new ArrayList<Customer>();
        customerList1.add(customer1);
        customerList1.add(customer2);
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(AA);
        subscriptions.add(AAA);
        gym.setSubscriptions(subscriptions);

        // Setting up Equipment and Coach objects
        Equipment treadmill = new Equipment("Treadmill", 1, 5);
        Equipment rowingMachine = new Equipment("Rowing Machine", 2, 3);
        ArrayList<Equipment> gymEquipment = new ArrayList<Equipment>();
        gymEquipment.add(treadmill);
        gymEquipment.add(rowingMachine);

        Coach coach1 = new Coach(1, "John Coach", 'M', "789 Pine St", "555-8765", "john.coach@example.com", 8, new char[] {'1','2','3','4','5'});
        Coach coach2 = new Coach(2, "Jane Coach", 'F', "987 Oak St", "555-9876", "jane.coach@example.com", 6, new char[] {'1','2','3','4','5'});
        Coach coach3 = new Coach(3, "Bob Coach", 'M', "100 Pine St", "555-1234", "bob.coach@example.com", 8, new char[] {'1','2','3','4','5'});
        coach1.setCustomers(customerList1);
        coach2.setCustomers(customerList2);
        ArrayList<Coach> coaches = new ArrayList<Coach>();
        coaches.add(coach1);
        coaches.add(coach2);
        coaches.add(coach3);
        gym.setCoaches(coaches);
        gym.setSportsEquipments(gymEquipment);
        gym.setCustomers(customerList1);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(gym);
            System.out.println("Serialization complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gym.setGym(gym);

//        Gym gym;
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
//            gym = (Gym) ois.readObject();
//            System.out.println("Deserialization complete.");
//        } catch (Exception e) {
//            System.out.println("Error");
//        }


}}
