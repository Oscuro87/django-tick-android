package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.SimpleTicket;

import java.util.List;

public class UserSimpleTicketCache
{
    private static UserSimpleTicketCache instance = null;
    private List<SimpleTicket> cache;

    private UserSimpleTicketCache()
    {

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
}
