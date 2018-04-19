 /*
  * Author: Kyle Mayer
  * Email:  kmayer1@cnm.edu
  * File:   Tenant.java
  * Date:   4/5/2018
  * Course: CSCI2251 Sec 101
 */

import java.util.ArrayList;

public class Tenant
{
    private String firstName;
    private String lastName;
    private int age;
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
        this.age = 0;
        this.emailAddress = "";
        this.billingAddress = "";
    }

    public Tenant(String firstName, String lastName, int age, String emailAddress, String billingAddress, String propertyID)
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

    public void setAge(int age)
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

    public int getAge()
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
