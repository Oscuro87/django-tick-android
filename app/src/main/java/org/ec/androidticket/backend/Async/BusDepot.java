package org.ec.androidticket.backend.Async;

import com.squareup.otto.Bus;

import java.util.HashMap;
import java.util.Map;

public class BusDepot
{
    public enum BusType
    {
        LOGIN,
        TICKET,
    }

    private static Map<BusType, Bus> depot;
    private static BusDepot instance;

    private BusDepot()
    {
    }

    public static BusDepot get()
    {
        if(instance == null)
            instance = new BusDepot();
        return instance;
    }

    public Bus getBus(BusType type)
    {
        if(depot == null)
            depot = new HashMap<>();

        if(depot.containsKey(type))
            return depot.get(type);
        else
        {
            Bus b = new Bus();
            depot.put(type, b);
            return b;
        }
    }
}
