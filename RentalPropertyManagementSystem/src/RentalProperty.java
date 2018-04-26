import java.io.Serializable;

public class RentalProperty implements Serializable
// implements Payable temporarily removed, will be needed for polymorphism
{

  //Revisit data types, changed to String to mess with DBManager HashMap
  protected String rentalID;
  protected String propertyDescription;
  protected String propertyAddress;
  protected int rentalType;
  protected int moveInDate;
  protected int lateDate;
  protected int secondLateDate;
  protected String isAvailable;
  protected String isEvicted;
  protected String isLate;
  protected String isLateFinal;
  protected String rentCost;

  //Initial constructor
  public RentalProperty()
  {
      this.rentalID = "";
      this.propertyDescription = "";
      this.propertyAddress = "";
      this.rentalType = 0;
      this.isAvailable = "F";
      this.isEvicted = "";
      this.isLate = "";
      this.moveInDate = 0;
      this.lateDate = 0;
      this.secondLateDate = 0;
      this.rentCost = "";
  }

  public RentalProperty(String rentalID, String propertyDescription,
  String propertyAddress, int rentalType, String isAvailable)
  {
      //set values with overloaded constructor
      this.setRentalID(rentalID);
      this.setPropertyDescription(propertyDescription);
      this.setPropertyAddress(propertyAddress);
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

  public void setPropertyAddress (String propertyAddress)
  {
    this.propertyAddress = propertyAddress;
  }

  public void setRentalType (int rentalType)
  {
    this.rentalType = rentalType;
  }

  public void setIsAvailable (String isAvailable)
  {
    this.isAvailable = isAvailable;
  }

  public void setIsEvicted(String isEvicted)
  {
	  this.isEvicted = isEvicted;
  }

  public void setIsLate(String isLate)
  {
  	this.isLate = isLate;
  }

  public void setIsLateFinal(String isLateFinal)
  {
  	this.isLateFinal = isLateFinal;
  }

  public void setDates(int moveInDate)
  {
    this.moveInDate = moveInDate;
    this.lateDate = moveInDate + 7;
    this.secondLateDate = moveInDate + 14;
  }

  public void setRentCost (String rentCost)
  {
    String rent = "";
    switch (rentalID.substring(1)) {
      case "1": rent = "$500";
      break;
      case "2": rent = "$800";
      break;
      case "3": rent = "$1000";
      break;
    }
    if (rentalID.substring(0).equals("V")) {
      switch (rentalID.substring(1)) {
        case "1": rent = "$50";
        break;
        case "2": rent = "$100";
        break;
        case "3": rent = "$150";
        break;
      }
    }
    this.rentCost = rent;
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

  public String getPropertyAddress()
  {
    return this.propertyAddress;
  }

  public int getRentalType()
  {
    return this.rentalType;
  }

  public String getIsAvailable()
  {
    return this.isAvailable;
  }

  public String getIsEvicted()
  {
	  return this.isEvicted;
  }

  public String getIsLate()
  {
  	return isLate;
  }

  public String getIsLateFinal()
  {
	return isLateFinal;
  }

  public String getRentCost() {
    return rentCost;
  }

}
