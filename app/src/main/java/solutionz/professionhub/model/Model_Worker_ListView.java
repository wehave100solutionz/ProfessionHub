package solutionz.professionhub.model;

import java.util.ArrayList;

import solutionz.professionhub.R;

/**
 * Created by asad on 1/1/2018.
 */

public class Model_Worker_ListView {

    private int imageID;
    private String userName;
    private String profession;
    private int totalWorkCount;


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getTotalWorkCount() {
        return totalWorkCount;
    }

    public void setTotalWorkCount(int totalWorkCount) {
        this.totalWorkCount = totalWorkCount;
    }



    public static ArrayList<Model_Worker_ListView> getData()
    {
        ArrayList<Model_Worker_ListView> dataList = new ArrayList<>();
        ArrayList<Model_Worker_ListView> filterOutput = new ArrayList<>();

        int[] imageId = getImages();
        String[] name = getName();

        for (int i=0; i<imageId.length;i++)
        {
            Model_Worker_ListView adapterWorkerListView = new Model_Worker_ListView();

            adapterWorkerListView.setImageID(imageId[i]);
            adapterWorkerListView.setUserName("100Solutionz "+i);
            adapterWorkerListView.setProfession(name[i]);
            adapterWorkerListView.setTotalWorkCount(12+i);

            dataList.add(adapterWorkerListView);
        }

       /* if (true)
        {

            for (Model_Worker_ListView item:dataList)
            {
                if (item.getProfession().toLowerCase().startsWith(query.toLowerCase()));
                {
                    filterOutput.add(item);
                    //return filterOutput;
                 //   dataList = filterOutput;
                }
            }
        }
*/

        return dataList;
    }

    public static int[] getImages()
    {
        int images[] = {R.drawable.img,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,
                R.drawable.img7,R.drawable.img8,R.drawable.img5,R.drawable.img8};
        return images;
    }


    public static String[] getName()
    {
        String name[] = {"Asad","Zubair","Butt","Rana","Asad","Zubair","Butt","Rana","Butt","Rana"};

    return name;
    }}
