package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CommentDiet
{
    @SerializedName("comment")
    @Expose
    private String commentText;

    @SerializedName("date_created")
    @Expose
    private Date dateCreated;

    @SerializedName("commenter_name")
    @Expose
    private String commenterName;

    public CommentDiet(String commentText, Date dateCreated, String commenterName)
    {
        this.commentText = commentText;
        this.dateCreated = dateCreated;
        this.commenterName = commenterName;
    }

    public String getCommentText()
    {
        return commentText;
    }

    public void setCommentText(String commentText)
    {
        this.commentText = commentText;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getCommenterName()
    {
        return commenterName;
    }

    public void setCommenterName(String commenterName)
    {
        this.commenterName = commenterName;
    }
}
