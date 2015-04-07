package org.ec.androidticket.backend.Async.events.ticketEvents.comment;

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
