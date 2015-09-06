package menu.customiz.customizmenuforrestaurants; /**
 * Created by Ashmeet on 9/5/2015.
 */
import java.util.ArrayList;
import java.util.List;

//the following class will hold domain model for the ExpandableListView.
public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}