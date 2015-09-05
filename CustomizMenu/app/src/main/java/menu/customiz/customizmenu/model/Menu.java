package menu.customiz.customizmenu.model;

import java.util.List;

import menu.customiz.customizmenu.ifaces.ICourse;
import menu.customiz.customizmenu.ifaces.IMenu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Menu implements IMenu {

    private String restaurantName;
    private String restaurantDescription;
    private List<ICourse> courseList;

    @Override
    public String getRestaurantName() {
        return restaurantName;
    }

    @Override
    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    @Override
    public List<ICourse> getCourseList() {
        return courseList;
    }
}
