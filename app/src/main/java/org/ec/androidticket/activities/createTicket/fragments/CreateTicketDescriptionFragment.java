package org.ec.androidticket.activities.createTicket.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.ec.androidticket.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTicketDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTicketDescriptionFragment extends Fragment
{
    private CreateTicketFragmentInterface activityInterface;
    private Button nextStepButton;

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

        // ...
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

    private void setupListeners()
    {
        nextStepButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                activityInterface.onNextStepCalled();
            }
        });
    }
}
