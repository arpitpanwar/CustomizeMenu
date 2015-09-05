package menu.customiz.customizmenuforrestaurants.model;

import java.util.List;

import menu.customiz.customizmenuforrestaurants.ifaces.ICourse;
import menu.customiz.customizmenuforrestaurants.ifaces.IItem;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Course implements ICourse {

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setItems(List<IItem> items) {
        this.items = items;
    }

    private String courseName;
    private List<IItem> items;

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public List<IItem> getItems() {
        return items;
    }
}
