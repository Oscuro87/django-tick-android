package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.TicketStatus;

public class UpdateTicketProgressionResponseEvent
{
    @SerializedName("new_ticket_status")
    @Expose
    public TicketStatus newTicketStatus;

    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("reason")
    @Expose
    public String reason;

    public UpdateTicketProgressionResponseEvent()
    {
    }

    public UpdateTicketProgressionResponseEvent(boolean success, String reason)
    {
        this.newTicketStatus = null;
        this.success = success;
        this.reason = reason;
    }

    public TicketStatus getNewTicketStatus()
    {
        return newTicketStatus;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public String getReason()
    {
        return reason;
    }

    public void setNewTicketStatus(TicketStatus newTicketStatus)
    {
        this.newTicketStatus = newTicketStatus;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
