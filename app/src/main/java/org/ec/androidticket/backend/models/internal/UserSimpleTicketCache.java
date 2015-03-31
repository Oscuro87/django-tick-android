package org.ec.androidticket.backend.models.internal;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.ticketEvents.SimpleTicketRequestResponse;
import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.SimpleTicket;

import java.util.List;

public class UserSimpleTicketCache
{
    private static UserSimpleTicketCache instance = null;
    private List<SimpleTicket> cache;
    private Bus bus;

    private UserSimpleTicketCache()
    {
        bus = BusDepot.get().getBus(BusDepot.BusType.TICKET);
        bus.register(this);
    }

    public static UserSimpleTicketCache getInstance()
    {
        if(instance == null)
            instance = new UserSimpleTicketCache();
        return instance;
    }

    public void putTicketInCache(SimpleTicket object)
    {
        cache.add(object);
    }

    public List<SimpleTicket> getCache()
    {
        return cache;
    }

    @Subscribe
    public void onSimpleTicketRequestResponse(SimpleTicketRequestResponse event)
    {
        Log.i("CustomLog", "Cached the tickets informations!");
        this.cache = event.getTickets();
        for (SimpleTicket t : cache)
        {
            t.toString();
        }
    }
}
