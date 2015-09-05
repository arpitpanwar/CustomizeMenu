package menu.customiz.customizmenu.model;

import menu.customiz.customizmenu.ifaces.IIngredient;

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
}
