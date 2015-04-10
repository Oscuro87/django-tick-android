package org.ec.androidticket.activities.createTicket.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.createTicket.CreateTicketActivity;
import org.ec.androidticket.activities.createTicket.adapters.TicketCreationInformationHolder;

public class CreateTicketConfirmationFragment extends Fragment
{
    private View view;
    private CreateTicketFragmentInterface activityInterface;

    private TextView categoryTV, subcategoryTV, buildingTV, floorTV, officeTV, descriptionTV;
    private Button confirmationButton;

    public static CreateTicketConfirmationFragment newInstance()
    {
        CreateTicketConfirmationFragment fragment = new CreateTicketConfirmationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CreateTicketConfirmationFragment()
    {
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
        View view = inflater.inflate(R.layout.fragment_ticket_create_confirmation, container, false);
        this.view = view;

        categoryTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_category);
        subcategoryTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_subcategory);
        buildingTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_buildingName);
        floorTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_floor);
        officeTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_office);
        descriptionTV = (TextView)view.findViewById(R.id.ticketCreateConfirmation_description);

        confirmationButton = (Button)view.findViewById(R.id.ticketCreateConfirmation_confirmButton);

        setupListeners();

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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
            refreshFragmentFields();
    }

    private void refreshFragmentFields()
    {
        TicketCreationInformationHolder tcih = CreateTicketActivity.creationInformation;
        categoryTV.setText(tcih.category.getLabel());

        if(tcih.category.getParentCategory() != null)
            if(tcih.category.getParentCategory().getPrimaryKey() != -1)
                subcategoryTV.setText(tcih.category.getParentCategory().getLabel());
            else
                subcategoryTV.setText(getString(R.string.ticketCreateConfirmation_undefined));
        else
        {
            subcategoryTV.setText(getString(R.string.ticketCreateConfirmation_undefined));
        }

        if(tcih.building != null)
            buildingTV.setText(tcih.building.getBuildingName());
        else
            buildingTV.setText(getString(R.string.ticketCreateConfirmation_undefined));

        if(!tcih.floor.equals(""))
            floorTV.setText(tcih.floor);
        else
            floorTV.setText(getString(R.string.ticketCreateConfirmation_undefined));

        if(!tcih.office.equals(""))
            officeTV.setText(tcih.office);
        else
            officeTV.setText(getString(R.string.ticketCreateConfirmation_undefined));

        if(!tcih.description.equals(""))
            descriptionTV.setText(tcih.description);
        else
            descriptionTV.setText(getString(R.string.ticketCreateConfirmation_undefined));
    }

    private void setupListeners()
    {
        confirmationButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TicketCreationInformationHolder tcih = CreateTicketActivity.creationInformation;

                if(tcih.category != null && tcih.category.getPrimaryKey() != -1)
                    activityInterface.onTicketCreationFinished();
                else
                    Toast.makeText(v.getContext(), getString(R.string.ticketCreate_pleaseSelectCategory), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
