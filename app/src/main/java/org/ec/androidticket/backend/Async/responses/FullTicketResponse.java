package org.ec.androidticket.backend.Async.responses;

import org.ec.androidticket.backend.models.ticketing.FullTicket;

public class FullTicketResponse
{
    private int code;
    private String base;
    private FullTicket ticket;

    public int getCode()
    {
        return code;
    }

    public String getBase()
    {
        return base;
    }

    public FullTicket getTicket()
    {
        return ticket;
    }
}
