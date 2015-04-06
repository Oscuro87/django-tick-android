package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.ErrorResponse;

public class HistoryRequestFailureEvent
{
    private String error;

    public HistoryRequestFailureEvent(String error)
    {
        this.error = error;
    }

    public String getError()
    {
        return error;
    }
}
