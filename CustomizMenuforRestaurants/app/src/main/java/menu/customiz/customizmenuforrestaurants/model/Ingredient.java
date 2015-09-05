package menu.customiz.customizmenuforrestaurants.model;

import menu.customiz.customizmenuforrestaurants.ifaces.IIngredient;

/**
 * Created by Arpit on 9/5/2015.
 */
public class Ingredient implements IIngredient {

    private String name;
    private String commonName;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCommonName() {
        return commonName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}
