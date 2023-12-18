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

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAssignedCoachId() {
        return assignedCoachId;
    }

    public void setAssignedCoachId(int assignedCoachId) {
        this.assignedCoachId = assignedCoachId;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    public boolean equals(Subscription sub1, Subscription sub2){
        return sub1.assignedCoachId == sub2.assignedCoachId &&
                sub1.membershipPlan.equals(sub2.membershipPlan) &&
                sub1.customerId == sub2.customerId;
    }
}