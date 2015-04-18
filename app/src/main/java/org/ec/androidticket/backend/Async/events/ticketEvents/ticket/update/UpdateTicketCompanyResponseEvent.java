package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Company;

public class UpdateTicketCompanyResponseEvent
{
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("companyInstance")
    @Expose
    private Company companyInstance;

    public UpdateTicketCompanyResponseEvent()
    {
    }

    public UpdateTicketCompanyResponseEvent(boolean success, String reason)
    {
        this.companyInstance = null;
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

    public Company getCompanyInstance()
    {
        return companyInstance;
    }
}
