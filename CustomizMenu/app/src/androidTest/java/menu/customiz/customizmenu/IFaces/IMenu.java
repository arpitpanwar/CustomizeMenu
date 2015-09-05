package menu.customiz.customizmenu.IFaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface IMenu extends Serializable {

    public String getRestaurantName();

    public String getRestaurantDescription();

    public List<ICourse> getCourseList();

}
