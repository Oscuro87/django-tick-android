package org.ec.androidticket.backend.Async.apis;

import org.ec.androidticket.backend.Async.responses.SimpleTicketResponse;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

public interface RestTicketAPI
{
    @FormUrlEncoded
    @POST("/rest/tickets/simple")
    void requestSimpleTickets(
            @Header("Authorization") String authtoken,
            @Field("ticketType") String ticketType,
            Callback<SimpleTicketResponse.Tickets> callback);
}
