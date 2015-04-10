package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category
{
    @SerializedName("id")
    @Expose
    private int primaryKey;
    @SerializedName("fk_parent_category")
    @Expose
    private Category parentCategory;
    @SerializedName("fk_priority")
    @Expose
    private Priority priority;
    @SerializedName("label")
    @Expose
    private String label;

    public Category()
    {
    }

    public Category(Integer primaryKey, Category parentCategory, Priority priority, String label)
    {
        this.primaryKey = primaryKey;
        this.parentCategory = parentCategory;
        this.priority = priority;
        this.label = label;
    }

    public int getPrimaryKey()
    {
        return primaryKey;
    }

    public Category getParentCategory()
    {
        return parentCategory;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString()
    {
        return label;
    }
}
