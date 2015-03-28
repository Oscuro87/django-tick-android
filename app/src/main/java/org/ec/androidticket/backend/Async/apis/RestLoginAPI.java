package org.ec.androidticket.backend.Async.apis;

import org.ec.androidticket.backend.Async.responses.RestLoginResponses;

import retrofit.Callback;
import retrofit.http.*;

public interface RestLoginAPI
{
    @FormUrlEncoded
    @POST("/rest/auth")
    void authTicket(
            @Field("email") String email,
            @Field("password") String password,
            Callback<RestLoginResponses.Auth> callback);

    @GET("/rest/logout")
    void logoutTicket(Callback<RestLoginResponses.Logout> callback);
}
