package org.ec.androidticket.activities.createTicket;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;
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
    public String floor;
    public String office;
    public String description;
    // public Image ticketPicture; // TODO intégration image dans ticket

    public TicketCreationHolder()
    {
    }

    public TicketCreation buildTicket()
    {
        TicketCreation t = new TicketCreation();

        t.category = categorie;
        t.building = building;
        t.floor = floor;
        t.office = office;
        t.description = description;

        return t;
    }
}
