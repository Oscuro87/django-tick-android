package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

public class RequestUserBuildingsEvent
{
    private final String authtoken;

    public RequestUserBuildingsEvent(String authtoken)
    {
        this.authtoken = authtoken;
    }

    public String getAuthtoken()
    {
        return "Token " + authtoken;
    }
}
