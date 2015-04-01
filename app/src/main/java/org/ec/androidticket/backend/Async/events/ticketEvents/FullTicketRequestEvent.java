package org.ec.androidticket.backend.Async.events.ticketEvents;

public class FullTicketRequestEvent
{
    private final Integer primaryKey;

    public FullTicketRequestEvent(Integer primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    public Integer getPrimaryKey()
    {
        return primaryKey;
    }
}
