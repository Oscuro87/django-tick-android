package org.ec.androidticket.backend.Async.apis;

import org.ec.androidticket.backend.models.ticketing.Auth;
import org.ec.androidticket.backend.models.ticketing.Logout;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public interface RestLoginAPI
{
    @FormUrlEncoded
    @POST("/rest/auth")
    void authTicket(
            @Field("email") String email,
            @Field("password") String password,
            Callback<Auth> callback);

    @GET("/rest/logout")
    void logoutTicket(
            @Header("Authorization") String authtoken,
            Callback<Logout> callback);
}
