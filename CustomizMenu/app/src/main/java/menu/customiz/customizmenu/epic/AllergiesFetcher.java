package menu.customiz.customizmenu.epic;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import menu.customiz.customizmenu.constants.ApiConstants;

/**
 * Created by Arpit on 9/5/2015.
 */
public class AllergiesFetcher {

    private String userId;
    private final String TAG = getClass().getCanonicalName();

     public AllergiesFetcher(String userId){
         this.userId = userId;
     }

    public List<String> getAllergies(){

        List<String> allergies = null;

        JSONObject allergiesData = fetchAllergiesData();
    try {
         Integer total =   allergiesData.getInt("total");
        JSONArray entries = allergiesData.getJSONArray("entry");
        allergies = new ArrayList<String>();

        for(int i=0; i<total;i++){
           allergies.add(entries.getJSONObject(i).getJSONObject("substance").getString("text").trim().toLowerCase());
        }

    }catch(JSONException jse){
        Log.d(TAG,"Unable to parse allergies");
    }
        return allergies;
    }

    private JSONObject fetchAllergiesData(){
        JSONObject fetchedObject = null;
        String requestUrl = ApiConstants.API_BASE_URL+ApiConstants.ALLERGIES_API_PATH+userId;
        try {
            URL allergiesUrl = new URL(requestUrl);

            URLConnection conn = allergiesUrl.openConnection();

            String read="";
            StringBuilder readData = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while((read = reader.readLine())!=null){
                readData.append(read);
            }
            reader.close();
            fetchedObject = new JSONObject(readData.toString());
        }catch(MalformedURLException mfue){
            Log.d(TAG,"Invalid url");
        }catch(IOException ioe){
            Log.d(TAG,"Exception fetching data");
        }catch(JSONException je){

        }
        return fetchedObject;
    }

}
