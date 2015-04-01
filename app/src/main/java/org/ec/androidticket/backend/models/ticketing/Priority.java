package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;

public class Priority
{
    @Expose
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
