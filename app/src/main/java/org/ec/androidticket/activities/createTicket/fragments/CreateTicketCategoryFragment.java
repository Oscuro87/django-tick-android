package org.ec.androidticket.activities.createTicket.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.ec.androidticket.R;
import org.ec.androidticket.activities.MyFragment;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestCategoriesEvent;
import org.ec.androidticket.backend.Async.events.ticketEvents.ticket.create.RequestCategoriesResponseEvent;
import org.ec.androidticket.backend.models.internal.UserDataCache;
import org.ec.androidticket.backend.models.ticketing.Category;

import java.util.ArrayList;
import java.util.List;

public class CreateTicketCategoryFragment extends MyFragment
{
    private Spinner categorySpinner, subcategorySpinner;
    private Button nextStepButton;
    private TextView subcategoryLabel;
    private List<Category> subcategoryFullList = new ArrayList<>();
    private ArrayAdapter<Category> categoriesAdapter, subcategoriesAdapter;

    private CreateTicketFragmentInterface activityInterface;

    public static CreateTicketCategoryFragment newInstance(String param1, String param2)
    {
        CreateTicketCategoryFragment fragment = new CreateTicketCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CreateTicketCategoryFragment()
    {
        // Laisser vide
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
        }

        bus.post(new RequestCategoriesEvent(UserDataCache.get().getAuthtoken()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_create_ticket_category, container, false);

        nextStepButton = (Button)view.findViewById(R.id.createTicketCategory_nextStep);

        categorySpinner = (Spinner)view.findViewById(R.id.ticketCreate_categorySpinner);
        subcategorySpinner = (Spinner)view.findViewById(R.id.ticketCreate_subcategorySpinner);
        subcategoryLabel = (TextView)view.findViewById(R.id.createTicket_subcategoryLabel);

        categoriesAdapter = new ArrayAdapter<>(view.getContext(), R.layout.create_ticket_category_subcategory_row);
        subcategoriesAdapter = new ArrayAdapter<>(view.getContext(), R.layout.create_ticket_category_subcategory_row);

        categorySpinner.setAdapter(categoriesAdapter);
        subcategorySpinner.setAdapter(subcategoriesAdapter);

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

    @Subscribe
    public void onRequestCategoriesResponseEvent(RequestCategoriesResponseEvent event)
    {
        categoriesAdapter.addAll(event.getCategories());
        categoriesAdapter.notifyDataSetChanged();

        subcategoryFullList.addAll(event.getSubcategories());
    }

    private void refreshSubcategorySpinner()
    {
        Category selected = (Category)categorySpinner.getSelectedItem();
        subcategoriesAdapter.clear();

        if(selected.getPrimaryKey() != -1)
        {
            for(Category c : subcategoryFullList)
            {
                if(c.getParentCategory() == null) continue;
                if(c.getParentCategory().getPrimaryKey() == selected.getPrimaryKey() || c.getParentCategory().getPrimaryKey() == -1)
                    subcategoriesAdapter.add(c);
            }
        }

        categoriesAdapter.notifyDataSetChanged();
        subcategoriesAdapter.notifyDataSetChanged();
    }

    private void setupListeners()
    {
        nextStepButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(((Category)categorySpinner.getSelectedItem()).getPrimaryKey() == -1)
                    Toast.makeText(v.getContext(), getString(R.string.ticketCreate_pleaseSelectCategory), Toast.LENGTH_SHORT).show();
                else
                    activityInterface.onNextStepCalled();
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Category selected = (Category) categorySpinner.getItemAtPosition(position);
                Log.d("CustomLog", "" + selected.getPrimaryKey());
                if(position <= 0)
                {
//                    Toast.makeText(getView().getContext(), getString(R.string.pleaseSelectACategory), Toast.LENGTH_SHORT).show();
                    subcategoryLabel.setVisibility(View.INVISIBLE);
                    subcategorySpinner.setVisibility(View.INVISIBLE);
                }
                else
                {
                    subcategoryLabel.setVisibility(View.VISIBLE);
                    subcategorySpinner.setVisibility(View.VISIBLE);
                }

                refreshSubcategorySpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                subcategoryLabel.setVisibility(View.INVISIBLE);
                subcategorySpinner.setVisibility(View.INVISIBLE);

                refreshSubcategorySpinner();
            }
        }
        );
    }
}
