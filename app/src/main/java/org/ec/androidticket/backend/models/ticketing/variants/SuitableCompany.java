package org.ec.androidticket.backend.models.ticketing.variants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuitableCompany
{
    @SerializedName("pk")
    @Expose
    public int pk;

    @SerializedName("name")
    @Expose
    public String companyName;

    @SerializedName("distance")
    @Expose
    public float distanceFromUser;

    public SuitableCompany()
    {
    }

    public SuitableCompany(int pk, String companyName, float distanceFromUser)
    {
        this.pk = pk;
        this.companyName = companyName;
        this.distanceFromUser = distanceFromUser;
    }

    public int getPk()
    {
        return pk;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public float getDistanceFromUser()
    {
        return distanceFromUser;
    }

    @Override
    public String toString()
    {
        if (this.companyName.equals(""))
            return "Unassign";

        final StringBuilder sb = new StringBuilder();
        sb.append(this.companyName).append(" (dist. ").append(distanceFromUser).append("km)");
        return sb.toString();
    }
}
