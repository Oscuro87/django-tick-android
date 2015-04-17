package org.ec.androidticket.backend.Async.apis;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.ec.androidticket.backend.models.internal.UserDataCache;

import java.io.IOException;

import retrofit.RequestInterceptor;

public class AuthRequestInterceptor implements RequestInterceptor
{
    @Override
    public void intercept(RequestFacade request)
    {
        if (UserDataCache.get().getAuthtoken() != null)
        {
            String authToken = "Token " + UserDataCache.get().getAuthtoken();

            request.addHeader("Authorization", authToken);
        }
    }
}
