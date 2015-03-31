package org.ec.androidticket.backend.Async.responses.simpleTicket;

import com.google.gson.annotations.Expose;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.SimpleTicket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicketResponse
{
    public class TicketsResponse
    {
        private int cod;
        private String base;
        private TicketsData ticketsData;
    }

    public class TicketsData
    {
        @Expose
        private List<SimpleTicket> simpleTickets = new ArrayList<SimpleTicket>();
        @Expose
        private Boolean success;

        /**
         * @return The tickets
         */
        public List<SimpleTicket> getSimpleTickets()
        {
            return simpleTickets;
        }

        /**
         * @param simpleTickets The tickets
         */
        public void setSimpleTickets(List<SimpleTicket> simpleTickets)
        {
            this.simpleTickets = simpleTickets;
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
