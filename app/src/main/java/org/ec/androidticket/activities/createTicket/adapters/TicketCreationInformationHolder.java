package org.ec.androidticket.activities.createTicket.adapters;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.variants.TicketCreation;

public class TicketCreationInformationHolder
{
    public Category category;
    public Building building;
    public String floor;
    public String office;
    public String description;
    // public Image ticketImage;

    public TicketCreationInformationHolder()
    {
    }

    public TicketCreation buildNewTicket()
    {
        TicketCreation result = new TicketCreation();
        result.description = description;
        result.category = category;
        result.building = building;
        result.floor = floor;
        result.office = office;
        // result.ticketImage = ticketImage;
        return result;
    }
}
