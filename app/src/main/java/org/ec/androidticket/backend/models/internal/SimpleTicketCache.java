package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicketCache
{
    public enum BrowsingMode
    {
        OWN_TICKETS,
        MANAGED_TICKETS,
        UNMANAGED_TICKETS,
    }

    private static SimpleTicketCache instance = null;

    private BrowsingMode mode = BrowsingMode.OWN_TICKETS;

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
        unmanagedCache.clear();
        managedByUserCache.clear();
    }

    public void putUserTicket(Ticket ticket)
    {
        if(ticket.getReporter().getEmail().equals(UserDataCache.get().getEmail()))
            cache.add(ticket);
    }

    public void putUnmanagedTicket(Ticket ticket)
    {
        if(ticket.getManager() == null)
            unmanagedCache.add(ticket);
    }

    public void putManagedTicket(Ticket ticket)
    {
        if(ticket.getManager().getEmail().equals(UserDataCache.get().getEmail()))
            managedByUserCache.add(ticket);
    }

    public List<Ticket> getCache()
    {
        switch(this.mode)
        {
            case OWN_TICKETS:
                return cache;
            case MANAGED_TICKETS:
                return managedByUserCache;
            case UNMANAGED_TICKETS:
                return unmanagedCache;
            default:
                return cache;
        }
    }

    public void setBrowsingMode(BrowsingMode mode)
    {
        this.mode = mode;
    }
}
