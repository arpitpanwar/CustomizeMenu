package menu.customiz.customizmenu.model;

import java.util.List;

import menu.customiz.customizmenu.ifaces.ICourse;
import menu.customiz.customizmenu.ifaces.IItem;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Course implements ICourse {

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
