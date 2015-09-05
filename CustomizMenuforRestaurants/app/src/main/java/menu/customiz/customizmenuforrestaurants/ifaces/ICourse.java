package menu.customiz.customizmenuforrestaurants.ifaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface ICourse extends Serializable{

    String getCourseName();

    List<IItem> getItems();


}
