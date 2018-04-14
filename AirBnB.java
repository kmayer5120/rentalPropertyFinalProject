public class AirBnB extends RentalProperty {

  private float rentCost;
  private int dueDate;
  private float lateFee;

  //Initial constructor
  public AirBnB()
  {
      this.rentCost = 0;
      this.dueDate = 0;
      this.lateFee = 0;
  }

  public AirBnB(float rentCost, int dueDate, float lateFee)
  {
      //set values with overloaded constructor
      this.setRentCost(rentCost);
      this.setDueDate(dueDate);
      this.setLateFee(lateFee);
  }

  //Variable set methods
  public void setRentCost (float rentCost) {
    this.rentCost = rentCost;
  }

  public void setDueDate (int dueDate) {
    this.dueDate = dueDate;
  }

  public void setLateFee (float lateFee) {
    this.lateFee = lateFee;
  }

  //Variable get methods
  public float getRentCost() {
    return this.rentCost;
  }

  public int getDueDate() {
    return this.dueDate;
  }

  public float getLateFee() {
    return this.lateFee;
  }

}
