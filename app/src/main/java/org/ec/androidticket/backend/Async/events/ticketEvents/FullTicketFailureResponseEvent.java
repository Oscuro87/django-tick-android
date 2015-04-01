package org.ec.androidticket.backend.Async.events.ticketEvents;

/**
 * Event représentant un problème lors de la réponse rendue par le serveur.
 */
public class FullTicketFailureResponseEvent
{
    private final String reason;

    public FullTicketFailureResponseEvent(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
}
