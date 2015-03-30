package org.ec.androidticket.backend.events.loginEvents;

import org.ec.androidticket.backend.models.internal.Credentials;

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
