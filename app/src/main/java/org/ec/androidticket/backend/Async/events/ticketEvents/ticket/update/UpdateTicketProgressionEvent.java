package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

public class UpdateTicketProgressionEvent
{
    private final String authtoken;
    private final String ticketCode;

    public UpdateTicketProgressionEvent(String authtoken, String ticketCode)
    {
        this.authtoken = "Token " + authtoken;
        this.ticketCode = ticketCode;
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }
}
