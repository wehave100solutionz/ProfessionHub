package solutionz.professionhub.model;

import java.util.ArrayList;

import solutionz.professionhub.R;

/**
 * Created by asad on 1/11/2018.
 */

public class ModelComments {

    private int imgID;
    private String comment;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static ArrayList<ModelComments> getData()
    {
        ArrayList<ModelComments> dataList = new ArrayList<>();
        int[] imageId = getImages();
        for (int i=0; i<imageId.length;i++)
        {
            ModelComments modelComments = new ModelComments();

            modelComments.setImgID(imageId[i]);
            modelComments.setComment("Sometimes being a brother is even better than being a superhero."+i);
            dataList.add(modelComments);
        }

        return dataList;
    }


    public static int[] getImages()
    {
        int images[] = {R.drawable.img,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,
                R.drawable.img7,R.drawable.img8,R.drawable.img5,R.drawable.img8};
        return images;
    }
}
