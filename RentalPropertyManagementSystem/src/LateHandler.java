import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class LateHandler 
{
	//Late handler has Tenant and RentalProperty objects and uses them for processing
	private Tenant tenant = new Tenant();
	private RentalProperty property = new RentalProperty();
	private String firstRentStatement;
	private String secondRentStatement;
	private String finalRentStatement;
	private String actionLetter;

	//overloaded constructor 
	public LateHandler(Tenant tenant, RentalProperty property)
	{
		this.setTenant(tenant);
		this.setProperty(property);
	}
	
	public String generateFirstRentStatement()
	{
		//create formatted String 
		String formattedString = "Dear %s %s,\n" + 
				"\n" + 
				"Subject: Initial Rental Statement\n" + 
				"\n" + 
				"\tYour rent will be due on the first of next month. The amount due is $%.2f.\n" + 
				"\n" + 
				"Thank you,\n" + 
				"\n" + 
				"iRental Solutions Inc.\n";
		
		//need to get first name, last name, amount of rent due
		//firstRentStatement = formattedString.format(tenant.getFirstName(), tenant.getLastName(), 
		return firstRentStatement; 
	}
	
	public String generateSecondRentStatement()
	{
		String formattedString = "Dear %s %s,\n" + 
				"\n" + 
				"Subject: Notification of late payment\n" + 
				"\n" + 
				"Your rent was due on the first of the month. The amount due was $%.2f.\n" + 
				"Because the payment is late, a late fee of $.2f has been added,\n" + 
				"making the total amount due %.2f. This is due in 7 days.\n" + 
				"\n" + 
				"Thank you,\n" + 
				"\n" + 
				"iRental Solutions Inc.\n";
	
		//need to get first name, last name, rent cost, late fee, and then add rentCost+lateFee together
		//formats String to add in all values needed for secondRentStatement
		//secondRentStatement = formattedString.format(tenant.getFirstName(), tenant.getLastName(), 
		//					property.getRentCost(), property.getLateFee(), (property.getRentCost()+property.getLateFee()));
				
		return secondRentStatement;
	}
	
	public String generateFinalRentDueStatement()
	{
		String formattedString = "Dear %s %s,\n" + 
				"\n" + 
				"Subject: Final Notice of Rent Past Due\n" + 
				"\n" + 
				"Your rent is far past due. The amount due was $.2f\n" + 
				"Because the payment is late,a late fee of $.2f has been added.\n" + 
				"As no attempt to pay was made since our last letter, if the payment is \n" + 
				"not in 7 days, then your lease agreement will be terminated.\n" + 
				"\n" + 
				"making the total amount due $.2f.\n" + 
				"\n" + 
				"Thank you,\n" + 
				"\n" + 
				"iRental Solutions Inc.\n";
		
		//need to get first name, last name, rent cost, late fee, and then add rentCost+lateFee together
		//finalRentStatement = formattedString.format(tenant.getFirstName(), tenant.getLastName(), property.getRentCost(), 
		//				property.getLateFee(), (property.getRentCost()+property.getLateFee()));
		
		return finalRentStatement;
	}
	
	public String generateActionLetter()
	{
		String formattedString = "Dear %s %s,\n" + 
				"\n" + 
				"Subject: Termination of Lease\n" + 
				"\n" + 
				"Your rent is 3 weeks past due. The amount due was $.2f.\n" + 
				"Because the payment is late, a late fee of $.2f has been added.\n" + 
				"As no attempt to pay was made since our last message one week ago,\n" + 
				"we will be terminating your rental agreement. \n" + 
				"You must move out within one week.\n" + 
				"\n" + 
				"Regards,\n" + 
				"\n" + 
				"iRental Solutions Inc.\n";
		
		//need to get first name, last name, rent cost, late fee, and then add rentCost+lateFee together
		//actionLetter = formattedString.format(tenant.getFirstName(), tenant.getLastName(), property.getRentCost(), 
		//			property.getLateFee(), (property.getRentCost()+property.getLateFee()));
		
		return actionLetter;
	}

	public void writeLetter()
	{
		//create the filename with tenant info and unique property identification string
		//format will look like John_Doe_A12345.txt
		String fileName = tenant.getFirstName() + "_" + tenant.getLastName() + "_" + tenant.getPropertyID() + ".txt";

		try
		{
			PrintWriter fileOut = new PrintWriter(fileName);
			if(property.getIsEvicted())
			{
				fileOut.write(actionLetter);
			}
			else if(property.getIsLate())
			{
				fileOut.write(secondRentStatement);
			}
			else if(property.getIsLateFinal())
			{
				fileOut.write(finalRentStatement);
			}
			else
			{
				fileOut.write(firstRentStatement);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occurred while writing to the file " + fileName);
			e.printStackTrace();
		}
		
	}
	
	public void setProperty(RentalProperty property)
	{
		this.property = property;
	}
	
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

}
