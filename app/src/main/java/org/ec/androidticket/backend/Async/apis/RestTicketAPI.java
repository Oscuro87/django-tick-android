package org.ec.androidticket.backend.Async.apis;

import org.ec.androidticket.backend.models.ticketing.FullTicket;
import org.ec.androidticket.backend.models.ticketing.Ticket;
import org.ec.androidticket.backend.models.ticketing.Tickets;

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
            Callback<Tickets> callback);

    @FormUrlEncoded
    @POST("/rest/tickets/full")
    void requestFullTicket(
            @Header("Authorization") String authtoken,
            @Field("ticketPK") Integer ticketPK,
            Callback<FullTicket> callback);
}
