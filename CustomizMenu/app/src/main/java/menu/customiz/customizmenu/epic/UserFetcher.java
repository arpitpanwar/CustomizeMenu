package menu.customiz.customizmenu.epic;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import menu.customiz.customizmenu.constants.ApiConstants;
import menu.customiz.customizmenu.model.UserInfo;
import menu.customiz.customizmenu.utils.StringUtils;

/**
 * Created by Arpit on 9/5/2015.
 */
public class UserFetcher {

    private final UserInfo userInfo;
    private final String TAG = UserFetcher.class.getCanonicalName();

    public UserFetcher(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String fetchUserId(){
        String id = "";
        try {
            
            URL requestUrl = generateRequestUrl();
            JSONObject userData = fetchUserData(requestUrl);

            if(null != userData){
                id = parseId(userData);
            }

        }catch(MalformedURLException mfue){
            Log.d(TAG,"Unable to generate url");
        }catch(JSONException jse){
            Log.d(TAG,"Unable to parse request");
        }
        

        return id;
    }

    /**
     * Parse id from the returned data
     * Currently first fetched value is used to parse out id. We need to correct out our logic for future
     * @param userData
     * @return
     * @throws JSONException
     */

    private String parseId(JSONObject userData) throws JSONException{
        String id = "";
        Integer total =  userData.getInt("total");

        if(total > 0){
          JSONArray entries =  userData.getJSONArray("entry");
          JSONObject currEntry = entries.getJSONObject(0);
          String userUrl =  currEntry.getJSONArray("link").getJSONObject(0).getString("url");
          String[] tokens = userUrl.split("/");
          id =  tokens[tokens.length-1];
        }

        return id;
    }

    private JSONObject fetchUserData(URL url){

        try{
            String data="";
            StringBuilder jsonData = new StringBuilder();
            URLConnection conn = url.openConnection();
            Log.d(TAG, "Data1: " + data);
            InputStream stream = conn.getInputStream();
            Log.d(TAG, "Data2: " + data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Log.d(TAG, "Data3: " + data);
            while(null != (data = reader.readLine())){
                Log.d(TAG, "Data4: " + data);
                jsonData.append(data);
            }
            stream.close();

            return new JSONObject(jsonData.toString());
        }catch(IOException ioe){
            Log.d(TAG,"Unable to read json data");
        }catch(JSONException jse){
            Log.d(TAG,"Unable to parse json");
        }
        return null;
    }

    private URL generateRequestUrl() throws MalformedURLException{

        Boolean isFirst = new Boolean(true);
        StringBuilder url = new StringBuilder();
        url.append(ApiConstants.API_BASE_URL+ApiConstants.PATIENT_API_PATH);
        url.append("?");


        if(!StringUtils.isNullOrWhitespace(userInfo.getFamilyName())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_FAMILY, userInfo.getFamilyName(), isFirst, url);
        }
        if(!StringUtils.isNullOrWhitespace(userInfo.getGivenName())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_GIVEN,userInfo.getGivenName(),isFirst,url);
        }
        if(!StringUtils.isNullOrWhitespace(userInfo.getAddress())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_ADDRESS,userInfo.getAddress(),isFirst,url);
        }
        if(!StringUtils.isNullOrWhitespace(userInfo.getGender())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_GENDER,userInfo.getGender(),isFirst,url);
        }
        if(!StringUtils.isNullOrWhitespace(userInfo.getTelecom())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_TELECOM,userInfo.getTelecom(),isFirst,url);
        }
        if(!StringUtils.isNullOrWhitespace(userInfo.getBirthDate())){
            appendParameter(ApiConstants.PATIENT_API_PARAMETER_BIRTHDATE,userInfo.getTelecom(),isFirst,url);
        }

        return new URL(url.toString());
    }

    private void appendParameter(String paramName, String paramValue, Boolean isFirst, StringBuilder builder){

        if(!isFirst)
            builder.append("&");
        else
            isFirst = new Boolean(false);

        builder.append(paramName);
        builder.append("=");
        builder.append(StringUtils.encodeParameter(paramValue));
    }


}
