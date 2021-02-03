import java.io.Serializable;

public class TravProf implements Serializable {

    private String travAgentID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private float tripCost;
    private String travelType;
    private String paymentType;
    private MedCond medCondInfo;

    //*********************************************************
    //CONSTRUCTOR METHOD
    //*********************************************************

    public TravProf(String travAgentID,
                    String firstName,
                    String lastName,
                    String address,
                    String phone,
                    float tripCost,
                    String travelType,
                    String paymentType,
                    MedCond medCondInfo){
        this.travAgentID = travAgentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.tripCost = tripCost;
        this.travelType = travelType;
        this.paymentType = paymentType;
        this.medCondInfo = medCondInfo;
    }

    //*********************************************************
    //GETTER METHODS
    //*********************************************************

    public String getTravAgentID() { return travAgentID; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getAddress() { return address; }

    public String getPhone() {return phone; }

    public float getTripCost() { return tripCost; }

    public String getTravelType() {return travelType; }

    public String getPaymentType() { return paymentType; }

    public MedCond getMedCondInfo() { return medCondInfo; }

    //*********************************************************
    //SETTER METHODS
    //*********************************************************

    public void updateFirstName(String firstName){ this.firstName = firstName; }

    public void updateLastName(String lastName){ this.lastName = lastName; }

    public void updateAddress(String address) { this.address = address; }

    public void updatePhone(String phone) { this.phone = phone; }

    public void updateTripCost(float tripCost) { this.tripCost = tripCost; }

    public void updateTravelType(String travelType) { this.travelType = travelType; }

    public void updatePaymentType(String paymentType) { this.paymentType = paymentType; }

    public void updateMedCondInfo(MedCond medCondInfo) {this.medCondInfo = medCondInfo; }

}
