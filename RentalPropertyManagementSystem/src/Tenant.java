import java.util.ArrayList;
import java.io.Serializable;

public class Tenant implements Serializable
{
    private String firstName;
    private String lastName;
    private String age;
    private String emailAddress;
    private String billingAddress;
    private String propertyID; //property ID of the property tenant is renting
    ArrayList<Person> coinhabitants = new ArrayList<Person>();

    public Tenant()
    {
       //default constructor
        //set each variable to a default value
        this.firstName = "";
        this.lastName = "";
        this.age = "";
        this.emailAddress = "";
        this.billingAddress = "";
    }

    public Tenant(String firstName, String lastName, String age, String emailAddress, String billingAddress, String propertyID)
    {
        //set values with overloaded constructor
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setEmailAddress(emailAddress);
        this.setBillingAddress(billingAddress);
        this.setPropertyID(propertyID);
    }

    public void addPerson(Person person)
    {
        //adds coinhabitant
        coinhabitants.add(person);
        //TODO maybe add a method to add to DB with SQL
    }

    //--------Setters and getters for private variables
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setBillingAddress(String billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public void setPropertyID(String propertyID)
    {
    	this.propertyID = propertyID;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getAge()
    {
        return this.age;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    public String getBillingAddress()
    {
        return this.billingAddress;
    }

    public String getPropertyID()
    {
    	return this.propertyID;
    }
}
