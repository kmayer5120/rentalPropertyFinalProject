 /*
  * Author: Kyle Mayer
  * Email:  kmayer1@cnm.edu
  * File:   Person.java
  * Date:   4/3/2018
  * Course: CSCI2251 Sec 101
 */


class Person
{
    //delcare private variables
    private String firstName;
    private String lastName;
    private int age;

    public Person()
    {
        //default constructor
        //set each variable to a default value
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
    }

    public Person(String firstName, String lastName, int age)
    {
        //overloaded constructor
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
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
}
