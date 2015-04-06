package org.ec.androidticket.activities.ticketDetail.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.ticketDetail.adapters.HistoryListViewAdapter;
import org.ec.androidticket.backend.models.internal.FullTicketCache;
import org.ec.androidticket.backend.models.ticketing.HistoryDiet;

import java.util.List;

public class TicketHistoryFragment extends Fragment implements TicketFragmentInterface
{
    private ListView historyList;

    public TicketHistoryFragment()
    {
        // Required empty public constructor
    }

    public static TicketHistoryFragment newInstance()
    {
        TicketHistoryFragment fragment = new TicketHistoryFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_history, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        historyList = (ListView) view.findViewById(R.id.historyListView);
        historyList.setAdapter(new HistoryListViewAdapter(view.getContext(), FullTicketCache.get().getHistoryCache()));
    }

    @Override
    public void onRefreshRequested()
    {
        List<HistoryDiet> historyCache = FullTicketCache.get().getHistoryCache();
        if(historyCache == null) return;

        ((HistoryListViewAdapter)this.historyList.getAdapter()).updateSourceList(historyCache);
    }
}