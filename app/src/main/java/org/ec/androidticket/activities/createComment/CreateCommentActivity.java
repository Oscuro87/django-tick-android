package org.ec.androidticket.activities.createComment;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.BusDepot;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.comment.CommentCreationResponseEvent;
import org.ec.androidticket.backend.Async.responses.PostResponseEvent;
import org.ec.androidticket.backend.models.internal.FullTicketCache;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.CommentDiet;

import java.util.Date;

public class CreateCommentActivity extends ActionBarActivity
{
    private final String TICKETID_ARGUMENT_NAME = "ticketID";
    private TicketCommentHolder commentInfos;
    private Button sendCommentButton;
    private EditText commentEditText;

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);

        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);

        sendCommentButton = (Button)findViewById(R.id.createComment_sendCommentButton);
        commentEditText = (EditText)findViewById(R.id.createComment_commentEditText);

        retrieveIntentArguments();
        setupListeners();
    }

    private void setupListeners()
    {
        sendCommentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendCommentCreation();
            }
        });
    }

    private void retrieveIntentArguments()
    {
        Bundle arguments = getIntent().getExtras();

        if (!arguments.containsKey(TICKETID_ARGUMENT_NAME))
            finish(); // Probleme!
        else
        {
            int ticketID = arguments.getInt(TICKETID_ARGUMENT_NAME);
            commentInfos = new TicketCommentHolder(ticketID);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_create_comment, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void sendCommentCreation()
    {
        commentInfos.dateCreated = new Date(); // Maintenant
        commentInfos.comment = commentEditText.getText().toString();
        bus.post(new CommentCreationEvent(UserDataCache.get().getAuthtoken(), commentInfos.buildComment()));
        finish();
    }

    private void backToTicketDetail()
    {
        finish();
    }
}
