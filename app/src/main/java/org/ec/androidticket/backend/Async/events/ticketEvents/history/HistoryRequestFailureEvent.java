package org.ec.androidticket.backend.Async.events.ticketEvents.history;

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
