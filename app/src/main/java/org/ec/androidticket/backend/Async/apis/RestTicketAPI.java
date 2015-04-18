package org.ec.androidticket.backend.Async.apis;

import android.support.annotation.NonNull;

import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestCategoriesResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestUserBuildingsResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.TicketCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketCompanyResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketDetailResponseEvent;
import org.ec.androidticket.backend.Async.responses.PostResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.get.RequestTicketCompaniesListResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketProgressionResponseEvent;
import org.ec.androidticket.backend.models.ticketing.Comment;
import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;
import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;
import org.ec.androidticket.backend.models.ticketing.variants.NewBuilding;
import org.ec.androidticket.backend.models.ticketing.variants.TicketCreation;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * http://stackoverflow.com/questions/24049434/android-retrofit-post-custom-object-send-json-to-server
 */


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
            Callback<SimpleTicketResponseEvent> callback);

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

    @POST("/rest/tickets/full/createComment")
    void createComment(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Body Comment commentObject,
            Callback<PostResponseEvent> callback
    );

    @POST("/rest/tickets/createbuilding")
    void createBuilding(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Body NewBuilding building,
            Callback<BuildingCreationResponseEvent> callback
    );


    /*
     * On utilise l'annotation Body pour envoyer des objets Java au web service.
     * Grâce à cette annotation, le client REST traduira l'object Java en JSON.
     */

    /**
     * Requête envoyée au web service pour créer un ticket dans la DB.
     */
    @POST("/rest/tickets/createticket")
    void createTicket(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Body TicketCreation newTicket,
            Callback<TicketCreationResponseEvent> callback
    );

    @GET("/rest/tickets/getallcategories")
    void requestAllCategories(
            Callback<RequestCategoriesResponseEvent> callback
    );

    @GET("/rest/tickets/getallbuildingsforuser")
    void requestUserBuildings(
            @NonNull @Header("Authorization") String authtoken,
            Callback<RequestUserBuildingsResponseEvent> callback
    );

    @POST("/rest/tickets/updatedetails")
    void updateTicketDetail(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Body FullTicket ticketInfos,
            Callback<UpdateTicketDetailResponseEvent> callback
    );

    @FormUrlEncoded
    @POST("/rest/tickets/updatedetails/nextprogression")
    void updateTicketProgression(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            Callback<UpdateTicketProgressionResponseEvent> callback
    );

    @FormUrlEncoded
    @POST("/rest/tickets/updatedetails/getlistofcompanies")
    void getListOfCompaniesForTicket(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            Callback<RequestTicketCompaniesListResponseEvent> callback
    );

    @FormUrlEncoded
    @POST("/rest/tickets/updatedetails/updateticketcompany")
    void updateTicketCompany(
            @NonNull @Header("Authorization") String authtoken,
            @NonNull @Field("ticketCode") String ticketCode,
            @Field("companyPK") int companyPK,
            Callback<UpdateTicketCompanyResponseEvent> callback
    );
}
