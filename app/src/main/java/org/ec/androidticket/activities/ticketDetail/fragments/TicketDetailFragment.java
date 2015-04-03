package org.ec.androidticket.activities.ticketDetail.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.models.internal.FullTicketCache;
import org.ec.androidticket.backend.models.ticketing.TicketStatus;

public class TicketDetailFragment extends Fragment implements TicketFragmentInterface
{
    private TextView ticketCode;
    private TextView ticketStatus;
    private TextView ticketCategory;
    private TextView ticketSubcategory;
    private View inflated;

    public static TicketDetailFragment newInstance()
    {
        TicketDetailFragment fragment = new TicketDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TicketDetailFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        inflated = inflater.inflate(R.layout.fragment_ticket_detail, container, false);
        return inflated;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        ticketCode = (TextView)inflated.findViewById(R.id.ticketDetail_ticketCode);
        ticketStatus = (TextView)inflated.findViewById(R.id.ticketDetail_ticketStatus);
        ticketCategory = (TextView)inflated.findViewById(R.id.ticketDetail_ticketCategory);
        ticketSubcategory = (TextView)inflated.findViewById(R.id.ticketDetail_ticketSubcategory);
    }

    @Override
    public void onRefreshRequested()
    {
        FullTicketCache cache = FullTicketCache.get();
        ticketCode.setText(cache.getTicketInformations().getTicketCode());
        ticketStatus.setText(cache.getTicketInformations().getStatus().getLabel());
        ticketSubcategory.setText(cache.getTicketInformations().getSubcategory().getLabel());
        ticketCategory.setText(cache.getTicketInformations().getSubcategory().getParentCategory().getLabel());
    }
}
