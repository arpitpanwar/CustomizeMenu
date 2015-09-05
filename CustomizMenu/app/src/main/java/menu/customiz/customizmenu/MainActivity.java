package menu.customiz.customizmenu;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import menu.customiz.customizmenu.epic.UserFetcher;
import menu.customiz.customizmenu.model.UserInfo;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.gender_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void getUserInfoFromUI(View v)
    {

        new Thread(new Runnable() {
            public void run() {

                UserInfo userInfo = new UserInfo();

                EditText givenName = (EditText)findViewById(R.id.firstNameTextField);
                userInfo.setGivenName(givenName.getText().toString());

                EditText familyName = (EditText)findViewById(R.id.lastNameTextField);
                userInfo.setFamilyName(familyName.getText().toString());

                EditText address = (EditText)findViewById(R.id.addressTextField);
                userInfo.setAddress(address.getText().toString());

                EditText dOB = (EditText)findViewById(R.id.dobTextField);
                userInfo.setAddress(dOB.getText().toString());

                //Spinner gender = (Spinner)findViewById(R.id.gender_spinner);
                //userInfo.setAddress(gender.g);

                EditText telephone = (EditText)findViewById(R.id.phoneNumberTextField);
                userInfo.setTelecom(telephone.getText().toString());

                UserFetcher fetcher = new UserFetcher(userInfo);
                String userId = fetcher.fetchUserId();

                Log.d("userId", "User id is " + userId);

            }
        }).start();


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
}
