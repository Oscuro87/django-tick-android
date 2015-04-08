package org.ec.androidticket.backend.Async.events.ticketEvents.comment;

import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;

import java.util.List;

public class CommentRequestSuccessEvent
{
    private final List<CommentDiet> comments;

    public CommentRequestSuccessEvent(List<CommentDiet> comments)
    {
        this.comments = comments;
    }

    public List<CommentDiet> getComments()
    {
        return comments;
    }
}
