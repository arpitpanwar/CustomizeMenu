package menu.customiz.customizmenu.menu;

import java.util.ArrayList;
import java.util.List;

import menu.customiz.customizmenu.ifaces.ICourse;
import menu.customiz.customizmenu.ifaces.IIngredient;
import menu.customiz.customizmenu.ifaces.IItem;
import menu.customiz.customizmenu.ifaces.IMenu;
import menu.customiz.customizmenu.model.Course;
import menu.customiz.customizmenu.model.Menu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class FilteredMenuProvider {

    private IMenu menu;
    private List<String> allergies;

    public FilteredMenuProvider(IMenu menu, List<String> allergies){
        this.menu = menu;
        this.allergies = allergies;
    }

    public IMenu getFilteredMenu(){

        Menu filteredMenu = new Menu();
        filteredMenu.setRestaurantDescription(menu.getRestaurantDescription());
        filteredMenu.setRestaurantName(menu.getRestaurantName());
        filteredMenu.setCourseList(getFilteredCourses());

        return filteredMenu;
    }

    private List<ICourse> getFilteredCourses(){

        List<ICourse> courses = this.menu.getCourseList();
        List<ICourse> filteredCourses = new ArrayList<ICourse>();
        for(ICourse course : courses){

            Course currCourse = new Course();

            List<IItem> filteredItems = new ArrayList<IItem>();

            for(IItem item: course.getItems()){
              boolean isAccepted = true;
              ingredientLoop:  for(IIngredient ingredient: item.getIngredients()){
                                    if(this.allergies.contains(ingredient.getCommonName().toLowerCase())
                                            || this.allergies.contains(ingredient.getName().toLowerCase()))
                                    {
                                        isAccepted = false;
                                        break ingredientLoop;
                                    }
                }
                if(isAccepted)
                    filteredItems.add(item);
            }
            currCourse.setItems(filteredItems);
            filteredCourses.add(currCourse);
        }

        return filteredCourses;
    }



}
