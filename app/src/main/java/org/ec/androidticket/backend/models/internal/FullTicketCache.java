package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.models.ticketing.FullTicket;

/**
 * Cache pour les informations complètes d'un ticket.
 * Séparé en trois parties pour un chargement progressif des données.
 * - Infos ticket
 * - Commentaires (max 10 derniers commentaires!)
 * - Historique ticket (complet)
 */
public class FullTicketCache
{
    private FullTicket ticketInformations;
    // TODO private List<TicketComment> comments;
    // TODO private List<TicketHistory> historique;

    public FullTicketCache()
    {

    }
}
