package org.ec.androidticket.activities.createTicket.adapters;

import android.media.Image;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;

public class TicketCreationInformationHolder
{
    public Category category;
    public Category subcategory;
    public Building building;
    public String floor;
    public String office;
    public String description;
    // public Image ticketImage;
}
