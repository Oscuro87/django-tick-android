package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.FullTicket;

public class FullTicketSuccessResponseEvent
{
    private final FullTicket ticket;

    public FullTicketSuccessResponseEvent(FullTicket ticket)
    {
        this.ticket = ticket;
    }

    public FullTicket getTicket()
    {
        return ticket;
    }
}
