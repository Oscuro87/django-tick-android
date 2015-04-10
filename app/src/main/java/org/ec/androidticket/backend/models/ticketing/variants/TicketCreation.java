package org.ec.androidticket.backend.models.ticketing.variants;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;
import org.ec.androidticket.backend.models.ticketing.User;

public class TicketCreation
{
    @SerializedName("building")
    @Expose
    @Nullable
    public Building building;

    @SerializedName("category")
    @Expose
    @NonNull
    public Category category;

    @SerializedName("floor")
    @Expose
    @Nullable
    public String floor;

    @SerializedName("office")
    @Expose
    @Nullable
    public String office;

    @SerializedName("description")
    @Expose
    @NonNull
    public String description;
}
