import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//         Creating Gym object
        Gym gym = Gym.getGym();

        //New Customers
//        InBody B = new InBody(LocalDate.now(), 180.0, 80.0, 50, 20, 20, 20);
//        ArrayList<InBody> BB = new ArrayList<>();
//        BB.add(B);
//        MembershipPlan A = new MembershipPlan(LocalDate.now(), PlanType.THREE_DAYS_PER_WEEK, 3);
//        Subscription AA = new Subscription(201, 101, A);
//        Subscription AAA = new Subscription(202, 101, A);
//        Customer customer1 = new Customer(201, "Bob Customer", 'M', "123 Birch", "123-456-789", "john.coach@example.com", AA, BB);
//        Customer customer2 = new Customer(202, "Boba Customer", 'F', "123 Birch", "123-456-789", "john.coach@example.com", AAA, null);
//        ArrayList<Customer> customerList1 = new ArrayList<Customer>();
//        ArrayList<Customer> customerList2 = new ArrayList<Customer>();
//        customerList1.add(customer1);
//        customerList1.add(customer2);
//        customerList2.add(customer1);
//        ArrayList<Subscription> subscriptions = new ArrayList<>();
//        subscriptions.add(AA);
//        subscriptions.add(AAA);
//        gym.setSubscriptions(subscriptions);
//
//        // Setting up Equipment and Coach objects
//        Equipment treadmill = new Equipment("Treadmill", 301, 5, "treadmill_photo.jpg");
//        Equipment rowingMachine = new Equipment("Rowing Machine", 302, 3, "rowing_machine_photo.jpg");
//        ArrayList<Equipment> gymEquipment = new ArrayList<Equipment>();
//        gymEquipment.add(treadmill);
//        gymEquipment.add(rowingMachine);
//
//        Coach coach1 = new Coach(101, "John Coach", 'M', "789 Pine St", "555-8765", "john.coach@example.com", 8, null);
//        Coach coach2 = new Coach(102, "Jane Coach", 'F', "987 Oak St", "555-9876", "jane.coach@example.com", 6, null);
//        Coach coach3 = new Coach(103, "Bob Coach", 'M', "789 Pine St", "555-8765", "john.coach@example.com", 8, null);
//        coach1.setCustomers(customerList1);
//        coach2.setCustomers(customerList2);
//        ArrayList<Coach> coaches = new ArrayList<Coach>();
//        coaches.add(coach1);
//        coaches.add(coach2);
//        coaches.add(coach3);
//        gym.setCoaches(coaches);
//        gym.setSportsEquipments(gymEquipment);
//        gym.setCustomers(customerList1);
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
//            oos.writeObject(gym);
//            System.out.println("Serialization complete.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
//            gym = (Gym) ois.readObject();
//            System.out.println("Deserialization complete.");
//        } catch (Exception e) {
//            System.out.println("Error");
        }
}
