package org.ec.androidticket.backend.Async.events.ticketEvents;

public class HistoryRequestEvent
{
    private final String authtoken;
    private final String ticketCode;

    public HistoryRequestEvent(String authtoken, String ticketCode)
    {
        this.authtoken = authtoken;
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
