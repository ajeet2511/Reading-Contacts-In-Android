package in.digitalcurry.readcontactsdemo.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.digitalcurry.readcontactsdemo.R;
import in.digitalcurry.readcontactsdemo.utils.ContactItem;

/**
 * Created by dev on 29/02/16.
 */
public class ContactListAdapter extends ArrayAdapter<ContactItem> {

    private int resourceID;
    private ArrayList<ContactItem> mList;
    private LayoutInflater inflater;

    public ContactListAdapter(Context context, ArrayList<ContactItem> list, int resourceID) {
        super(context, resourceID, list);

        this.inflater = LayoutInflater.from(context);
        this.mList = list;
        this.resourceID = resourceID;
    }

    @Override
    public int getCount() {

        return mList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = inflater.inflate(resourceID, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        String name = mList.get(position).getName();
        viewHolder.nameTextView.setText(name);


        return  convertView;
    }


    private class ViewHolder {

        public TextView nameTextView;
    }

}
