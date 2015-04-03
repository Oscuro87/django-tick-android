package org.ec.androidticket.activities.ticketDetail.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ec.androidticket.R;

public class TicketCommentFragment extends Fragment implements TicketFragmentInterface
{
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


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_comment, container, false);
    }


    @Override
    public void onRefreshRequested()
    {
        //TODO: refresh du fragment commentaires
    }
}
