import java.io.Serializable;

public class RentalProperty implements Serializable
// implements Payable temporarily removed, will be needed for polymorphism
{

  //Revisit data types, changed to String to mess with DBManager HashMap
  private String rentalID;
  private String propertyDescription;
  private int rentalType;
  private int moveInDate;
  private int lateDate;
  private int secondLateDate;
  private String isAvailable;
  private boolean isEvicted;
  private boolean isLate;
  private boolean isLateFinal;

  //Initial constructor
  public RentalProperty()
  {
      this.rentalID = "";
      this.propertyDescription = "";
      this.rentalType = 0;
      this.isAvailable = "F";
      this.isEvicted = false;
      this.isLate = false;
      this.moveInDate = 0;
      this.lateDate = 0;
      this.secondLateDate = 0;
  }

  public RentalProperty(String rentalID, String propertyDescription,
                        int rentalType, String isAvailable)
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

  public void setIsAvailable (String isAvailable)
  {
    this.isAvailable = isAvailable;
  }

  public void setIsEvicted(boolean isEvicted)
  {
	  this.isEvicted = isEvicted;
  }

  public void setIsLate(boolean isLate)
  {
  	this.isLate = isLate;
  }

  public void setIsLateFinal(boolean isLateFinal)
  {
  	this.isLateFinal = isLateFinal;
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

  public String getIsAvailable()
  {
    return this.isAvailable;
  }

  public boolean getIsEvicted()
  {
	  return this.isEvicted;
  }

  public boolean getIsLate()
  {
  	return isLate;
  }

  public boolean getIsLateFinal()
  {
	return isLateFinal;
  }

}
