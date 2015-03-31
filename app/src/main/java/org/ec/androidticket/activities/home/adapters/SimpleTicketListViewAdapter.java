package org.ec.androidticket.activities.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ec.androidticket.R;
import org.ec.androidticket.backend.Async.responses.helpers.Ticket;

import java.util.List;

public class SimpleTicketListViewAdapter extends ArrayAdapter<Ticket>
{
    public SimpleTicketListViewAdapter(Context context, List<Ticket> ticketses)
    {
        super(context, 0, ticketses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Ticket ticket = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_ticket_layout, parent, false);
        TextView ticketCodeTV = (TextView)convertView.findViewById(R.id.simple_ticket_code);
        TextView ticketStatusTV = (TextView)convertView.findViewById(R.id.simple_ticket_status);

        ticketCodeTV.setText(ticket.getTicketCode());
        ticketStatusTV.setText(ticket.getTicketStatus().getLabel());

        return convertView;
    }
}
