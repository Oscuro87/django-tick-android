package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

import org.ec.androidticket.backend.models.ticketing.variants.TicketCreation;

public class TicketCreationEvent
{
    private final String authtoken;
    private final TicketCreation newTicket;

    public TicketCreationEvent(String authtoken, TicketCreation newTicket)
    {
        this.authtoken = "Token " + authtoken;
        this.newTicket = newTicket;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public TicketCreation getNewTicket()
    {
        return newTicket;
    }
}
