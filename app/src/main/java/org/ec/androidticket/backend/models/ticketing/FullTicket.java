package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("UnusedDeclaration")
public class FullTicket
{
    @SerializedName("fk_building")
    @Expose
    private Building building;
    @SerializedName("fk_channel")
    @Expose
    private Channel channel;
    @SerializedName("fk_category")
    @Expose
    private Category parentCategory;
    @SerializedName("fk_reporter")
    @Expose
    private User reporter;
    @SerializedName("fk_priority")
    @Expose
    private Priority priority;
    @SerializedName("fk_status")
    @Expose
    private TicketStatus status;
    @SerializedName("fk_manager")
    @Expose
    private Object manager;
    @SerializedName("fk_company")
    @Expose
    private Object company;
    @SerializedName("ticket_code")
    @Expose
    private String ticketCode;
    @Expose
    private String floor;
    @Expose
    private String office;
    @SerializedName("intervention_date")
    @Expose
    private Object interventionDate;
    @Expose
    private String description;

    public Building getBuilding()
    {
        return building;
    }

    public void setBuilding(Building building)
    {
        this.building = building;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public void setChannel(Channel channel)
    {
        this.channel = channel;
    }

    public Category getParentCategory()
    {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory)
    {
        this.parentCategory = parentCategory;
    }

    public User getReporter()
    {
        return reporter;
    }

    public void setReporter(User reporter)
    {
        this.reporter = reporter;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

    public TicketStatus getStatus()
    {
        return status;
    }

    public void setStatus(TicketStatus status)
    {
        this.status = status;
    }

    public Object getManager()
    {
        return manager;
    }

    public void setManager(Object manager)
    {
        this.manager = manager;
    }

    public Object getCompany()
    {
        return company;
    }

    public void setCompany(Object company)
    {
        this.company = company;
    }

    public String getTicketCode()
    {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode)
    {
        this.ticketCode = ticketCode;
    }

    public String getFloor()
    {
        return floor;
    }

    public void setFloor(String floor)
    {
        this.floor = floor;
    }

    public String getOffice()
    {
        return office;
    }

    public void setOffice(String office)
    {
        this.office = office;
    }

    public Object getInterventionDate()
    {
        return interventionDate;
    }

    public void setInterventionDate(Object interventionDate)
    {
        this.interventionDate = interventionDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
