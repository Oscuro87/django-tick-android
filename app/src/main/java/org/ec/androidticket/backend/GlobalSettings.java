package org.ec.androidticket.backend;

public class GlobalSettings
{
    public static final String WEBSERVICE_ADDRESS       = "http://192.168.1.50:80";
    public static final String CREDENTIAL_COOKIE_FILENAME   = "cookie";

    public static String getWebserviceAddress()
    {
        return WEBSERVICE_ADDRESS;
    }

    public static String getCredentialCookieFilename()
    {
        return CREDENTIAL_COOKIE_FILENAME;
    }
}
