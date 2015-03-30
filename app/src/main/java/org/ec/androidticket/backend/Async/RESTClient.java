package org.ec.androidticket.backend.Async;

import com.squareup.okhttp.OkHttpClient;

import org.ec.androidticket.backend.Async.apis.RestLoginAPI;
import org.ec.androidticket.backend.Async.apis.RestTicketAPI;
import org.ec.androidticket.backend.GlobalSettings;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RESTClient
{
    private static RestLoginAPI REST_LOGIN_API;
    private static RestTicketAPI REST_TICKET_API;
    private static String ROOT = GlobalSettings.WEBSERVICE_ADDRESS;

    static {
        setupRestClient();
    }

    private RESTClient() {}

    public static RestLoginAPI getLoginAPI()
    {
        return REST_LOGIN_API;
    }

    public static RestTicketAPI getTicketAPI()
    {
        return REST_TICKET_API;
    }

    private static void setupRestClient()
    {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ROOT);
        builder.setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter adapter = builder.build();
        REST_LOGIN_API = adapter.create(RestLoginAPI.class);
        REST_TICKET_API = adapter.create(RestTicketAPI.class);
    }
}
