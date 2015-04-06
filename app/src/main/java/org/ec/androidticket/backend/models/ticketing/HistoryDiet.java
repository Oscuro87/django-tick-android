package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HistoryDiet
{
    @SerializedName("new_status")
    @Expose
    private String newStatusName;

    @SerializedName("update_date")
    @Expose
    private Date updateDate;

    @SerializedName("update_reason")
    @Expose
    private String updateReason;

    public HistoryDiet(String newStatusName, Date updateDate, String updateReason)
    {
        this.newStatusName = newStatusName;
        this.updateDate = updateDate;
        this.updateReason = updateReason;
    }

    public String getNewStatusName()
    {
        return newStatusName;
    }

    public void setNewStatusName(String newStatusName)
    {
        this.newStatusName = newStatusName;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public String getUpdateReason()
    {
        return updateReason;
    }

    public void setUpdateReason(String updateReason)
    {
        this.updateReason = updateReason;
    }
}
