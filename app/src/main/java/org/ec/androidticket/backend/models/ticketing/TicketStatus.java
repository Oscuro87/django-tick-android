package org.ec.androidticket.backend.models.ticketing;

public class TicketStatus
{
    private final int pk;
    private final String statusName;

    public TicketStatus(int pk, String statusName)
    {
        this.pk = pk;
        this.statusName = statusName;
    }

    public int getPk()
    {
        return pk;
    }

    public String getStatusName()
    {
        return statusName;
    }
}
