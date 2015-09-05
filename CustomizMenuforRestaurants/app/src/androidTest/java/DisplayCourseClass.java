/**
 * Created by Ashmeet on 9/5/2015.
 */

import java.util.ArrayList;

        import android.app.Activity;
        import android.os.Bundle;
        import android.util.SparseArray;
        import android.view.Menu;
        import android.widget.ExpandableListView;

import menu.customiz.customizmenuforrestaurants.R;

public class DisplayCourseClass extends Activity {
    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_course_layout);
        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    public void createData() {
        for (int j = 0; j < 5; j++) {
            Group group = new Group("Test " + j);
            for (int i = 0; i < 5; i++) {
                group.children.add("Sub Item" + i);
            }
            groups.append(j, group);
        }
    }

}

