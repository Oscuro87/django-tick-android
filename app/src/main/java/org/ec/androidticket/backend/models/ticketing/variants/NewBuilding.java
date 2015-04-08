package org.ec.androidticket.backend.models.ticketing.variants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Utilisé lors de la création d'un bâtiment.
 */
public class NewBuilding
{
    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("vicinity")
    @Expose
    public String vicinity;

    @SerializedName("postcode")
    @Expose
    public String postcode;

    @SerializedName("building_name")
    @Expose
    public String buildingName;
}
