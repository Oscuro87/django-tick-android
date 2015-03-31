package org.ec.androidticket.backend.Async.events.loginEvents;

public class LoginSuccessEvent
{
    public final String authtoken;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final boolean staff;

    public LoginSuccessEvent(String authtoken, String firstName, String lastName, String email, boolean staff)
    {
        this.authtoken = authtoken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.staff = staff;
    }
}
