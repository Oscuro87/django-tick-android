package org.ec.androidticket.backend.Async.apis;

import org.ec.androidticket.backend.models.ticketing.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.FullTicket;
import org.ec.androidticket.backend.models.ticketing.HistoryDiet;
import org.ec.androidticket.backend.models.ticketing.Ticket;
import org.ec.androidticket.backend.models.ticketing.Tickets;

import java.util.List;

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
            @Field("ticketCode") String ticketCode,
            Callback<FullTicket> callback);

    @FormUrlEncoded
    @POST("/rest/tickets/full/comments")
    void requestTicketComments(
            @Header("Authorization") String authtoken,
            @Field("ticketCode") String ticketCode,
            Callback<List<CommentDiet>> callback
    );

    @FormUrlEncoded
    @POST("/rest/tickets/full/history")
    void requestTicketHistory(
            @Header("Authorization") String authtoken,
            @Field("ticketCode") String ticketCode,
            Callback<List<HistoryDiet>> callback
    );
}
