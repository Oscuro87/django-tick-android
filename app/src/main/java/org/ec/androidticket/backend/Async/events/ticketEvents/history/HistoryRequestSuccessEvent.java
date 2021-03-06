package org.ec.androidticket.backend.Async.events.ticketEvents.history;

import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;

import java.util.List;

public class HistoryRequestSuccessEvent
{
    private final List<HistoryDiet> history;

    public HistoryRequestSuccessEvent(List<HistoryDiet> history)
    {
        this.history = history;
    }

    public List<HistoryDiet> getHistory()
    {
        return history;
    }
}
