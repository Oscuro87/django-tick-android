package org.ec.androidticket.activities.createTicket;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;

/**
 * Cette classe sert à contenir les informations entrées par l'utilisateur lors de la création
 * d'un ticket.
 */
public class TicketInformationHolder
{
    public Category categorie;
    public Category subcategory;
    public Building building;
    // Le moyen de création sera toujours "Android" à partir de cette app
    public final Channel channel;
    public String floor;
    public String office;
    public String description;

    public TicketInformationHolder()
    {
        channel = new Channel();
        channel.setLabel("Android");
    }
}
