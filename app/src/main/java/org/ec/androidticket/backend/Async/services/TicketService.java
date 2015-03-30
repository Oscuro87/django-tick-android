package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.responses.simpleTicket.SimpleTicket;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TicketService
{
    private Bus bus;

    public TicketService(Bus bus)
    {
        this.bus = bus;
        this.bus.register(this);
    }

    @Subscribe
    public void onSimpleTicketsRequest(SimpleTicketRequestEvent event)
    {
        RESTClient.getTicketAPI().requestSimpleTickets(
                event.getAuthorizationToken(),
                event.getTicketType(),
                new Callback<SimpleTicket.Tickets>()
                {
                    @Override
                    public void success(SimpleTicket.Tickets tickets, Response response)
                    {
                        Log.i("CustomLog", "Retrieve tickets success? " + tickets.getSuccess());
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        // TODO
                    }
                }
        );
    }
}
