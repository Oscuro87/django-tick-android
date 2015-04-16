package org.ec.androidticket.activities.ticketDetail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyFragment;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.update.UpdateTicketDetailEvent;
import org.ec.androidticket.backend.models.internal.FullTicketCache;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.variants.FullTicket;

public class TicketDetailFragment extends MyFragment implements TicketFragmentInterface
{
    private TextView codeTV;
    private TextView statusTV;
    private TextView categoryTV;
    private TextView subcategoryTV;
    private TextView buildingTV;
    private TextView floorTV;
    private TextView officeTV;
    private TextView reporterTV;
    private TextView managerTV;
    private TextView companyTV;
    private TextView descriptionTV;
    private FullTicket ticket;

    private Button takeManagement, releaseManagement, assignCompany, changePhase;

    private View inflated;

    public TicketDetailFragment()
    {
        // Required empty public constructor
    }

    public static TicketDetailFragment newInstance()
    {
        TicketDetailFragment fragment = new TicketDetailFragment();
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
        inflated = inflater.inflate(R.layout.fragment_ticket_detail, container, false);
        return inflated;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        codeTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketCode);
        statusTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketStatus);
        categoryTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketCategory);
        subcategoryTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketSubcategory);
        buildingTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketBuilding);
        floorTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketFloor);
        officeTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketOffice);
        reporterTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketReporter);
        managerTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketManager);
        companyTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketCompany);
        descriptionTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketDescription);

        takeManagement = (Button) inflated.findViewById(R.id.ticketDetail_takeManagement);
        releaseManagement = (Button) inflated.findViewById(R.id.ticketDetail_releaseManagement);
        assignCompany = (Button) inflated.findViewById(R.id.ticketDetail_assignCompany);
        changePhase = (Button) inflated.findViewById(R.id.ticketDetail_changePhase);

        setupListeners();
    }

    private void setupListeners()
    {
        final TextView descriptionTV = (TextView) inflated.findViewById(R.id.ticketDetail_ticketDescription);
        descriptionTV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (descriptionTV.getMaxLines() == Integer.MAX_VALUE)
                    descriptionTV.setMaxLines(2);
                else
                    descriptionTV.setMaxLines(Integer.MAX_VALUE);
            }
        });

        takeManagement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(ticket == null)
                    ticket = FullTicketCache.get().getTicketCache();
                ticket.setManager(UserDataCache.get().buildUserFromCache());
                refreshMe();
            }
        });

        releaseManagement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(ticket == null)
                    ticket = FullTicketCache.get().getTicketCache();
                ticket.setManager(null);
                refreshMe();
            }
        });
    }

    @Override
    public void onRefreshRequested()
    {
        refreshMe();
    }

    @Override
    public void onDestroyView()
    {
        bus.post(new UpdateTicketDetailEvent(UserDataCache.get().getAuthtoken(), FullTicketCache.get().getTicketCache()));
        // ...
        super.onDestroyView();
    }

    private void refreshAdministrationButtons()
    {
        if (ticket == null)
            ticket = FullTicketCache.get().getTicketCache();

        takeManagement.setVisibility(View.GONE);
        releaseManagement.setVisibility(View.GONE);
        assignCompany.setVisibility(View.GONE);
        changePhase.setVisibility(View.GONE);

        // Si l'utilisateur est le manager de ce ticket
        if (ticket.getManager() != null && ticket.getManager().getEmail().equals(UserDataCache.get().getEmail()))
        {
            releaseManagement.setVisibility(View.VISIBLE);
            assignCompany.setVisibility(View.VISIBLE);
            changePhase.setVisibility(View.VISIBLE);
        }

        // Si l'utilisateur est manager et que ce ticket est non géré
        if (UserDataCache.get().isStaff() && ticket.getManager() == null)
        {
            takeManagement.setVisibility(View.VISIBLE);
        }
    }

    private void refreshMe()
    {
        ticket = FullTicketCache.get().getTicketCache();
        if (ticket == null) return;

        codeTV.setText(ticket.getTicketCode());
        statusTV.setText(ticket.getStatus().getLabel());

        if (ticket.getSubcategory() != null)
        {
            subcategoryTV.setText(ticket.getSubcategory().getLabel());
            categoryTV.setText(ticket.getSubcategory().getParentCategory().getLabel());
        } else
        {
            subcategoryTV.setText("");
            categoryTV.setText(ticket.getCategory().getLabel());
        }

        if (ticket.getBuilding() != null)
            buildingTV.setText(ticket.getBuilding().getBuildingName());
        else
            buildingTV.setText(getString(R.string.ticketDetail_noBuildingFlavour));

        floorTV.setText(ticket.getFloor());
        officeTV.setText(ticket.getOffice());
        reporterTV.setText(ticket.getReporter().getFullName());
        if (ticket.getManager() != null)
            managerTV.setText(ticket.getManager().getFullName());
        else
            managerTV.setText(getString(R.string.ticketDetail_noManagerFlavour));
        if (ticket.getCompany() != null)
            companyTV.setText(ticket.getCompany().getName());
        else
            companyTV.setText(getString(R.string.ticketDetail_noCompanyFlavour));
        descriptionTV.setMaxLines(2);
        descriptionTV.setText(ticket.getDescription());

        refreshAdministrationButtons();
    }
}
