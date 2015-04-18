package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationEvent;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestCategoriesEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestCategoriesResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestUserBuildingsEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestUserBuildingsResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.TicketCreationEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.TicketCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.get.RequestTicketCompaniesListEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.get.RequestTicketCompaniesListResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketCompanyEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketCompanyResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketDetailEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketDetailResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketProgressionEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketProgressionResponseEvent;
import org.ec.androidticket.backend.Async.responses.PostResponseEvent;
import org.ec.androidticket.backend.models.ticketing.Building;
import org.ec.androidticket.backend.models.ticketing.Category;
import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;
import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TicketService
{
    private static TicketService instance = null;
    private Bus bus;

    private TicketService()
    {
        this.bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        bus.register(this);
    }

    public static TicketService get()
    {
        if (instance == null)
            instance = new TicketService();
        return instance;
    }

    @Subscribe
    public void onSimpleTicketsRequest(SimpleTicketRequestEvent event)
    {
        RESTClient.getTicketAPI().requestSimpleTickets(
                event.getAuthorizationToken(),
                event.getTicketType(),
                new Callback<SimpleTicketResponseEvent>()
                {
                    @Override
                    public void success(SimpleTicketResponseEvent responseEvent, Response response)
                    {
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new SimpleTicketResponseEvent(error));
                    }
                }
        );
    }

    @Subscribe
    public void onFullTicketRequest(FullTicketRequestEvent event)
    {
        RESTClient.getTicketAPI().requestFullTicket(
                event.getAuthToken(),
                event.getTicketCode(),
                new Callback<FullTicket>()
                {
                    @Override
                    public void success(FullTicket ticket, Response response)
                    {
                        Log.i("CustomLog", "Successfully retrieved ticket information for ticket code: " + ticket.getTicketCode());
                        bus.post(new FullTicketRequestSuccessEvent(ticket));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Failed to retrieve ticket informations:\n" + error.getMessage());
                        bus.post(new FullTicketRequestFailureEvent(error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onCommentRequest(CommentRequestEvent event)
    {
        RESTClient.getTicketAPI().requestTicketComments(
                event.getAuthtoken(),
                event.getTicketCode(),
                new Callback<List<CommentDiet>>()
                {
                    @Override
                    public void success(List<CommentDiet> commentDiets, Response response)
                    {
                        Log.i("CustomLog", "Retrieved ticket comments");
                        bus.post(new CommentRequestSuccessEvent(commentDiets));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Error retrieving ticket comments: " + error.getMessage());
                        bus.post(new CommentRequestFailureEvent(error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onCommentCreationRequest(CommentCreationEvent event)
    {
        RESTClient.getTicketAPI().createComment(
                event.getAuthtoken(),
                event.getComment(),
                new Callback<PostResponseEvent>()
                {
                    @Override
                    public void success(PostResponseEvent postResponse, Response response)
                    {
                        bus.post(new CommentCreationResponseEvent(postResponse.isSuccess(), postResponse.getReason()));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new CommentCreationResponseEvent(false, error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onHistoryRequest(HistoryRequestEvent event)
    {
        RESTClient.getTicketAPI().requestTicketHistory(
                event.getAuthtoken(),
                event.getTicketCode(),
                new Callback<List<HistoryDiet>>()
                {
                    @Override
                    public void success(List<HistoryDiet> historyDiets, Response response)
                    {
                        Log.i("CustomLog", "Retrieved ticket history");
                        bus.post(new HistoryRequestSuccessEvent(historyDiets));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Error retrieving ticket comments: " + error.getMessage());
                        bus.post(new HistoryRequestFailureEvent(error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onBuildingCreationRequest(BuildingCreationEvent event)
    {
        RESTClient.getTicketAPI().createBuilding(
                event.getAuthtoken(),
                event.getBuilding(),
                new Callback<BuildingCreationResponseEvent>()
                {
                    @Override
                    public void success(BuildingCreationResponseEvent event1, Response response)
                    {
                        bus.post(new BuildingCreationResponseEvent(event1.success, event1.reason));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new BuildingCreationResponseEvent(false, error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onTicketCreationRequest(TicketCreationEvent event)
    {
        RESTClient.getTicketAPI().createTicket(
                event.getAuthtoken(),
                event.getNewTicket(),
                new Callback<TicketCreationResponseEvent>()
                {
                    @Override
                    public void success(TicketCreationResponseEvent ticketCreationResponseEvent, Response response)
                    {
                        bus.post(ticketCreationResponseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new TicketCreationResponseEvent(false, "Retrofit error: " + error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onCategoriesRequest(RequestCategoriesEvent event)
    {
        RESTClient.getTicketAPI().requestAllCategories(
                new Callback<RequestCategoriesResponseEvent>()
                {
                    @Override
                    public void success(RequestCategoriesResponseEvent requestCategoriesResponseEvent, Response response)
                    {
                        requestCategoriesResponseEvent.getCategories().add(0, new Category(-1, null, null, ""));
                        requestCategoriesResponseEvent.getSubcategories().add(0, new Category(-1, new Category(-1, null, null, ""), null, ""));
                        bus.post(requestCategoriesResponseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new RequestCategoriesResponseEvent());
                    }
                }
        );
    }

    @Subscribe
    public void onBuildingsRequest(RequestUserBuildingsEvent event)
    {
        RESTClient.getTicketAPI().requestUserBuildings(
                event.getAuthtoken(),
                new Callback<RequestUserBuildingsResponseEvent>()
                {
                    @Override
                    public void success(RequestUserBuildingsResponseEvent responseEvent, Response response)
                    {
                        Log.i("CustomLog", "Retrieve buildings for user success");
                        Building emptyBuilding = new Building();
//                        emptyBuilding.
                        responseEvent.getUserBuildings().add(0, emptyBuilding);
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", error.getMessage());
                        bus.post(new RequestUserBuildingsResponseEvent(error));
                    }
                }
        );
    }

    @Subscribe
    public void onTicketDetailUpdate(UpdateTicketDetailEvent event)
    {
        RESTClient.getTicketAPI().updateTicketDetail(
                event.getAuthtoken(),
                event.getFullTicket(),
                new Callback<UpdateTicketDetailResponseEvent>()
                {
                    @Override
                    public void success(UpdateTicketDetailResponseEvent responseEvent, Response response)
                    {
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new UpdateTicketDetailResponseEvent(error));
                    }
                }
        );
    }

    @Subscribe
    public void onTicketUpdateProgression(UpdateTicketProgressionEvent event)
    {
        RESTClient.getTicketAPI().updateTicketProgression(
                event.getAuthtoken(),
                event.getTicketCode(),
                new Callback<UpdateTicketProgressionResponseEvent>()
                {
                    @Override
                    public void success(UpdateTicketProgressionResponseEvent responseEvent, Response response)
                    {
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new UpdateTicketProgressionResponseEvent(false, error.getMessage()));
                    }
                }
        );
    }

    @Subscribe
    public void onTicketCompaniesListRequest(RequestTicketCompaniesListEvent event)
    {
        RESTClient.getTicketAPI().getListOfCompaniesForTicket(
                event.getAuthtoken(),
                event.getTicketCode(),
                new Callback<RequestTicketCompaniesListResponseEvent>()
                {
                    @Override
                    public void success(RequestTicketCompaniesListResponseEvent responseEvent, Response response)
                    {
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        bus.post(new RequestTicketCompaniesListResponseEvent(error.getMessage(), false));
                    }
                }
        );
    }

    @Subscribe
    public void onUpdateTicketCompany(UpdateTicketCompanyEvent event)
    {
        RESTClient.getTicketAPI().updateTicketCompany(
                event.getAuthtoken(),
                event.getTicketCode(),
                event.getCompanyPK(),
                new Callback<UpdateTicketCompanyResponseEvent>()
                {
                    @Override
                    public void success(UpdateTicketCompanyResponseEvent responseEvent, Response response)
                    {
                        bus.post(responseEvent);
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        UpdateTicketCompanyResponseEvent responseEvent = new UpdateTicketCompanyResponseEvent(false, error.getMessage());
                        bus.post(responseEvent);
                    }
                }
        );
    }
}
