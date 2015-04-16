package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;

public class UpdateTicketDetailEvent
{
    private final String authtoken;
    private final FullTicket fullTicket;

    public UpdateTicketDetailEvent(String authtoken, FullTicket fullTicket)
    {
        this.authtoken = "Token " + authtoken;
        this.fullTicket = fullTicket;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public FullTicket getFullTicket()
    {
        return fullTicket;
    }
}
