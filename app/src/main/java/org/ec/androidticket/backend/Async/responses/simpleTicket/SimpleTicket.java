package org.ec.androidticket.backend.Async.responses.simpleTicket;

import com.google.gson.annotations.Expose;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicket
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
        @Expose
        private Boolean success;

        /**
         * @return The tickets
         */
        public List<Ticket> getTickets()
        {
            return tickets;
        }

        /**
         * @param tickets The tickets
         */
        public void setTickets(List<Ticket> tickets)
        {
            this.tickets = tickets;
        }

        /**
         * @return The success
         */
        public Boolean getSuccess()
        {
            return success;
        }

        /**
         * @param success The success
         */
        public void setSuccess(Boolean success)
        {
            this.success = success;
        }

    }
}
