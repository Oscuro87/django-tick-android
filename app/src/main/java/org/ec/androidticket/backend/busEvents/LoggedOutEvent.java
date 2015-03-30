package org.ec.androidticket.backend.busEvents;

public class LoggedOutEvent
{
    public final boolean disconnected;

    public LoggedOutEvent(boolean disconnected)
    {
        this.disconnected = disconnected;
    }
}
