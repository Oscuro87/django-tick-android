package org.ec.androidticket.backend.busEvents;

public class LogoutEvent
{
    private final String sessionID;

    public LogoutEvent(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public String getSessionID()
    {
        return sessionID;
    }
}
