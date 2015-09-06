package menu.customiz.customizmenu.menu;

import java.util.ArrayList;
import java.util.List;

import menu.customiz.customizmenu.ifaces.ICourse;
import menu.customiz.customizmenu.ifaces.IMenu;
import menu.customiz.customizmenu.model.Course;
import menu.customiz.customizmenu.model.Ingredient;
import menu.customiz.customizmenu.model.Item;
import menu.customiz.customizmenu.model.Menu;

/**
 * Created by Arpit on 9/5/2015.
 */
public class FilteredMenuProvider {

    private IMenu menu;
    private List<String> allergies;

    public FilteredMenuProvider(Menu menu, List<String> allergies){
        this.menu = menu;
        this.allergies = allergies;
    }

    public Menu getFilteredMenu(){

        Menu filteredMenu = new Menu();
        filteredMenu.setRestaurantDescription(menu.getRestaurantDescription());
        filteredMenu.setRestaurantName(menu.getRestaurantName());
        filteredMenu.setCourseList(getFilteredCourses());

        return filteredMenu;
    }

    private List<Course> getFilteredCourses(){

        List<Course> courses = this.menu.getCourseList();
        List<Course> filteredCourses = new ArrayList<Course>();
        for(ICourse course : courses){

            Course currCourse = new Course();

            List<Item> filteredItems = new ArrayList<Item>();

            for(Item item: course.getItems()){
              boolean isAccepted = true;
              ingredientLoop:  for(Ingredient ingredient: item.getIngredients()){
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
            currCourse.setCourseName(course.getCourseName());
            filteredCourses.add(currCourse);
        }

        return filteredCourses;
    }



}
