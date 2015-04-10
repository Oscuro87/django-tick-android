package org.ec.androidticket.activities.createTicket.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ec.androidticket.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTicketConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTicketConfirmationFragment extends Fragment
{
    private CreateTicketCategoryFragment categoryFragment;
    private CreateTicketFragmentInterface activityInterface;

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
}
