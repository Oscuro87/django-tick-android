package org.ec.androidticket.backend;

public class GlobalSettings
{
    public static final String DEVELOPMENT_WEBSERVICE_ADDRESS       = "http://192.168.1.50:2337/";
    public static final String PRODUCTION_WEBSERVICE_ADDRESS       = "http://ticketplatform.no-ip.org:2337/";
    public static final String CREDENTIAL_COOKIE_FILENAME   = "cookie";

    public static String getProductionWebserviceAddress()
    {
        return PRODUCTION_WEBSERVICE_ADDRESS;
    }

    public static String getDevelopmentWebserviceAddress()
    {
        return DEVELOPMENT_WEBSERVICE_ADDRESS;
    }

    public static String getCredentialCookieFilename()
    {
        return CREDENTIAL_COOKIE_FILENAME;
    }
}
