package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestResponse;
import org.ec.androidticket.backend.Async.responses.simpleTicket.SimpleTicketResponse;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TicketService
{
    private Bus bus;

    public TicketService(Bus bus)
    {
        this.bus = BusDepot.get().getBus(BusDepot.BusType.TICKET);
        this.bus.register(this);
    }

    @Subscribe
    public void onSimpleTicketsRequest(SimpleTicketRequestEvent event)
    {
        RESTClient.getTicketAPI().requestSimpleTickets(
                event.getAuthorizationToken(),
                event.getTicketType(),
                new Callback<SimpleTicketResponse.TicketsData>()
                {
                    @Override
                    public void success(SimpleTicketResponse.TicketsData ticketsData, Response response)
                    {
                        Log.i("CustomLog", "Retrieve tickets success? " + ticketsData.getSuccess());
                        bus.post(new SimpleTicketRequestResponse(ticketsData.getSimpleTickets()));
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
