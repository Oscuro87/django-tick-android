package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestResponseEvent;
import org.ec.androidticket.backend.Async.responses.SimpleTicketResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TicketService
{
    private static TicketService instance = null;
    private Bus bus;

    private TicketService()
    {
        this.bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        bus.register(this);
    }

    public static TicketService get()
    {
        if(instance == null)
            instance = new TicketService();
        return instance;
    }

    @Subscribe
    public void onSimpleTicketsRequest(SimpleTicketRequestEvent event)
    {
        RESTClient.getTicketAPI().requestSimpleTickets(
                event.getAuthorizationToken(),
                event.getTicketType(),
                new Callback<SimpleTicketResponse.Tickets>()
                {
                    @Override
                    public void success(SimpleTicketResponse.Tickets tickets, Response response)
                    {
                        bus.post(new SimpleTicketRequestResponseEvent(tickets.getTickets()));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Error with Retrofit: " + error.getMessage());
                    }
                }
        );
    }
}
