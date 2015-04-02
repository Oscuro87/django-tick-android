package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicketCache
{
    private static SimpleTicketCache instance = null;
    private List<Ticket> cache;
    private List<Ticket> unmanagedCache;
    private List<Ticket> managedByUserCache;

    private SimpleTicketCache()
    {
        cache = new ArrayList<>();
        unmanagedCache = new ArrayList<>();
        managedByUserCache = new ArrayList<>();
    }

    public static SimpleTicketCache get()
    {
        if(instance == null)
            instance = new SimpleTicketCache();
        return instance;
    }

    public void purge()
    {
        cache.clear();
    }

    public void putTicketInCache(Ticket ticket)
    {
        // Cas 1: le ticket appartient à l'user
        if(ticket.getReporter().getEmail().equals(UserDataCache.get().getEmail()))
            cache.add(ticket);
        // Cas 2: le ticket est un ticket non géré et l'utilisateur est manager
        else if(ticket.getManager() == null && UserDataCache.get().isStaff())
            unmanagedCache.add(ticket);
        else if(ticket.getManager().getEmail().equals(UserDataCache.get().getEmail()))
            managedByUserCache.add(ticket);
    }

    public List<Ticket> getCache()
    {
        return cache;
    }
}
