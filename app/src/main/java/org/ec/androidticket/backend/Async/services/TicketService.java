package org.ec.androidticket.backend.Async.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.RESTClient;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationEvent;
import org.ec.androidticket.backend.Async.events.buildingEvents.BuildingCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationResponseEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.FullTicketRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestFailureEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.history.HistoryRequestSuccessEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketRequestEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.SimpleTicketRequestSuccessEvent;
import org.ec.androidticket.backend.Async.responses.PostResponseEvent;
import org.ec.androidticket.backend.models.ticketing.variants.CommentDiet;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;
import org.ec.androidticket.backend.models.ticketing.variants.HistoryDiet;
import org.ec.androidticket.backend.models.ticketing.Tickets;

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
                new Callback<Tickets>()
                {
                    @Override
                    public void success(Tickets tickets, Response response)
                    {
                        bus.post(new SimpleTicketRequestSuccessEvent(tickets.getTickets()));
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        Log.e("CustomLog", "Error with Retrofit: " + error.getMessage());
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
}
