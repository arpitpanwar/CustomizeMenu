package menu.customiz.customizmenu.menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;

import menu.customiz.customizmenu.ifaces.IMenu;
import menu.customiz.customizmenu.model.Menu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class MenuParser {

    public static Menu parse(String data)throws Exception{
        Gson gson = new Gson();
        Type type = new TypeToken<Menu>(){}.getType();
        return gson.fromJson(data,type);
    }


}
