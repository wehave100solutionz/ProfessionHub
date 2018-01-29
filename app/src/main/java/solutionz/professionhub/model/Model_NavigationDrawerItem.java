package solutionz.professionhub.model;

/**
 * Created by asad on 1/11/2018.
 */
import java.util.ArrayList;
import java.util.List;

import solutionz.professionhub.R;

public class Model_NavigationDrawerItem {

    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageNavi() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<Model_NavigationDrawerItem> getData() {
        List<Model_NavigationDrawerItem> dataList = new ArrayList<>();

        int[] imageIds = getImages();
        String[] titles = getTitles();

        for (int i = 0; i < titles.length; i++) {
            Model_NavigationDrawerItem navItem = new Model_NavigationDrawerItem();
            navItem.setTitle(titles[i]);
            navItem.setImageId(imageIds[i]);
            dataList.add(navItem);
        }
        return dataList;
    }

    private static int[] getImages() {

        return new int[]{
                R.drawable.ic_birds, R.drawable.ic_animal,
                R.drawable.ic_forest, R.drawable.ic_ocean,
                R.drawable.ic_planet, R.drawable.ic_landscape};
    }

    private static String[] getTitles() {

        return new String[] {
                "Birds", "Animals", "Forest", "Ocean", "Planets", "Landscape"
        };
    }
}

