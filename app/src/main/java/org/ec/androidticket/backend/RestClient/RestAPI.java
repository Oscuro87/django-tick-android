package org.ec.androidticket.backend.RestClient;

import retrofit.Callback;
import retrofit.http.*;

public interface RestAPI
{
    @FormUrlEncoded
    @POST("/rest/auth")
    void authTicket(
            @Field("email") String email,
            @Field("password") String password,
            Callback<RestResponses.Auth> callback);

    @GET("/rest/logout")
    void logoutTicket(Callback<RestResponses.Logout> callback);
}
