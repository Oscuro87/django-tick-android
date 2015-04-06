package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.List;

public class SimpleTicketRequestSuccessEvent
{
    private List<Ticket> tickets;

    public SimpleTicketRequestSuccessEvent(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }
}