package org.ec.androidticket.backend.events.loginEvents;

public class LogoutEvent
{
    private final String authtoken;

    public LogoutEvent(String authtoken)
    {
        this.authtoken = authtoken;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }
}
