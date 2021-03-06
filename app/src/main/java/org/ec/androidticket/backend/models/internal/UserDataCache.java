package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.Async.events.loginEvents.LoginSuccessEvent;
import org.ec.androidticket.backend.models.ticketing.User;

public class UserDataCache
{
    private static UserDataCache instance;

    private int userID;
    private String authtoken;
    private boolean loggedIn;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isStaff;
    private boolean isActive;

    private UserDataCache()
    {
    }

    public static UserDataCache get()
    {
        if (instance == null)
            instance = new UserDataCache();
        return instance;
    }

    public void purge()
    {
        this.userID = -1;
        this.authtoken = "";
        this.loggedIn = false;
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.isStaff = false;
    }

    public void applyLoginSuccessEvent(LoginSuccessEvent event)
    {
        setAuthtoken(event.authtoken);
        setLoggedIn(true);
        setUserID(event.pk);
        setStaff(event.is_staff);
        setActive(event.is_active);
        setEmail(event.email);
        setFirstName(event.firstName);
        setLastName(event.lastName);
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public int getUserID()
    {
        return userID;
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

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public boolean isStaff()
    {
        return isStaff;
    }

    public boolean isActive()
    {
        return isActive;
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

    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public void setLoggedIn(boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public void setAuthtoken(String authtoken)
    {
        this.authtoken = authtoken;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public User buildUserFromCache()
    {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setIsActive(isActive);
        user.setIsStaff(isStaff());
        user.setPk(userID);
        return user;
    }
}
