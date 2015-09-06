package menu.customiz.customizmenuforrestaurants.ifaces;

import java.io.Serializable;
import java.util.List;

import menu.customiz.customizmenuforrestaurants.model.Course;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface IMenu extends Serializable {

    String getRestaurantName();

    String getRestaurantDescription();

    List<Course> getCourseList();

}
