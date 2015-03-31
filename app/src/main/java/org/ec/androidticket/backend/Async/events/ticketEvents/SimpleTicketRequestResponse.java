package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.SimpleTicket;

import java.util.List;

public class SimpleTicketRequestResponse
{
    private final List<SimpleTicket> tickets;

    public SimpleTicketRequestResponse(List<SimpleTicket> tickets)
    {
        this.tickets = tickets;
    }

    public List<SimpleTicket> getTickets()
    {
        return tickets;
    }
}
