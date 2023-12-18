import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class MembershipPlan implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    private LocalDate startDate;
    private PlanType planType;
    private int numberOfMonths;
    private double price;

    public MembershipPlan(LocalDate startDate, PlanType planType,
                          int numberOfMonths) {
        this.startDate = startDate;
        this.planType = planType;
        this.numberOfMonths = numberOfMonths;
        calculatePrice(); // Call the calculatePrice method during initialization
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        calculatePrice(); // Recalculate price after changing start date
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
        calculatePrice(); // Recalculate price after changing plan type
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
        calculatePrice(); // Recalculate price after changing number of months
    }

    public double getPrice() {
        return price;
    }

    // Calculate the price based on the number of months and discount
    private double calculatePrice() {
        double basePricePerMonth =0;
        if (planType==PlanType.THREE_DAYS_PER_WEEK) {
            basePricePerMonth = 50.0;
        }
        else {
            basePricePerMonth = 70.0;
        }
        double discountPercentage = 0.0; // Discount for >= 3 months

        if (numberOfMonths >= 3) {
            discountPercentage = 10.0; // 10% discount for 3 or more months
        }

        double discountedPrice = basePricePerMonth * (1 - discountPercentage / 100);
        price = discountedPrice * numberOfMonths;
        return price;
    }

    public boolean equals(MembershipPlan membership1, MembershipPlan membership2){
        return membership1.startDate.isEqual(membership2.startDate) &&
                membership1.planType == membership2.planType &&
                membership1.numberOfMonths == membership2.numberOfMonths;
    }
}
