import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    // Additional attributes for Customer
    private Subscription subscription;
    private ArrayList<Subscription> oldSubscription;
    private List<InBody> inBodyInfo;

    // Constructor

    public Customer(int id, String name, char gender, String address, String phoneNumber, String email, String password) {
        super(id, name, gender, address, phoneNumber, email, password);
    }

    //getters and setters
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<InBody> getInBodyInfo() {
        return inBodyInfo;
    }

    public void setInBodyInfo(List<InBody> inBodyInfo) {
        this.inBodyInfo = inBodyInfo;
    }

    // Constraints
    public boolean isAllowedToPerformInBody() {
        if (inBodyInfo.isEmpty()) {
            return true;
        }
        else {
            LocalDate currentDate = LocalDate.now();
            InBody lastInbody = inBodyInfo.get((inBodyInfo.size() - 1));
            LocalDate LastTime = lastInbody.getDateOfInBody();
            long difference = ChronoUnit.DAYS.between(currentDate, LastTime);
            return difference < 30;
        }
    }

    // Additional functionalities

    //1
    public void showCoachInfo() {
        Coach customerCoach = null;
        int coachId = subscription.getAssignedCoachId();
        List<Coach>coacheslist = Gym.gym.coaches;
        for (Coach coach : coacheslist) {
            if (coach.getId()==coachId)
            {
                customerCoach = coach;
                break;
            }
        }
        if (customerCoach != null) {
            System.out.println("Coach Information:");
            System.out.println("Name: " + customerCoach.getName());
            System.out.println("Phone Number: " + customerCoach.getPhoneNumber());
            System.out.println("Working Hours: " + customerCoach.getMaxWorkingHoursPerDay() + " hours per day");
        } else {
            System.out.println("No coach information available for this customer.");
        }
    }

    //2
    public void displayGymEquipment() {
        try {
            int i = 0;
            List<Equipment> equipmentList = Gym.getGym().sportsEquipments;
            for (Equipment equipment : equipmentList) {
                System.out.println("Equipment Information Number:" + (i + 1));
                System.out.println("Name: " + equipment.getEquipmentName());
                System.out.println("Code : " + equipment.getEquipmentCode());
                System.out.println("Quantity : " + equipment.getQuantity());
                //System.out.println("Photo : " + equipment.getEquipmentPhoto());
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: No Gym equipment available.");
        }
    }


    //3
    public void displayMembershipDetails(){
        MembershipPlan myMembership = subscription.getMembershipPlan();
        System.out.println("MembershipPlan Information:");
        System.out.println("StartDate :" + myMembership.getStartDate());
        System.out.println("PlanType :" + myMembership.getPlanType());
        System.out.println("Number Of Months for your membership is :" + myMembership.getNumberOfMonths());
        System.out.println("Price  :"+myMembership.getPrice());
    }
    //4

    public void displayInBodyInfoAtDate(LocalDate specificDate) {
        boolean found = false;

        for (InBody inBody : inBodyInfo) {
            if (inBody.getDateOfInBody().equals(specificDate)) {
                found = true;
                System.out.println("InBody Information on " + specificDate + ":");
                System.out.println("Height: " + inBody.getHeight());
                System.out.println("Total Weight: " + inBody.getTotalWeight());
                System.out.println("Body Fat Mass: " + inBody.getBodyFatMass());
                System.out.println("Minerals: " + inBody.getMinerals());
                System.out.println("Total Body Water: " + inBody.getTotalBodyWater());
                System.out.println("Protein: " + inBody.getProtein());
                System.out.println("---------------------");
                break;
            }
        }

        if (!found) {
            System.out.println("No InBody information available for the specified date: " + specificDate);
        }
    }


    public void displayWeightLossGoal() {
        try {
            InBody lastInBody = inBodyInfo.get(inBodyInfo.size() - 1);
            double targetWeight = lastInBody.getHeight() - 100;
            double weightToLose = lastInBody.getTotalWeight() - targetWeight;

            System.out.println("Customer: " + getName());
            System.out.println("Current Weight: " + lastInBody.getTotalWeight() + " kg");
            System.out.println("Target Weight: " + targetWeight + " kg");

            if (weightToLose == 0) {
                System.out.println("Your weight is perfect");
            } else if (weightToLose > 0) {
                System.out.println("You need to lose " + weightToLose + " kg");
            } else {
                System.out.println("You need to gain " + (weightToLose * -1) + " kg");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: No InBody information available for the customer.");
        }
    }

    //Functions for GUI
    public int getCoachID() {
        int coachId = subscription.getAssignedCoachId();
        return coachId;
    }

    public String displayMembershipDetails(int i) {
        String tempString = "";
        MembershipPlan myMembership = subscription.getMembershipPlan();
        tempString += ("MembershipPlan Information: ");
        tempString += ("\nStartDate: " + myMembership.getStartDate());
        tempString += ("\nPlanType: " + myMembership.getPlanType());
        tempString += ("\nNumber Of Months for your membership is: " + myMembership.getNumberOfMonths());
        tempString += ("\nPrice: " + myMembership.getPrice());
        return tempString;
    }
    public String displayInBodyInfoAtDate(LocalDate specificDate, int i) {
        String returnString = "";
        for (InBody inBody : inBodyInfo) {
            if (inBody.getDateOfInBody().equals(specificDate)) {
                returnString += ("Height: " + inBody.getHeight()) + "\n";
                returnString += ("Total Weight: " + inBody.getTotalWeight()) + "\n";
                returnString += ("Body Fat Mass: " + inBody.getBodyFatMass()) + "\n";
                returnString += ("Minerals: " + inBody.getMinerals()) + "\n";
                returnString += ("Total Body Water: " + inBody.getTotalBodyWater() + "\n");
                returnString += ("Protein: " + inBody.getProtein()) + "\n";
                break;
            }
            else returnString = "No information found";
        }
        return returnString;
    }

    public String displayWeightLossGoal(int i) {
        String returnString = "";
        try {
            InBody lastInBody = inBodyInfo.get(inBodyInfo.size() - 1);
            double targetWeight = lastInBody.getHeight() - 100;
            double weightToLose = lastInBody.getTotalWeight() - targetWeight;

            returnString += ("Customer: " + getName());
            returnString += ("\nCurrent Weight: " + lastInBody.getTotalWeight() + " kg");
            returnString += ("\nTarget Weight: " + targetWeight + " kg");

            if (weightToLose == 0) {
                returnString += ("\nYour weight is perfect");
            } else if (weightToLose > 0) {
                returnString += ("\nYou need to lose " + weightToLose + " kg");
            } else {
                returnString += ("\nYou need to gain " + (weightToLose * -1) + " kg");
            }
        } catch (IndexOutOfBoundsException e) {
            returnString += ("Error: No InBody information available for the customer.");
        }
        return returnString;
    }

}
