package menu.customiz.customizmenuforrestaurants.ifaces;

import java.io.Serializable;
import java.util.List;

import menu.customiz.customizmenuforrestaurants.model.Ingredient;

/**
 * Created by Arpit on 9/5/2015.
 */
public interface IItem extends Serializable {

    double getItemPrice();

    List<Ingredient> getIngredients();

    double getCaloriesCount();

    String getName();

}
