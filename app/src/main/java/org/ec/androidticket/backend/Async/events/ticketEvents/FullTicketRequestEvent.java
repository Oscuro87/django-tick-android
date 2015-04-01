package org.ec.androidticket.backend.Async.events.ticketEvents;

public class FullTicketRequestEvent
{
    private final String authToken;
    private final Integer primaryKey;

    public FullTicketRequestEvent(String authToken, Integer primaryKey)
    {
        this.authToken = authToken;
        this.primaryKey = primaryKey;
    }

    public Integer getPrimaryKey()
    {
        return primaryKey;
    }

    public String getAuthToken()
    {
        return authToken;
    }
}
