package org.ec.androidticket.backend.Async.events.loginEvents;

public class LoginEvent
{
    private final String email;
    private final String password;

    public LoginEvent(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
