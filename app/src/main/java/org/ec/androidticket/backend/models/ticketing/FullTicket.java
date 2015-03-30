package org.ec.androidticket.backend.models.ticketing;

/**
 * Contient l'entièreté des informations ticket.
 */
public class FullTicket
{
    // TODO: classes pour les foreign keys ?
    // FK
    private final String buildingName;
    private final String channel;
    private final String subCategory;
    private final String category;
    private final String reporterName;
    private final String priorityName;
    private final String status;
    private final String managerName;
    private final String companyName;
    // Data
    private final String ticketCode;
    private final String floor;
    private final String office;
    private final String interventionDate;
    private final String description;

    public FullTicket(String buildingName, String channel, String subCategory, String category, String reporterName, String priorityName, String status, String managerName, String companyName, String ticketCode, String floor, String office, String interventionDate, String description)
    {
        this.buildingName = buildingName;
        this.channel = channel;
        this.subCategory = subCategory;
        this.category = category;
        this.reporterName = reporterName;
        this.priorityName = priorityName;
        this.status = status;
        this.managerName = managerName;
        this.companyName = companyName;
        this.ticketCode = ticketCode;
        this.floor = floor;
        this.office = office;
        this.interventionDate = interventionDate;
        this.description = description;
    }

    public String getBuildingName()
    {
        return buildingName;
    }

    public String getChannel()
    {
        return channel;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public String getCategory()
    {
        return category;
    }

    public String getReporterName()
    {
        return reporterName;
    }

    public String getPriorityName()
    {
        return priorityName;
    }

    public String getStatus()
    {
        return status;
    }

    public String getManagerName()
    {
        return managerName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }

    public String getFloor()
    {
        return floor;
    }

    public String getOffice()
    {
        return office;
    }

    public String getInterventionDate()
    {
        return interventionDate;
    }

    public String getDescription()
    {
        return description;
    }
}
