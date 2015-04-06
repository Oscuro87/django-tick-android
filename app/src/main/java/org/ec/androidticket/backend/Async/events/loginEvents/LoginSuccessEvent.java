package org.ec.androidticket.backend.Async.events.loginEvents;

public class LoginSuccessEvent
{
    public final int pk;
    public final String authtoken;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final boolean is_staff;
    public final boolean is_active;

    public LoginSuccessEvent(int userID, String authtoken, String firstName, String lastName, String email, boolean is_staff, boolean is_active)
    {
        this.pk = userID;
        this.authtoken = authtoken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.is_staff = is_staff;
        this.is_active = is_active;
    }
}
