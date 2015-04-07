package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment
{
    @SerializedName("commentCreation")
    @Expose
    public final boolean commentCreation = true;

    @SerializedName("fk_ticket_id")
    @Expose
    public int ticketID;

    @SerializedName("fk_commenter_id")
    @Expose
    public int commenterID;

    @SerializedName("date_created")
    @Expose
    public Date dateCreated;

    @SerializedName("comment")
    @Expose
    public String comment;

    public int getTicketID()
    {
        return ticketID;
    }

    public void setTicketID(int ticketID)
    {
        this.ticketID = ticketID;
    }

    public int getCommenterID()
    {
        return commenterID;
    }

    public void setCommenterID(int commenterID)
    {
        this.commenterID = commenterID;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
