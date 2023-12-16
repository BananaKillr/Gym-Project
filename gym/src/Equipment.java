import java.io.Serial;
import java.io.Serializable;

public class Equipment implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    // Attributes
    private String equipmentName;
    private int equipmentCode;
    private int quantity;
    //private String equipmentPhoto; // Optional

    //constructor
    public Equipment(String equipmentName, int equipmentCode,
                     int quantity, String equipmentPhoto) {
        this.equipmentName = equipmentName;
        this.equipmentCode = equipmentCode;
        this.quantity = quantity;
        //this.equipmentPhoto = equipmentPhoto;
    }

    //getters and setters
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(int equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public String getEquipmentPhoto() {
//        return equipmentPhoto;
//    }
//
//    public void setEquipmentPhoto(String equipmentPhoto) {
//        this.equipmentPhoto = equipmentPhoto;
//    }

}
