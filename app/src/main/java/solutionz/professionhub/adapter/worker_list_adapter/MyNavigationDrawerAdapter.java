package solutionz.professionhub.adapter.worker_list_adapter;

/**
 * Created by asad on 1/11/2018.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import solutionz.professionhub.R;
import solutionz.professionhub.RoundedImageView.CircleTransform;
import solutionz.professionhub.model.Model_NavigationDrawerItem;

public class MyNavigationDrawerAdapter extends RecyclerView.Adapter<MyNavigationDrawerAdapter.MyViewHolder> {

    private List<Model_NavigationDrawerItem> mDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public MyNavigationDrawerAdapter(Context context, List<Model_NavigationDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Model_NavigationDrawerItem current = mDataList.get(position);
        holder.setData(current,position);

      /*  holder.imgIcon.setImageResource(current.getImageId());
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.title.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);

        }


        public void setData(Model_NavigationDrawerItem current, int postion)
        {
            this.imgIcon.setImageResource(current.getImageNavi());
            this.title.setText(current.getTitle());// this.imgProfile.setImageResource(current.getImageID());
            /*this.position = postion;
            this.current_obj = current;*/
            Glide.with(context).load(current.getImageNavi()).transform(new CircleTransform(context)).into(this.imgIcon);
        }
    }
}
