package org.ec.androidticket.backend.Async.events.loginEvents;

public class LoginFailureEvent
{
    public final boolean success;
    public final String reason;

    public LoginFailureEvent(String reason)
    {
        this.success = false;
        this.reason = reason;
    }
}
