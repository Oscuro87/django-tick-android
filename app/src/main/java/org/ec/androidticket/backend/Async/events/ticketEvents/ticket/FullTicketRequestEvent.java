package org.ec.androidticket.backend.Async.events.ticketEvents.ticket;

public class FullTicketRequestEvent
{
    private final String authToken;
    private final String ticketCode;

    public FullTicketRequestEvent(String authToken, String ticketCode)
    {
        this.authToken = authToken;
        this.ticketCode = ticketCode;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }

    public String getAuthToken()
    {
        return authToken;
    }
}
