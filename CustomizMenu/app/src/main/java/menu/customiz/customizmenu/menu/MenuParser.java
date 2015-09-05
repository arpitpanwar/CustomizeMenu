package menu.customiz.customizmenu.menu;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import menu.customiz.customizmenu.ifaces.IMenu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class MenuParser {

    public static IMenu parse(String data)throws Exception{

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data.getBytes()));
        IMenu menu =  (IMenu)ois.readObject();
        return menu;
    }


}
