package org.ec.androidticket.backend.Async.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponseEvent
{
    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("reason")
    @Expose
    public String reason;

    public PostResponseEvent(boolean success, String reason)
    {
        this.success = success;
        this.reason = reason;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public String getReason()
    {
        return reason;
    }
}
