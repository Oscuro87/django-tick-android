package org.ec.androidticket.activities.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.models.ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SimpleTicketListViewAdapter extends BaseAdapter implements Filterable
{
    public Context context;
    public List<Ticket> sourceList;
    public List<Ticket> filteredList;

    public SimpleTicketListViewAdapter(Context context, List<Ticket> sourceList)
    {
        this.context = context;
        this.sourceList = sourceList;
        filteredList = sourceList;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return filteredList.size();
    }

    @Override
    public Ticket getItem(int position)
    {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence constraint)
            {
                final FilterResults output = new FilterResults();
                final List<Ticket> result = new ArrayList<>();
                if (sourceList == null)
                    sourceList = result;
                if (constraint != null)
                {
                    if (sourceList.size() > 0)
                    {
                        for (final Ticket ticket : sourceList)
                        {
                            if (ticket.getTicketCode().toLowerCase().contains(constraint.toString().toLowerCase())
                                    || ticket.getTicketStatus().getLabel().toLowerCase().contains(constraint.toString().toLowerCase()))
                                result.add(ticket);
                        }
                        output.values = result;
                    }
                }
                return output;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results)
            {
                filteredList = (ArrayList<Ticket>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TicketHolder stockage;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_ticket_row, parent, false);
            stockage = new TicketHolder();
            stockage.ticketCode = (TextView)convertView.findViewById(R.id.simple_ticket_code);
            stockage.status = (TextView)convertView.findViewById(R.id.simple_ticket_status);
            convertView.setTag(stockage);
        } else
        {
            stockage = (TicketHolder) convertView.getTag();
        }

        stockage.ticketCode.setText(filteredList.get(position).getTicketCode());
        stockage.status.setText(filteredList.get(position).getTicketStatus().getLabel());

        return convertView;
    }
}
