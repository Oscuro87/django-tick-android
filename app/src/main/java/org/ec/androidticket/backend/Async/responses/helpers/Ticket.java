package org.ec.androidticket.backend.Async.responses.helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket
{
    @SerializedName("pk")
    @Expose
    private Integer pk;
    @SerializedName("ticket_code")
    @Expose
    private String ticketCode;
    @SerializedName("fk_reporter")
    @Expose
    private User reporter;
    @SerializedName("fk_manager")
    @Expose
    private User manager;
    @SerializedName("fk_status")
    @Expose
    private TicketStatus ticketStatus;

    public Integer getPk()
    {
        return pk;
    }

    public void setPk(Integer pk)
    {
        this.pk = pk;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode)
    {
        this.ticketCode = ticketCode;
    }

    public User getReporter()
    {
        return reporter;
    }

    public void setReporter(User reporter)
    {
        this.reporter = reporter;
    }

    public User getManager()
    {
        return manager;
    }

    public void setManager(User manager)
    {
        this.manager = manager;
    }

    public TicketStatus getTicketStatus()
    {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus)
    {
        this.ticketStatus = ticketStatus;
    }

    @Override
    public String toString()
    {
        return "Ticket{" +
                "pk=" + pk +
                ", ticketCode='" + ticketCode + '\'' +
                ", reporter=" + reporter +
                ", manager=" + manager +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
