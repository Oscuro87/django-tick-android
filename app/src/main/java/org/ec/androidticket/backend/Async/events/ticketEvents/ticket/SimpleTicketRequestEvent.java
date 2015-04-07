package org.ec.androidticket.backend.Async.events.ticketEvents.ticket;

public class SimpleTicketRequestEvent
{
    private final String authorizationToken;
    private final String ticketType = "all";

    public SimpleTicketRequestEvent(String authorizationToken)
    {
        this.authorizationToken = "Token " + authorizationToken;
    }

    public String getAuthorizationToken()
    {
        return authorizationToken;
    }

    public String getTicketType()
    {
        return ticketType;
    }
}
