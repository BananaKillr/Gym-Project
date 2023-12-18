import java.io.Serial;
import java.io.Serializable;

public class Subscription implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;
    // Attributes
    private int customerId;
    private int assignedCoachId;
    private MembershipPlan membershipPlan;

    // Constructor
    public Subscription(int customerId, int assignedCoachId,
                        MembershipPlan membershipPlan) {
        this.customerId = customerId;
        this.assignedCoachId = assignedCoachId;
        this.membershipPlan = membershipPlan;
    }

    // Getter and Setter methods
    public int getCustomerId() {
        return customerId;
    }


    public int getAssignedCoachId() {
        return assignedCoachId;
    }


    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

}