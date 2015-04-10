package org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Category;

import java.util.ArrayList;
import java.util.List;

public class RequestCategoriesResponseEvent
{
    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    @SerializedName("subcategories")
    @Expose
    private List<Category> subcategories;

    public RequestCategoriesResponseEvent()
    {
        categories = new ArrayList<>();
        subcategories = new ArrayList<>();
    }

    public RequestCategoriesResponseEvent(List<Category> categories, List<Category> subcategories)
    {
        this.categories = categories;
        this.subcategories = subcategories;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public List<Category> getSubcategories()
    {
        return subcategories;
    }
}
