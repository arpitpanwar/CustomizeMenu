package menu.customiz.customizmenuforrestaurants.model;

import java.util.List;

import menu.customiz.customizmenuforrestaurants.ifaces.IIngredient;
import menu.customiz.customizmenuforrestaurants.ifaces.IItem;

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

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setCaloriesCount(double caloriesCount) {
        this.caloriesCount = caloriesCount;
    }

    public void setName(String name) {
        this.name = name;
    }
}
