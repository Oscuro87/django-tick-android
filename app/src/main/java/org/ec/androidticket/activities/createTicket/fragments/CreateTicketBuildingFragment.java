package org.ec.androidticket.activities.createTicket.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyFragment;
import org.ec.androidticket.activities.createTicket.CreateTicketActivity;
import org.ec.androidticket.activities.createTicket.adapters.TicketCreationInformationHolder;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestUserBuildingsEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestUserBuildingsResponseEvent;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.Building;

import java.util.ArrayList;
import java.util.List;

public class CreateTicketBuildingFragment extends MyFragment
{
    private Spinner buildingSpinner;
    private Button nextStepButton;
    private TableRow floorRow, officeRow;
    private EditText floorEdit, officeEdit;
    private CreateTicketFragmentInterface activityInterface;
    private ArrayAdapter<Building> userBuildingsAdapter;
    private List<Building> userBuildings;
    private boolean isViewCreated = false;

    public static CreateTicketBuildingFragment newInstance(String param1, String param2)
    {
        CreateTicketBuildingFragment fragment = new CreateTicketBuildingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public CreateTicketBuildingFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
        }

        bus.post(new RequestUserBuildingsEvent(UserDataCache.get().getAuthtoken()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_create_ticket_building, container, false);

        nextStepButton = (Button) view.findViewById(R.id.ticketCreateBuilding_nextStep);
        buildingSpinner = (Spinner) view.findViewById(R.id.createTicket_buildingSpinner);
        floorRow = (TableRow) view.findViewById(R.id.ticketCreateBuilding_floorRow);
        officeRow = (TableRow) view.findViewById(R.id.ticketCreateBuilding_officeRow);
        floorEdit = (EditText) view.findViewById(R.id.ticketCreate_floor);
        officeEdit = (EditText) view.findViewById(R.id.ticketCreate_office);

        userBuildings = new ArrayList<>();
        userBuildingsAdapter = new ArrayAdapter<Building>(view.getContext(), R.layout.create_ticket_category_subcategory_row, userBuildings);
        buildingSpinner.setAdapter(userBuildingsAdapter);

        setupListeners();

        isViewCreated = true;

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            activityInterface = (CreateTicketFragmentInterface) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement CreateTicketFragmentInterface");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        activityInterface = null;
    }

    @Subscribe
    public void onBuildingsResponseReceived(RequestUserBuildingsResponseEvent event)
    {
        if(!event.hasError())
        {
            userBuildings.addAll(event.getUserBuildings());
            userBuildingsAdapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(getView().getContext(), event.error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupListeners()
    {
        nextStepButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveFragmentIntoCache();
                activityInterface.onNextStepCalled();
            }
        });



        buildingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Building selected = (Building)

                if(position <= 0)
                {
                    officeRow.setVisibility(View.GONE);
                    floorRow.setVisibility(View.GONE);
                }
                else
                {
                    officeRow.setVisibility(View.VISIBLE);
                    floorRow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                officeRow.setVisibility(View.GONE);
                floorRow.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser && isViewCreated)
            saveFragmentIntoCache();
    }

    private void saveFragmentIntoCache()
    {
        TicketCreationInformationHolder tcih = CreateTicketActivity.creationInformation;
        if(buildingSpinner.getSelectedItemPosition() > 0)
        {
            tcih.building = (Building)buildingSpinner.getSelectedItem();

            if( ! floorEdit.getText().toString().equals(getString(R.string.ticketCreateConfirmation_undefined)))
                tcih.floor = floorEdit.getText().toString();
            else
                tcih.floor = "";

            if( ! officeEdit.getText().toString().equals(getString(R.string.ticketCreateConfirmation_undefined)))
                tcih.office = officeEdit.getText().toString();
            else
                tcih.office = "";
        }
        else
        {
            tcih.building = null;
            tcih.floor = "";
            tcih.office = "";
        }
    }
}
