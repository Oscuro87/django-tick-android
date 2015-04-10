package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

public class RequestCategoriesEvent
{
    private final String authtoken;

    public RequestCategoriesEvent(String authtoken)
    {
        this.authtoken = "Token " + authtoken;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }
}
