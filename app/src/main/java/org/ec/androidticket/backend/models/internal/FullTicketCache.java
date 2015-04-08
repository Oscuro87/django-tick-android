package org.ec.androidticket.backend.models.internal;

import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;
import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;

import java.util.List;

/**
 * Cache pour les informations complètes d'un ticket.
 * Séparé en trois parties pour un chargement progressif des données.
 * - Infos ticket
 * - Commentaires (max 10 derniers commentaires!)
 * - Historique ticket (complet)
 */
public class FullTicketCache
{
    private static FullTicketCache inst;
    private FullTicket ticketCache;
    private List<CommentDiet> commentCache;
    private List<HistoryDiet> historyCache;

    private FullTicketCache()
    {
    }

    public static FullTicketCache get()
    {
        if(inst == null)
            inst = new FullTicketCache();
        return inst;
    }

    public void purgeCache()
    {
        ticketCache = null;
        commentCache = null;
        historyCache = null;
    }

    public FullTicket getTicketCache()
    {
        return ticketCache;
    }

    public List<CommentDiet> getCommentCache()
    {
        return commentCache;
    }

    public void addPostedCommentToCache(CommentDiet comment)
    {

    }

    public List<HistoryDiet> getHistoryCache()
    {
        return historyCache;
    }

    public void setTicketCache(FullTicket ticketCache)
    {
        this.ticketCache = ticketCache;
    }

    public void setCommentCache(List<CommentDiet> comments)
    {
        this.commentCache = comments;
    }

    public void setHistoryCache(List<HistoryDiet> histories)
    {
        this.historyCache = histories;
    }
}
