package org.ec.androidticket.backend.models.ticketing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("pk")
    @Expose
    private Integer userID;
    @SerializedName("fk_company")
    @Expose
    private Company linkedCompany;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;

    public Integer getPk() {
        return userID;
    }

    public void setPk(Integer newUserID) {
        this.userID = newUserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Company getLinkedCompany()
    {
        return linkedCompany;
    }

    public void setLinkedCompany(Company linkedCompany)
    {
        this.linkedCompany = linkedCompany;
    }

    public boolean isUserCompany()
    {
        return this.linkedCompany != null;
    }
}
