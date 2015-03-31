package org.ec.androidticket.backend.Async.responses.simpleTicket.helpers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleTicket
{
    @Expose
    private Integer pk;
    @SerializedName("ticket_code")
    @Expose
    private String ticketCode;
    @SerializedName("fk_reporter")
    @Expose
    private Reporter reporter;
    @SerializedName("fk_manager")
    @Expose
    private Object fkManager;
    @SerializedName("fk_status")
    @Expose
    private TicketStatus ticketStatus;

    /**
     * @return The pk
     */
    public Integer getPk()
    {
        return pk;
    }

    /**
     * @param pk The pk
     */
    public void setPk(Integer pk)
    {
        this.pk = pk;
    }

    /**
     * @return The ticketCode
     */
    public String getTicketCode()
    {
        return ticketCode;
    }

    /**
     * @param ticketCode The ticket_code
     */
    public void setTicketCode(String ticketCode)
    {
        this.ticketCode = ticketCode;
    }

    /**
     * @return The fkReporter
     */
    public Reporter getReporter()
    {
        return reporter;
    }

    /**
     * @param reporter The fk_reporter
     */
    public void setReporter(Reporter reporter)
    {
        this.reporter = reporter;
    }

    /**
     * @return The fkManager
     */
    public Object getFkManager()
    {
        return fkManager;
    }

    /**
     * @param fkManager The fk_manager
     */
    public void setFkManager(Object fkManager)
    {
        this.fkManager = fkManager;
    }

    /**
     * @return The fkStatus
     */
    public TicketStatus getTicketStatus()
    {
        return ticketStatus;
    }

    /**
     * @param ticketStatus The fk_status
     */
    public void setTicketStatus(TicketStatus ticketStatus)
    {
        this.ticketStatus = ticketStatus;
    }

}
