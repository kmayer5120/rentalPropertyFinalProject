public class Apartment extends RentalProperty
{

  private String rentCost;
  private int dueDate;
  private float lateFee;

  //Initial constructor
  public Apartment()
  {
      this.rentCost = "";
      this.dueDate = 0;
      this.lateFee = 0;
  }

  public Apartment(String rentCost, int dueDate, float lateFee, String ammenities)
  {
      //set values with overloaded constructor
      this.setRentCost(rentCost);
      this.setDueDate(dueDate);
      this.setLateFee(lateFee);
  }

  //Variable set methods
  public void setRentCost (String rentCost)
  {
    String rent = "";
    switch (rentalID.substring(1)) {
      case "1": rent = "$500";
      break;
      case "2": rent = "$800";
      break;
    }
    this.rentCost = rent;
  }

  public void setDueDate (int dueDate)
  {
    this.dueDate = dueDate;
  }

  public void setLateFee (float lateFee)
  {
    this.lateFee = lateFee;
  }

  //Variable get methods
  public String getRentCost()
  {
    return this.rentCost;
  }

  public int getDueDate()
  {
    return this.dueDate;
  }

  public float getLateFee()
  {
    return this.lateFee;
  }

}
