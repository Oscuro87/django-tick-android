package org.ec.androidticket.backend.models.ticketing;

/**
 * Cette classe servira uniquement à stocker le code ticket (unique) pour l'activité "home", afin
 * de ne pas charger l'entièreté de tous les tickets de l'utilisateur à chaque venue sur l'activité "home".
 */
public class SimpleTicket
{
    // Le code ticket unique, pour l'identifier
    private final String codeTicket;
    // Le nom du status ticket, pour assigner une couleur diff. aux icônes sur la vue
    private final TicketStatus ticketStatus;

    public SimpleTicket(String codeTicket, int ticketStatusPK, String ticketStatusName)
    {
        this.codeTicket = codeTicket;
        this.ticketStatus = new TicketStatus(ticketStatusPK, ticketStatusName);
    }

    public String getCodeTicket()
    {
        return codeTicket;
    }

    public TicketStatus getTicketStatus()
    {
        return ticketStatus;
    }
}
