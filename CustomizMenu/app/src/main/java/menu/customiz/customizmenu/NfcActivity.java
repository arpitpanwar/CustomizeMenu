package menu.customiz.customizmenu;


/**
 * Created by ameyamore on 05/09/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;
import java.util.List;

import menu.customiz.customizmenu.R;
import menu.customiz.customizmenu.SQLite.DbHelper;
import menu.customiz.customizmenu.epic.AllergiesFetcher;
import menu.customiz.customizmenu.menu.FilteredMenuProvider;
import menu.customiz.customizmenu.menu.MenuParser;
import menu.customiz.customizmenu.model.Course;
import menu.customiz.customizmenu.model.Ingredient;
import menu.customiz.customizmenu.model.Item;
import menu.customiz.customizmenu.model.Menu;

import static android.nfc.NdefRecord.createMime;


public class NfcActivity extends Activity implements CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    //TextView textView;

    public Menu filteredMenu;
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc);
        TextView textView = (TextView) findViewById(R.id.helloText);
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        // Register callback
        mNfcAdapter.setNdefPushMessageCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMime(
                        "text/menu.customiz", text.getBytes())
                        /**
                         * The Android Application Record (AAR) is commented out. When a device
                         * receives a push with an AAR in it, the application specified in the AAR
                         * is guaranteed to run. The AAR overrides the tag dispatch system.
                         * You can add it back in to guarantee that this
                         * activity starts when receiving a beamed message. For now, this code
                         * uses the tag dispatch system.
                         */
                        //,NdefRecord.createApplicationRecord("com.example.android.beam")
                });
        return msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        InfoFetcher fetcher = new InfoFetcher();
        fetcher.execute(intent);
          //List<String> allergies =
    }

    public void createData() {
        List<Course> allCourses = filteredMenu.getCourseList();
        int j=0;
        for(Course course:allCourses)
        {
            Group group = new Group(course.getCourseName());
            for(Item item : course.getItems())
            {
                String data = item.getName() +"\t\t" + "$"+item.getItemPrice()+"\nIngredients: ";
                List<Ingredient> ingredients = item.getIngredients();
                data+=ingredients.get(0).getName();
                for(int i=1;i<ingredients.size();i++)
                {
                    data+=", " + ingredients.get(i).getName();
                }
                group.children.add(data);
            }
            groups.append(j, group);
            ++j;
        }
    }

    public void Create(){
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,groups);
        listView.setAdapter(adapter);
    }

    class InfoFetcher extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params) {
            Intent intent = (Intent)params[0];
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            // only one message sent during the beam
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            // record 0 contains the MIME type, record 1 is the AAR, if present
            //textView.setText(new String(msg.getRecords()[0].getPayload()));

            /*TODO: Get allergy data and display menu*/

            DbHelper dbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();



            //Cursor c = db.rawQuery("select * from Patient", null);
            //c.moveToFirst();
            /*String userId = c.getString(0);
            Log.d("QUERY", userId);
            Log.d("QUERY", c.getString(1));
            Log.d("QUERY", c.getString(2));
            Log.d("QUERY", c.getString(3));
            Log.d("QUERY", c.getString(4));
            Log.d("QUERY", c.getString(5));
            Log.d("QUERY", c.getString(6));*/
            SQLiteDatabase mydatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME,MODE_PRIVATE,null);
            Cursor resultSet = mydatabase.rawQuery("Select * from PATIENT", null);
            resultSet.moveToFirst();
            String [] values=resultSet.getString(0).split(",");
            AllergiesFetcher allergiesFetcher = new AllergiesFetcher(values[0]);

            try {
                Log.d("Before filtered menu", "Before filtered menu");
               Menu origMenu = MenuParser.parse(new String(msg.getRecords()[0].getPayload()));
                List<String> allergies = allergiesFetcher.getAllergies();
                FilteredMenuProvider filteredMenuProvider = new FilteredMenuProvider(origMenu, allergies);
                Menu menu = filteredMenuProvider.getFilteredMenu();
                return menu;
            } catch (Exception e) {
                Log.d("NFC", "Unable to filter menu" + e.getStackTrace());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(o instanceof Menu){
                Menu men = (Menu)o;
                Log.d("NfcActivity","Menu fetched");
                setContentView(R.layout.display_course_layout);
                filteredMenu = men;
                createData();
                Create();
            }
        }
    }
}