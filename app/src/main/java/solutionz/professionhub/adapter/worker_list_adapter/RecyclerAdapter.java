package solutionz.professionhub.adapter.worker_list_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import solutionz.professionhub.activity.ProfileDetails;
import solutionz.professionhub.R;
import solutionz.professionhub.RoundedImageView.CircleTransform;
import solutionz.professionhub.model.Model_Worker_ListView;

/**
 * Created by asad on 1/2/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Model_Worker_ListView> mdata;
    LayoutInflater minflater;
    Context context;

    public RecyclerAdapter(Context context, List<Model_Worker_ListView> data) {
        this.mdata = data;
        this.minflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = minflater.inflate(R.layout.worker_list_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Model_Worker_ListView currenObj = mdata.get(position);
        holder.setData(holder, currenObj, position);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        TextView tvProfession;
        TextView tvWorkAttempt;
        ImageView imgProfile;
        int position;
        Model_Worker_ListView current_obj;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvProfession = (TextView) itemView.findViewById(R.id.tv_work_title);
            tvWorkAttempt = (TextView) itemView.findViewById(R.id.tv_work_attempt);
            imgProfile = (ImageView) itemView.findViewById(R.id.profile_ImageView);

        }

        public void setData(MyViewHolder holder, final Model_Worker_ListView current, final int postion) {
            this.tvUserName.setText(current.getUserName());
            this.tvProfession.setText(current.getProfession());
            this.tvWorkAttempt.setText(String.valueOf(current.getTotalWorkCount()));
            // this.imgProfile.setImageResource(current.getImageID());
            this.position = postion;
            this.current_obj = current;
            //  holder.imgProfile.setImageResource(current.getImageID());
            Log.d("pathImg", imgProfile + "");
            Glide.with(context).load(current.getImageID()).transform(new CircleTransform(context)).into(this.imgProfile);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(context, "Click Item ID is " + postion, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, ProfileDetails.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
