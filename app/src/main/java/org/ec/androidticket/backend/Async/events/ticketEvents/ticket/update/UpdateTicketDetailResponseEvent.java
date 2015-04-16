package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit.RetrofitError;

public class UpdateTicketDetailResponseEvent
{
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("reason")
    @Expose
    private String reason;

    private RetrofitError error;

    public UpdateTicketDetailResponseEvent()
    {
        error = null;
    }

    public UpdateTicketDetailResponseEvent(RetrofitError error)
    {
        this.error = error;
    }

    public boolean hasErrors()
    {
        return error != null;
    }

    public RetrofitError getError()
    {
        return error;
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
