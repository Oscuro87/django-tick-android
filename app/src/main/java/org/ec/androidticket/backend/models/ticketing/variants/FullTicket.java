package org.ec.androidticket.backend.models.ticketing.variants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;
import org.ec.androidticket.backend.models.ticketing.Company;
import org.ec.androidticket.backend.models.ticketing.Priority;
import org.ec.androidticket.backend.models.ticketing.TicketStatus;
import org.ec.androidticket.backend.models.ticketing.User;

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
    private Category category;
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
    private User manager;
    @SerializedName("fk_company")
    @Expose
    private Company company;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ticket_code")
    @Expose
    private String ticketCode;
    @Expose
    private String floor;
    @Expose
    private String office;
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

    public Category getCategory()
    {
        return category;
    }

    public Category getSubcategory()
    {
        if (category.getParentCategory() != null)
            return category;
        return null;
    }

    public void setSubcategory(Category subcategory)
    {
        this.category = subcategory;
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

    public User getManager()
    {
        return manager;
    }

    public void setManager(User manager)
    {
        this.manager = manager;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getTicketID()
    {
        return id;
    }
}
