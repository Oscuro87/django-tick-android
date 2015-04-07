package org.ec.androidticket.backend.Async.events.ticketEvents.comment;

public class CommentRequestEvent
{
    private final String authtoken;
    private final String ticketCode;

    public CommentRequestEvent(String authtoken, String ticketCode)
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
