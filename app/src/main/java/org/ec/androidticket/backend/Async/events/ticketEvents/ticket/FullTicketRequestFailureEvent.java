package org.ec.androidticket.backend.Async.events.ticketEvents.ticket;

/**
 * Event représentant un problème lors de la réponse rendue par le serveur.
 */
public class FullTicketRequestFailureEvent
{
    private final String reason;

    public FullTicketRequestFailureEvent(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
}
