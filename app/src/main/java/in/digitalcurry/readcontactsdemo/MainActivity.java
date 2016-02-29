package in.digitalcurry.readcontactsdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.digitalcurry.readcontactsdemo.adapters.ContactListAdapter;
import in.digitalcurry.readcontactsdemo.utils.ContactItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ContactItem> mContactList;
    private ContactListAdapter listViewAdapter;


    @Bind(R.id.fetch_contacts_btn)
    Button fetchContactsBtn;

    @Bind(R.id.contacts_list_view)
    ListView mContactsListView;


    @OnClick(R.id.fetch_contacts_btn)
    public void onFetchContactsBtnClicked(View view) {

        try {
            // Initiate the gathering of contacts list
            String[] projections = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
                    Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB ? ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY : ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projections, null, null, null);


            while(cursor.moveToNext()) {
                ContactItem item = new ContactItem();

                String name = cursor.getString(cursor.getColumnIndex(projections[2]));
                String id = cursor.getString(cursor.getColumnIndex(projections[0]));
                String lookup_key = cursor.getString(cursor.getColumnIndex(projections[1]));

                item.setId(id);
                item.setName(name);
                item.setLookup_key(lookup_key);

                mContactList.add(item);
            }

            listViewAdapter.notifyDataSetChanged();

        }
        catch(SecurityException ex) {
            Log.d("Exception", ex.getMessage());
        }




    }

    // List View Click handler
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            ContactItem item = mContactList.get(i);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("id", item.getId());
            intent.putExtra("lookup_key", item.getLookup_key());
            startActivity(intent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mContactList = new ArrayList<ContactItem>();
        listViewAdapter = new ContactListAdapter(this, mContactList, R.layout.layout_list_item);

        mContactsListView.setOnItemClickListener(onItemClickListener);
        mContactsListView.setAdapter(listViewAdapter);

    }
}
