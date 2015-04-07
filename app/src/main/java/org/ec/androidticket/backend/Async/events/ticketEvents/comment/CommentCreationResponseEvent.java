package org.ec.androidticket.backend.Async.events.ticketEvents.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentCreationResponseEvent
{
    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("reason")
    @Expose
    public String reason;

    public CommentCreationResponseEvent(boolean success, String reason)
    {
        this.success = success;
        this.reason = reason;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
