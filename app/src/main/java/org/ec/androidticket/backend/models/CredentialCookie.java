package org.ec.androidticket.backend.models;

public class CredentialCookie
{
    private final String email;
    private final String password;

    public CredentialCookie(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }
}
