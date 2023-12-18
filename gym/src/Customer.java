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
    private ArrayList<Subscription> oldSubscription = new ArrayList<>();
    private List<InBody> inBodyInfo = new ArrayList<>();

    // Constructor

    public Customer(int id, String name, char gender, String address, String phoneNumber, String email, char[] password) {
        super(id, name, gender, address, phoneNumber, email, password);
    }
    //getters and setters
    public Subscription getSubscription() {
        return subscription;
    }

    public void setOldSubscription(ArrayList<Subscription> subscriptions){
        this.oldSubscription =subscriptions;
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
            InBody lastInbody = inBodyInfo.getLast();
            LocalDate LastTime = lastInbody.getDateOfInBody();
            long difference = ChronoUnit.DAYS.between(currentDate, LastTime);
            return difference > 30;
        }
    }

    //Functions for GUI
    public String getCoachInfo() {
        String returnString = "";
        int coachId = subscription.getAssignedCoachId();
        for (Coach coach : Gym.gym.coaches){
            if (coach.getId() == coachId){
                returnString += "Coach Name: " + coach.getName() +
                        "\nPhone Number: " + coach.getPhoneNumber()+
                        "\nWorking Hours: " + coach.getMaxWorkingHoursPerDay();
            }
        }
        return  returnString;
    }
    public String displayMembershipDetails() {
        String tempString = "";
        MembershipPlan myMembership = subscription.getMembershipPlan();
        tempString += ("MembershipPlan Information: ");
        tempString += ("\nStartDate: " + myMembership.getStartDate());
        tempString += ("\nPlanType: " + myMembership.getPlanType());
        tempString += ("\nNumber Of Months for your membership is: " + myMembership.getNumberOfMonths());
        tempString += ("\nPrice: " + myMembership.getPrice());
        return tempString;
    }
    public String displayInBodyInfoAtDate(LocalDate specificDate) {
        String returnString = "";
        for (int i = inBodyInfo.size() - 1 ; i >= 0; i--){
            InBody inbody = inBodyInfo.get(i);
            LocalDate date = inbody.getDateOfInBody();

            if (inBodyInfo.isEmpty()) {
                returnString = "No In Bodies";
            }
            else if (inbody.getDateOfInBody().isBefore(specificDate) || inbody.getDateOfInBody().isEqual(specificDate)){
                returnString += "Date: " + inbody.getDateOfInBody().toString() + "\n";
                returnString += ("Height: " + inbody.getHeight()) + "\n";
                returnString += ("Total Weight: " + inbody.getTotalWeight()) + "\n";
                returnString += ("Body Fat Mass: " + inbody.getBodyFatMass()) + "\n";
                returnString += ("Minerals: " + inbody.getMinerals()) + "\n";
                returnString += ("Total Body Water: " + inbody.getTotalBodyWater() + "\n");
                returnString += ("Protein: " + inbody.getProtein()) + "\n";
                break;
            }
            else {
                returnString = "No In Bodies Before Date";
            }
        }
//        for (InBody inBody : inBodyInfo) {
//            if (inBody.getDateOfInBody().equals(specificDate)) {
//                returnString += ("Height: " + inBody.getHeight()) + "\n";
//                returnString += ("Total Weight: " + inBody.getTotalWeight()) + "\n";
//                returnString += ("Body Fat Mass: " + inBody.getBodyFatMass()) + "\n";
//                returnString += ("Minerals: " + inBody.getMinerals()) + "\n";
//                returnString += ("Total Body Water: " + inBody.getTotalBodyWater() + "\n");
//                returnString += ("Protein: " + inBody.getProtein()) + "\n";
//                break;
//            }
//            else returnString = "No information found";
//        }
        return returnString;
    }

    public String displayWeightLossGoal() {
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

    public int getCoachID() {
        return subscription.getAssignedCoachId();
    }

    public void addInBody(InBody inBody){
        if (this.isAllowedToPerformInBody()) {
            this.inBodyInfo.add(inBody);
        }
    }

    public ArrayList<Subscription> getOldSubscription() {
        return oldSubscription;
    }

    public void updateSubscription(Subscription newSubscription) {
        String result = "";
        try {
            oldSubscription.add(subscription);
            subscription = newSubscription;
            result += ("Subscription updated successfully.");

        } catch (Exception e) {
            result += ("Error: " + e.getMessage());
        }
    }

    public void deleteLastInBody(){
        if (!inBodyInfo.isEmpty()) inBodyInfo.removeLast();
    }
}