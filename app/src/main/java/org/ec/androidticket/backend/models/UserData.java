package org.ec.androidticket.backend.models;

public class UserData
{
    private String email;
    private String firstName;
    private String lastName;
    private boolean isStaff;

    public UserData(String email, String firstName, String lastName, boolean isStaff)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStaff = isStaff;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public boolean isStaff()
    {
        return isStaff;
    }
}
