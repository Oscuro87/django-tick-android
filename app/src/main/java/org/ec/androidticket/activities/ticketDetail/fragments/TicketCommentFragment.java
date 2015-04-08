package org.ec.androidticket.activities.ticketDetail.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.createComment.CreateCommentActivity;
import org.ec.androidticket.activities.ticketDetail.adapters.CommentListViewAdapter;
import org.ec.androidticket.backend.models.internal.FullTicketCache;

public class TicketCommentFragment extends Fragment implements TicketFragmentInterface
{
    private ListView commentsListView;
    private Button postCommentButton;

    public TicketCommentFragment()
    {
        // Required empty public constructor
    }

    public static TicketCommentFragment newInstance()
    {
        TicketCommentFragment fragment = new TicketCommentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // http://stackoverflow.com/questions/11311541/how-can-i-implement-a-collapsible-view-like-the-one-from-google-play
        return inflater.inflate(R.layout.fragment_ticket_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        this.commentsListView = (ListView)view.findViewById(R.id.ticketComment_commentsList);
        CommentListViewAdapter clva = new CommentListViewAdapter(view.getContext(), FullTicketCache.get().getCommentCache());
        commentsListView.setAdapter(clva);

        postCommentButton = (Button) view.findViewById(R.id.ticketComment_postCommentButton);

        setupListeners();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        onRefreshRequested();
    }

    private void setupListeners()
    {
        postCommentButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), CreateCommentActivity.class);
                Bundle arguments = new Bundle();
                arguments.putInt("ticketID", FullTicketCache.get().getTicketCache().getTicketID());
                intent.putExtras(arguments);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefreshRequested()
    {
        if (FullTicketCache.get().getCommentCache() == null) return;
        CommentListViewAdapter adapter = (CommentListViewAdapter) commentsListView.getAdapter();

        //TODO: refresh du fragment commentaires
        adapter.updateCommentsList(FullTicketCache.get().getCommentCache());
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();
        getView().invalidate();
    }
}
