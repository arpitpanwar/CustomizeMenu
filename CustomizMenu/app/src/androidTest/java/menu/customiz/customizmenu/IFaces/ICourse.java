package menu.customiz.customizmenu.IFaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface ICourse extends Serializable{

    public String getCourseName();

    public List<IItem> getItems();


}
