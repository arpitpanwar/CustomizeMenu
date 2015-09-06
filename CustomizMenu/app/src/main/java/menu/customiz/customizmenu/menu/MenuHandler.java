package menu.customiz.customizmenu.menu;

import android.util.Log;

import menu.customiz.customizmenu.epic.AllergiesFetcher;
import menu.customiz.customizmenu.epic.UserFetcher;
import menu.customiz.customizmenu.ifaces.IMenu;
import menu.customiz.customizmenu.model.Menu;
import menu.customiz.customizmenu.model.UserInfo;

/**
 * Created by Arpit on 9/5/2015.
 */
public class MenuHandler {

    private static String TAG = MenuHandler.class.getCanonicalName();

    public static void handle(String data,UserInfo userInfo){
       try{
             Menu menu =  MenuParser.parse(data);
             UserFetcher fetcher = new UserFetcher(userInfo);

             AllergiesFetcher allergiesFetcher = new AllergiesFetcher(fetcher.fetchUserId());
             FilteredMenuProvider filteredProvider =  new FilteredMenuProvider(menu,allergiesFetcher.getAllergies());

            Menu filteredMenu =  filteredProvider.getFilteredMenu();

        }catch(Exception e){
           Log.d(TAG,"Error handling data: "+e.getMessage());
       }
    }

}
