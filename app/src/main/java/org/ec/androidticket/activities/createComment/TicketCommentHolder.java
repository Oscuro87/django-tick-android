package org.ec.androidticket.activities.createComment;

import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.Comment;

import java.util.Date;

public class TicketCommentHolder
{
    public final int commenterID;
    public final int associatedTicketID;
    public Date dateCreated;
    public String comment;

    public TicketCommentHolder(int associatedTicketID)
    {
        this.commenterID = UserDataCache.get().getUserID();
        this.associatedTicketID = associatedTicketID;
    }

    public void setDateToNow()
    {
        this.dateCreated = new Date(); // = maintenant
    }

    public Comment buildComment()
    {
        Comment output = new Comment();
        output.setComment(comment);
        output.setDateCreated(dateCreated);
        output.setTicketID(associatedTicketID);
        output.setCommenterID(commenterID);
        return output;
    }
}
