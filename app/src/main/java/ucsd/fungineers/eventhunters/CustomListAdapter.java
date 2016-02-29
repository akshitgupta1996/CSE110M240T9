package ucsd.fungineers.eventhunters;

import java.util.*;
import android.view.*;
import android.content.*;
import android.widget.*;

/**
 * Created by kagcaoili on 2/28/16.
 */
public class CustomListAdapter extends BaseAdapter{

    private ArrayList<Event> listData;
    private LayoutInflater layoutInflater;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.event_item, null);
            holder = new ViewHolder();
            holder.event_item_name = (TextView) convertView.findViewById(R.id.event_item_name);
            holder.event_item_date_text = (TextView) convertView.findViewById(R.id.event_item_date_text);
            holder.event_item_location = (TextView) convertView.findViewById(R.id.event_item_location);
            holder.event_item_description = (TextView) convertView.findViewById(R.id.event_item_description);
            //holder.event_item_date_overlay = (TextView) convertView.findViewById(R.id.event_item_date_overlay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.event_item_name.setText(listData.get(position).getName());
        holder.event_item_date_text.setText(listData.get(position).getDate().toString());
        holder.event_item_description.setText(listData.get(position).getDescription());
        //holder.event_item_date_overlay.setText(listData.get(position).getDate().toString());
        return convertView;

    }

    static class ViewHolder {
        TextView event_item_name;
        TextView event_item_date_text;
        TextView event_item_location;
        TextView event_item_description;
        //TextView event_item_date_overlay;
    }

}
