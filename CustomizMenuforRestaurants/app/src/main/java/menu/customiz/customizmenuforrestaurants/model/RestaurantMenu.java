package menu.customiz.customizmenuforrestaurants.model;

import java.util.List;

import menu.customiz.customizmenuforrestaurants.ifaces.ICourse;
import menu.customiz.customizmenuforrestaurants.ifaces.IMenu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class RestaurantMenu implements IMenu {

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

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public void setRestaurantDescription(String restaurantDescription){
        this.restaurantDescription = restaurantDescription;
    }

    public void setCourseList(List<ICourse> courseList){
        this.courseList = courseList;
    }
}
