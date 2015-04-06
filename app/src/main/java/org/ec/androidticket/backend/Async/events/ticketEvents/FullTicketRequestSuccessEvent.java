package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.FullTicket;

public class FullTicketRequestSuccessEvent
{
    private final FullTicket ticket;

    public FullTicketRequestSuccessEvent(FullTicket ticket)
    {
        this.ticket = ticket;
    }

    public FullTicket getTicket()
    {
        return ticket;
    }
}