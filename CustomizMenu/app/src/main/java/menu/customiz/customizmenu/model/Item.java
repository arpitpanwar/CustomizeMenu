package menu.customiz.customizmenu.model;

import java.util.List;

import menu.customiz.customizmenu.ifaces.IIngredient;
import menu.customiz.customizmenu.ifaces.IItem;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Item implements IItem{

    private double itemPrice;
    private List<Ingredient> ingredients;
    private double caloriesCount;
    private String name;

    @Override
    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public double getCaloriesCount() {
        return caloriesCount;
    }

    @Override
    public String getName() {
        return name;
    }
}
