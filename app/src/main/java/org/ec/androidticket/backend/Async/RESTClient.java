package org.ec.androidticket.backend.Async;

import com.squareup.okhttp.OkHttpClient;

import org.ec.androidticket.backend.Async.apis.RestLoginAPI;
import org.ec.androidticket.backend.GlobalSettings;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RESTClient
{
    private static RestLoginAPI REST_CLIENT;
    private static String ROOT = GlobalSettings.WEBSERVICE_ADDRESS;

    static {
        setupRestClient();
    }

    private RESTClient() {}

    public static RestLoginAPI get()
    {
        return REST_CLIENT;
    }

    private static void setupRestClient()
    {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ROOT);
        builder.setClient(new OkClient(new OkHttpClient()));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter adapter = builder.build();
        REST_CLIENT = adapter.create(RestLoginAPI.class);
    }
}
