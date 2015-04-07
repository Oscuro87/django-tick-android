package org.ec.androidticket.backend.Async.events.ticketEvents.comment;

import org.ec.androidticket.backend.models.ticketing.Comment;

import java.util.Date;

public class CommentCreationEvent
{
    private final String authtoken;
    private final Comment comment;

    public CommentCreationEvent(String authtoken, Comment comment)
    {
        this.authtoken = "Token " + authtoken;
        this.comment = comment;
    }

    public CommentCreationEvent(String authtoken, int ticketID, int userID, Date dateCreated, String commentBody)
    {
        this.authtoken = "Token " + authtoken;
        comment = new Comment();
        comment.setTicketID(ticketID);
        comment.setCommenterID(userID);
        comment.setDateCreated(dateCreated);
        comment.setComment(commentBody);
    }

    public String getAuthtoken()
    {
        return authtoken;
    }

    public Comment getComment()
    {
        return comment;
    }
}
