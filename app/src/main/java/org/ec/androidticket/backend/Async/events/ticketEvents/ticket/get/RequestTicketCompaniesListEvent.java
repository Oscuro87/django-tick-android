package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.get;

public class RequestTicketCompaniesListEvent
{
    private final String authtoken;
    private final String ticketCode;

    public RequestTicketCompaniesListEvent(String authtoken, String ticketCode)
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
