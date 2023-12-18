import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class InBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    // Attributes
    private LocalDate dateOfInBody;
    private double height;
    private double totalWeight;
    private double bodyFatMass;
    private double minerals;
    private double totalBodyWater;
    private double protein;

    public InBody(LocalDate dateOfInBody, double height,
                  double totalWeight, double bodyFatMass,
                  double minerals, double totalBodyWater,
                  double protein) {
        this.dateOfInBody = dateOfInBody;
        this.height = height;
        this.totalWeight = totalWeight;
        this.bodyFatMass = bodyFatMass;
        this.minerals = minerals;
        this.totalBodyWater = totalBodyWater;
        this.protein = protein;
    }

    public LocalDate getDateOfInBody() {
        return dateOfInBody;
    }


    public double getHeight() {
        return height;
    }


    public double getTotalWeight() {
        return totalWeight;
    }


    public double getBodyFatMass() {
        return bodyFatMass;
    }


    public double getMinerals() {
        return minerals;
    }


    public double getTotalBodyWater() {
        return totalBodyWater;
    }


    public double getProtein() {
        return protein;
    }

}
