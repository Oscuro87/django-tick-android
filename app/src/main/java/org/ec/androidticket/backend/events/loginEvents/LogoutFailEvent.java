package org.ec.androidticket.backend.events.loginEvents;

public class LogoutFailEvent
{
    public final boolean disconnected;
    public final String reason;

    public LogoutFailEvent(String reason)
    {
        this.disconnected = false;
        this.reason = reason;
    }
}
