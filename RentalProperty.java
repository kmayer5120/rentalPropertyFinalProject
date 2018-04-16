public abstract class RentalProperty implements Payable
{

  private String rentalID;
  private String propertyDescription;
  private int rentalType;
  private int moveInDate;
  private int lateDate;
  private int secondLateDate;
  private boolean isAvailable;

  //Initial constructor
  public RentalProperty()
  {
      this.rentalID = "";
      this.propertyDescription = "";
      this.rentalType = 0;
      this.isAvailable = false;
      this.moveInDate = 0;
      this.lateDate = 0;
      this.secondLateDate = 0;
  }

  public RentalProperty(String rentalID, String propertyDescription,
                        int rentalType, boolean isAvailable)
  {
      //set values with overloaded constructor
      this.setRentalID(rentalID);
      this.setPropertyDescription(propertyDescription);
      this.setRentalType(rentalType);
      this.setIsAvailable(isAvailable);
      this.setDates(moveInDate);
  }

  //Variable set methods
  public void setRentalID (String rentalID)
  {
    this.rentalID = rentalID;
  }

  public void setPropertyDescription (String propertyDescription)
  {
    this.propertyDescription = propertyDescription;
  }

  public void setRentalType (int rentalType)
  {
    this.rentalType = rentalType;
  }

  public void setIsAvailable (boolean isAvailable)
  {
    this.isAvailable = isAvailable;
  }

  public void setDates(int moveInDate)
  {
    this.moveInDate = moveInDate;
    this.lateDate = moveInDate + 7;
    this.secondLateDate = moveInDate + 14;
  }

  //Variable get methods
  public String getRentalID()
  {
    return this.rentalID;
  }

  public String getPropertyDescription()
  {
    return this.propertyDescription;
  }

  public int getRentalType()
  {
    return this.rentalType;
  }

  public boolean getIsAvailable()
  {
    return this.isAvailable;
  }


}
