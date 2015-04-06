package org.ec.androidticket.backend.Async.events.ticketEvents;

import org.ec.androidticket.backend.models.ticketing.ErrorResponse;

public class CommentRequestFailureEvent
{
    private final String error;

    public CommentRequestFailureEvent(String error)
    {
        this.error = error;
    }

    public String getError()
    {
        return error;
    }
}
