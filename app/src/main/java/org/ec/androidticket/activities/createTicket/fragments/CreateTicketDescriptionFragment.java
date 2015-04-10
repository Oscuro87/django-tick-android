package org.ec.androidticket.activities.createTicket.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.createTicket.CreateTicketActivity;
import org.ec.androidticket.activities.createTicket.adapters.TicketCreationInformationHolder;

public class CreateTicketDescriptionFragment extends Fragment
{
    private CreateTicketFragmentInterface activityInterface;
    private EditText descriptionEdit;
    private Button nextStepButton;
    private boolean isViewCreated = false;

    public static CreateTicketDescriptionFragment newInstance(String param1, String param2)
    {
        CreateTicketDescriptionFragment fragment = new CreateTicketDescriptionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CreateTicketDescriptionFragment()
    {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_create_ticket_description, container, false);

        nextStepButton = (Button) view.findViewById(R.id.ticketCreateDescription_nextStepButton);
        descriptionEdit = (EditText) view.findViewById(R.id.ticketCreate_description);

        // ...
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
        if(!descriptionEdit.getText().toString().equals(getString(R.string.ticketCreateConfirmation_undefined)))
            tcih.description = descriptionEdit.getText().toString();
        else
            tcih.description = "";
    }
}
