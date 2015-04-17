package org.ec.androidticket.backend.Async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.ec.androidticket.backend.Async.apis.AuthRequestInterceptor;
import org.ec.androidticket.backend.Async.apis.RestLoginAPI;
import org.ec.androidticket.backend.Async.apis.RestTicketAPI;
import org.ec.androidticket.backend.GlobalSettings;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RESTClient
{
    private static RestLoginAPI REST_LOGIN_API;
    private static RestTicketAPI REST_TICKET_API;
    private static String ROOT = GlobalSettings.getDevelopmentWebserviceAddress();

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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ROOT);
        builder.setConverter(new GsonConverter(gson));
//        builder.setRequestInterceptor(new AuthRequestInterceptor());
        builder.setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter adapter = builder.build();
        REST_LOGIN_API = adapter.create(RestLoginAPI.class);
        REST_TICKET_API = adapter.create(RestTicketAPI.class);
    }
}
