package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.List;

public class SimpleTicketRequestResponseEvent
{
    private List<Ticket> tickets;

    public SimpleTicketRequestResponseEvent(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    @Override
    public String toString()
    {
        // TODO: remove when done debugging
        String ans = "";
        for (Ticket ticket : tickets)
        {
            ans += ticket.toString();
        }

        return ans;
    }
}
