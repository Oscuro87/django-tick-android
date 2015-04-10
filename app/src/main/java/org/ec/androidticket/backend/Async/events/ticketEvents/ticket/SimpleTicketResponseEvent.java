package org.ec.androidticket.backend.Async.events.ticketEvents.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

public class SimpleTicketResponseEvent
{
    @SerializedName("user_tickets")
    @Expose
    private List<Ticket> tickets;

    @SerializedName("managed_by_user")
    @Expose
    private List<Ticket> managedByUser;

    @SerializedName("unmanaged")
    @Expose
    private List<Ticket> unmanagedTickets;

    private RetrofitError error;

    public SimpleTicketResponseEvent()
    {
        error = null;
        tickets = new ArrayList<>();
        managedByUser = new ArrayList<>();
        unmanagedTickets = new ArrayList<>();
    }

    public SimpleTicketResponseEvent(RetrofitError error)
    {
        this.error = error;
        tickets = new ArrayList<>();
        managedByUser = new ArrayList<>();
        unmanagedTickets = new ArrayList<>();
    }

    public boolean hasErrors()
    {
        return this.error != null;
    }

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public List<Ticket> getManagedByUser()
    {
        return managedByUser;
    }

    public List<Ticket> getUnmanagedTickets()
    {
        return unmanagedTickets;
    }
}