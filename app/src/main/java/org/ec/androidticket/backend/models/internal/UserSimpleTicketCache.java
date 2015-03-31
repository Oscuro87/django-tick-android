package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.Async.responses.helpers.Ticket;

import java.util.ArrayList;
import java.util.List;

public class UserSimpleTicketCache
{
    private static UserSimpleTicketCache instance = null;
    private List<Ticket> cache;
    private List<Ticket> unmanagedCache;
    private List<Ticket> managedByUserCache;

    private UserSimpleTicketCache()
    {
        cache = new ArrayList<>();
        unmanagedCache = new ArrayList<>();
        managedByUserCache = new ArrayList<>();
    }

    public static UserSimpleTicketCache get()
    {
        if(instance == null)
            instance = new UserSimpleTicketCache();
        return instance;
    }

    public void purge()
    {
        cache.clear();
    }

    public void putTicketInCache(Ticket ticket)
    {
        // Cas 1: le ticket appartient à l'user
        if(ticket.getReporter().getEmail() == UserDataCache.get().getEmail())
            cache.add(ticket);
        // Cas 2: le ticket est un ticket non géré et l'utilisateur est manager
        else if(ticket.getManager() == null && UserDataCache.get().isStaff())
            unmanagedCache.add(ticket);
        else if(ticket.getManager().getEmail() == UserDataCache.get().getEmail())
            managedByUserCache.add(ticket);
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
