package menu.customiz.customizmenuforrestaurants; /**
 * Created by Abhishek and Ashmeet on 9/5/2015.
 */

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import menu.customiz.customizmenuforrestaurants.model.Course;
import menu.customiz.customizmenuforrestaurants.model.Ingredient;
import menu.customiz.customizmenuforrestaurants.model.Item;
import menu.customiz.customizmenuforrestaurants.model.RestaurantMenu;

import static android.nfc.NdefRecord.createMime;

public class DisplayCourseClass extends Activity implements NfcAdapter.CreateNdefMessageCallback {
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    NfcAdapter mNfcAdapter;
    TextView textView;
    RestaurantMenu menu=null;
    SQLiteDatabase mydatabase;
    public static final String RESTAURANT_NAME="Han Dynasty";
    public static final String CHINESE="CHINESE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_course_layout);
        menu = getMenuForDisplay();
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Register callback
        mNfcAdapter.setNdefPushMessageCallback(this, this);

    }

    public void createData() {
        List<Course> allCourses = menu.getCourseList();
        int j=0;
        for(Course course:allCourses)
        {
            Group group = new Group(course.getCourseName());
            for(Item item : course.getItems())
            {
                String data = item.getName() +"\t\t" + "$"+item.getItemPrice()+"\nIngredients: ";
                List<Ingredient> ingredients = item.getIngredients();
                data+=ingredients.get(0);
                for(int i=1;i<ingredients.size();i++)
                {
                    data+=", " + ingredients.get(i);
                }
                group.children.add(data);
            }
            groups.append(j, group);
            ++j;
        }
    }

    public RestaurantMenu getMenuForDisplay()
    {
        initializeDatabase();
        Cursor resultSet = mydatabase.rawQuery("Select * from COURSES", null);
        Gson gson = new Gson();
        List<Course> allCourses = new ArrayList<Course>();
        while (resultSet.moveToNext()) {
            String serObj = resultSet.getString(0);
            Type t = new TypeToken<Course>() {}.getType();
            Course storedCourse = gson.fromJson(serObj, t);
            allCourses.add(storedCourse);
        }
        RestaurantMenu menu= new RestaurantMenu();
        menu.setCourseList(allCourses);
        menu.setRestaurantName(RESTAURANT_NAME);
        menu.setRestaurantDescription(CHINESE);
        return menu;
    }

    public void initializeDatabase()
    {
        mydatabase = openOrCreateDatabase(AddItemsActivity.DATABASE_NAME,MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS COURSES(SerializedObject VARCHAR);");
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        Gson gson = new Gson();
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMime(
                        "text/menu.customiz", gson.toJson(menu).getBytes())
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

}

