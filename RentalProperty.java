

public class RentalProperty {

  private String rentalID;
  private String propertyDescription;
  private int rentalType;
  private boolean isAvailable;

  public RentalProperty()
  {
      this.rentalID = "";
      this.propertyDescription = "";
      this.rentalType = 0;
      this.isAvailable = false;
  }

  public RentalProperty(String rentalID, String propertyDescription,
                        int rentalType, boolean isAvailable)
  {
      //set values with overloaded constructor
      this.setRentalID(rentalID);
      this.setPropertyDescription(propertyDescription);
      this.setRentalType(rentalType);
      this.setIsAvailable(isAvailable);
  }

  public void setRentalID (String rentalID) {
    this.rentalID = rentalID;
  }

  public void setPropertyDescription (String propertyDescription) {
    this.propertyDescription = propertyDescription;
  }

  public void setRentalType (int rentalType) {
    this.rentalType = rentalType;
  }

  public void setIsAvailable (boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public String getRentalID() {
    return this.rentalID;
  }

  public String getPropertyDescription() {
    return this.propertyDescription;
  }

  public int getRentalType() {
    return this.rentalType;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }


}
