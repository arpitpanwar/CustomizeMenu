package menu.customiz.customizmenu.IFaces;

import java.util.List;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface IItem {

    public double getItemPrice();

    public List<IIngredient> getIngredients();

    public double getCaloriesCount();

    public String getItemName();

}
