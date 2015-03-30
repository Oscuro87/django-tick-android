package org.ec.androidticket.backend.models.internal;

public class UserData
{
    private static UserData instance;

    private String sessionid;
    private boolean loggedIn;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isStaff;

    private UserData() {}

    public static UserData get()
    {
        if (instance == null)
            instance = new UserData();
        return instance;
    }

    public void purge()
    {
        this.sessionid = "";
        this.loggedIn = false;
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.isStaff = false;
    }

    public String getSessionid()
    {
        return sessionid;
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

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public boolean isStaff()
    {
        return isStaff;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setStaff(boolean isStaff)
    {
        this.isStaff = isStaff;
    }

    public void setLoggedIn(boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public void setSessionid(String sessionid)
    {
        this.sessionid = sessionid;
    }
}
