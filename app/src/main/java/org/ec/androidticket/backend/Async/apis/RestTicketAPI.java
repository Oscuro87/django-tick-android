package org.ec.androidticket.backend.Async.apis;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.Channel;
import org.ec.androidticket.backend.models.ticketing.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.FullTicket;
import org.ec.androidticket.backend.models.ticketing.HistoryDiet;
import org.ec.androidticket.backend.models.ticketing.Tickets;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Contient l'API utilisable par toute l'application pour faire des requêtes REST vers le web service.
 * Configuration de l'adresse du web service : GlobalSettings.java
 */
public interface RestTicketAPI
{
    /**
     * Demande la liste de tous les tickets de cet utilisateur.
     * Les informations de ces tickets sont limitées à leur nom et status, pour réduire l'utilisation de la bande passante.
     * TODO: Si l'utilisateur est un gestionnaire (ou un admin), retourne tous ses tickets + tickets non gérés + tickets gérés par cet utilisateur.
     */
    @FormUrlEncoded
    @POST("/rest/tickets/simple")
    void requestSimpleTickets(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketType") String ticketType,
            Callback<Tickets> callback);

    /**
     * Demande des informations complètes d'un ticket en particulier.
     * Ne comprend que les informations du ticket, pas les commentaires ni l'historique. (voir méthodes suivantes)
     */
    @FormUrlEncoded
    @POST("/rest/tickets/full")
    void requestFullTicket(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            Callback<FullTicket> callback);

    /**
     * Demande des commentaires du ticket.
     */
    @FormUrlEncoded
    @POST("/rest/tickets/full/comments")
    void requestTicketComments(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            Callback<List<CommentDiet>> callback
    );

    /**
     * Demande l'historique du ticket
     */
    @FormUrlEncoded
    @POST("/rest/tickets/full/history")
    void requestTicketHistory(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            Callback<List<HistoryDiet>> callback
    );

    /*
    NOTE: Pour envoyer une photo via une requête http, utiliser l'annotation @MultiPart
     */

    /**
     * Requête envoyée au web service pour créer un ticket dans la DB.
     */
    @FormUrlEncoded
    @POST("/rest/tickets/create")
    void createTicket(
            @NonNull @Header("Authorization") String authtoken,
            /*
             * On utilise l'annotation Body pour envoyer des objets Java au web service.
             * Grâce à cette annotation, le client REST traduira l'object Java en JSON.
             */
            // Champs non nullables
            @NonNull @Body Category category,
            // Champs nullables
            @Nullable @Body Category subcategory,
            @Nullable @Body Building building,
            @Nullable @Body Channel channel,
            @Nullable @Field("floor") String floor,
            @Nullable @Field("office") String office,
            @Nullable @Field("description") String description
    );
}
