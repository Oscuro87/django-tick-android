package org.ec.androidticket.activities.createTicket;

import android.media.Image;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;
import org.ec.androidticket.backend.models.ticketing.Ticket;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;
import org.ec.androidticket.backend.models.ticketing.variants.TicketCreation;

/**
 * Cette classe sert à contenir les informations entrées par l'utilisateur lors de la création
 * d'un ticket.
 */
public class TicketCreationHolder
{
    public Category categorie;
    public Category subcategory;
    public Building building;
    public final Channel channel; // Le moyen de création sera toujours "Android"
    public String floor;
    public String office;
    public String description;
    // public Image ticketPicture; // TODO intégration image dans ticket

    public TicketCreationHolder()
    {
        channel = new Channel();
        channel.setLabel("Android");
    }

    public TicketCreation buildTicket()
    {
        TicketCreation t = new TicketCreation();

        t.parentCategory = categorie;
        t.subcategory = subcategory;
        t.building = building;
        t.channel = channel;
        t.floor = floor;
        t.office = office;
        t.description = description;

        return t;
    }
}
