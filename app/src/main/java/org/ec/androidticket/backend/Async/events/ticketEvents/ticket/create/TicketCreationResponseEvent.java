package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketCreationResponseEvent
{
    @SerializedName("success")
    @Expose
    public final boolean success;


    @SerializedName("reason")
    @Expose
    public final String reason;

    public TicketCreationResponseEvent(boolean success, String reason)
    {
        this.success = success;
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
