package org.ec.androidticket.backend.Async.events.loginEvents;

public class LoggedOutEvent
{
    public final boolean disconnected;

    public LoggedOutEvent(boolean disconnected)
    {
        this.disconnected = disconnected;
    }
}
