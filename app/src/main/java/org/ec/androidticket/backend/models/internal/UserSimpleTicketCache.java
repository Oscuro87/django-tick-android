package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.Ticket;

import java.util.ArrayList;
import java.util.List;

public class UserSimpleTicketCache
{
    private static UserSimpleTicketCache instance = null;
    private List<Ticket> cache;

    private UserSimpleTicketCache()
    {
        cache = new ArrayList<>();
    }

    public static UserSimpleTicketCache getInstance()
    {
        if(instance == null)
            instance = new UserSimpleTicketCache();
        return instance;
    }

    public void purge()
    {
        cache.clear();
    }

    public void putTicketInCache(Ticket object)
    {
        cache.add(object);
    }

    public List<Ticket> getCache()
    {
        return cache;
    }

    @Override
    public String toString()
    {
        StringBuilder bld = new StringBuilder();
        for (Ticket t : cache)
        {
            bld.append(t.toString()).append("\n");
        }
        return bld.toString();
    }
}
