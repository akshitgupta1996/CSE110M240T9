package ucsd.fungineers.eventhunters;

import java.text.SimpleDateFormat;
import java.util.*;
import android.view.*;
import android.content.*;
import android.widget.*;

/**
 * Created by kagcaoili on 2/28/16.
 * This creates the selectable ListView of all the events for Host and Attendee.
 * Works alongside event_item to populate it's data and eventually populate list with event_items
 */
public class CustomListAdapter extends BaseAdapter{

    private ArrayList<Event> listData;
    private LayoutInflater layoutInflater;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy  hh:mm aa");
    private SimpleDateFormat overlayFormat = new SimpleDateFormat("MMM dd");

    public CustomListAdapter(Context aContext, ArrayList<Event> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //links the views to the holder so that it can set the data gathered from user
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.event_item, null);
            holder = new ViewHolder();
            holder.event_item_name = (TextView) convertView.findViewById(R.id.event_item_name);
            holder.event_item_date_text = (TextView) convertView.findViewById(R.id.event_item_date_text);
            holder.event_item_location = (TextView) convertView.findViewById(R.id.event_item_location);
            holder.event_item_description = (TextView) convertView.findViewById(R.id.event_item_description);
            holder.event_item_date_overlay = (TextView) convertView.findViewById(R.id.event_item_date_overlay);
            holder.event_item_location = (TextView) convertView.findViewById(R.id.event_item_location);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.event_item_name.setText(listData.get(position).getName());
        holder.event_item_date_text.setText(dateFormat.format(listData.get(position).getDate().getTime()));
        holder.event_item_description.setText(listData.get(position).getDescription());
        holder.event_item_date_overlay.setText(overlayFormat.format(listData.get(position).getDate().getTime()));
        holder.event_item_location.setText(listData.get(position).getLocation());
        return convertView;

    }

    //Contains all the views on the event_item
    static class ViewHolder {
        TextView event_item_name;
        TextView event_item_date_text;
        TextView event_item_location;
        TextView event_item_description;
        TextView event_item_date_overlay;
    }

}
