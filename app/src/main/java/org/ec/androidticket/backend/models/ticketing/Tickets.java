package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Tickets
{
    @Expose
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public List<Ticket> getTickets()
    {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }
}
