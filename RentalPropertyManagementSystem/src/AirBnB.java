public class AirBnB extends RentalProperty
{

  private String rentCost;
  private int dueDate;
  private float lateFee;

  //Initial constructor
  public AirBnB()
  {
      this.rentCost = "";
      this.dueDate = 0;
      this.lateFee = 0;
  }

  public AirBnB(String rentCost, int dueDate, float lateFee)
  {
      //set values with overloaded constructor
      this.setRentCost(rentCost);
      this.setDueDate(dueDate);
      this.setLateFee(lateFee);
  }

  //Variable set methods
  public void setRentCost (String rentCost)
  {
    this.rentCost = rentCost;
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
