package menu.customiz.customizmenuforrestaurants;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.*;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONStringer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import menu.customiz.customizmenuforrestaurants.ifaces.ICourse;
import menu.customiz.customizmenuforrestaurants.ifaces.IIngredient;
import menu.customiz.customizmenuforrestaurants.ifaces.IItem;
import menu.customiz.customizmenuforrestaurants.model.Course;
import menu.customiz.customizmenuforrestaurants.model.Ingredient;
import menu.customiz.customizmenuforrestaurants.model.Item;
import menu.customiz.customizmenuforrestaurants.model.RestaurantMenu;

public class AddItemsActivity extends Activity {

    private RestaurantMenu restaurantMenu;
    private Course currentCourse;
    private ArrayList<Item> items = new ArrayList<Item>();
    private SQLiteDatabase mydatabase;
    public final static String DATABASE_NAME = "RESTAURANTMENU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        initializeSqliteDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void storeItemInfo(View view)
    {
        Item newItem = new Item();
        EditText itemName = (EditText) findViewById(R.id.editText2);
        newItem.setName(itemName.getText().toString());
        EditText itemPrice = (EditText) findViewById(R.id.editText4);
        newItem.setItemPrice(Double.parseDouble(itemPrice.getText().toString()));
        EditText ingredients = (EditText) findViewById(R.id.editText3);
        String [] allIngredients = ingredients.getText().toString().split(",");
        ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();
        for(String ing: allIngredients)
        {
            Ingredient cur = new Ingredient();
            String trimmedIng = ing.trim();
            cur.setName(trimmedIng);
            cur.setCommonName(trimmedIng);
            listOfIngredients.add(cur);
        }
        newItem.setIngredients(listOfIngredients);
        newItem.setCaloriesCount(250); // This is hardcoded as no way to know the calorie count of the item.
        items.add(newItem);
        Toast.makeText(getApplicationContext(), "The item has been added to the course successfully", Toast.LENGTH_LONG).show();
        itemName.setText("");
        itemPrice.setText("");
        ingredients.setText("");
    }

    public void addAnotherCourse(View view)
    {
        storeCurrentCourseToDatabase();
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }

    public void finish(View view) {
        storeCurrentCourseToDatabase();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void storeCurrentCourseToDatabase()
    {
        currentCourse = new Course();
        currentCourse.setCourseName(getIntent().getStringExtra(AddCourse.COURSE_NAME));
        currentCourse.setItems(items);
       try {
            Gson gson = new Gson();
            ContentValues cv=new ContentValues();
            cv.put("SerializedObject", gson.toJson(currentCourse));
            mydatabase.insert("COURSES", null, cv);
            Cursor resultSet = mydatabase.rawQuery("Select * from COURSES", null);
            resultSet.moveToFirst();
            String serObj = resultSet.getString(0);
            Log.d("Serialized string: ", serObj);
            Type t = new TypeToken<Course>() {}.getType();
            Course storedCourse = gson.fromJson(serObj, t);
            Log.d("Course name: ", storedCourse.getCourseName());
            int i=0;
            for(IItem item: storedCourse.getItems())
            {
                ++i;
                Log.d("Item "+i, item.getName()+"\t\t"+item.getItemPrice());
            }
            //mydatabase.execSQL("INSERT INTO SerializedObject VALUES('"+bout.toString(Xml.Encoding.UTF_8.toString())+"');");
        } catch (Exception e){

       }
        Toast.makeText(getApplicationContext(), "Course stored successfully", Toast.LENGTH_LONG).show();
    }

    public void initializeSqliteDatabase()
    {
        mydatabase = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS COURSES(SerializedObject VARCHAR);");
    }
}
