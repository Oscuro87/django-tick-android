package org.ec.androidticket.backend.Async.responses.simpleTicket;

import com.google.gson.annotations.Expose;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicketResponse
{
    public class TicketsResponse
    {
        private int cod;
        private String base;
        private Tickets tickets;
    }

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
}
