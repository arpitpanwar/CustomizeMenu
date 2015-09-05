package menu.customiz.customizmenu.ifaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface IItem extends Serializable {

    double getItemPrice();

    List<IIngredient> getIngredients();

    double getCaloriesCount();

    String getName();

}
