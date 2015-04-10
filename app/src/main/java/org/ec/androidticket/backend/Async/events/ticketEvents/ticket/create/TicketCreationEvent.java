package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.variants.TicketCreation;

public class TicketCreationEvent
{
    private final String authtoken;
    @SerializedName("new_ticket")
    @Expose
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
