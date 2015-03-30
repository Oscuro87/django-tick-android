package org.ec.androidticket.activities.home.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.responses.simpleTicket.helpers.TicketStatus;

/**
 * Fragment représentant une icône + code ticket pour chaque ticket associé à l'utilisateur.
 * L'utilisateur pourra "cliquer" sur le fragment pour ouvrir la vue ticket.
 */
public class TicketObjectFragment extends Fragment
{
    private static final String ARG_ticketCode = "ticketCode";
    private static final String TICKETSTATUS_KEY = "ticketStatusKey";

    private String ticketCode;

    private OnTicketObjectFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ticketCode Le code ticket à afficher
     * @param status The tickt
     * @return A new instance of fragment TicketObjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketObjectFragment newInstance(String ticketCode, TicketStatus status)
    {
        TicketObjectFragment fragment = new TicketObjectFragment();
        // préparer les params
        Bundle args = new Bundle();
        args.putString(ARG_ticketCode, ticketCode);

        fragment.setArguments(args);
        return fragment;
    }

    public TicketObjectFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            ticketCode = getArguments().getString(ARG_ticketCode);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_object, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onTicketObjectInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mListener = (OnTicketObjectFragmentInteractionListener) activity;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTicketObjectFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface OnTicketObjectFragmentInteractionListener
    {
        // TODO: Update argument type and name
        public void onTicketObjectInteraction(Uri uri);
    }

}
