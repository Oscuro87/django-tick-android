package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Attention, cette classe représente la version SIMPLIFIEE d'un ticket.
 * Pour la classe reprenant l'intégralité des informations pour un ticket, voir FullTicket.java!
 */
public class Ticket
{
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

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
