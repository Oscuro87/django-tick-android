package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category
{
    @SerializedName("fk_parent_category")
    @Expose
    private Category parentCategory;
    @SerializedName("fk_priority")
    @Expose
    private Priority priority;
    @SerializedName("label")
    @Expose
    private String label;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
