package org.ec.androidticket.backend.Async.events.ticketEvents.ticket;

import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;

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
