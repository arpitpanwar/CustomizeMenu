package menu.customiz.customizmenu.model;

import java.util.List;

import menu.customiz.customizmenu.ifaces.ICourse;
import menu.customiz.customizmenu.ifaces.IItem;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Course implements ICourse {

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private String courseName;
    private List<Item> items;

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
