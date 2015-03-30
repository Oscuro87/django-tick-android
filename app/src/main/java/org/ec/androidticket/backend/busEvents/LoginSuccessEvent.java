package org.ec.androidticket.backend.busEvents;

public class LoginSuccessEvent
{
    public final String sessionID;
    public final boolean success;
    public final String reason;
    public final String firstName;
    public final String lastName;
    public final String email;
    public final boolean staff;

    public LoginSuccessEvent(String sessionID, boolean success, String reason, String firstName, String lastName, String email, boolean staff)
    {
        this.sessionID = sessionID;
        this.success = success;
        this.reason = reason;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.staff = staff;
    }
}
