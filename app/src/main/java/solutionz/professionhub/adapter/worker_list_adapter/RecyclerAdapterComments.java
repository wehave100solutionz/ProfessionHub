package solutionz.professionhub.adapter.worker_list_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import solutionz.professionhub.R;
import solutionz.professionhub.RoundedImageView.CircleTransform;
import solutionz.professionhub.model.ModelComments;
import solutionz.professionhub.model.Model_Worker_ListView;

/**
 * Created by asad on 1/2/2018.
 */

public class RecyclerAdapterComments extends RecyclerView.Adapter<RecyclerAdapterComments.MyViewHolder> {

    private List<ModelComments> mdata;
    LayoutInflater minflater;
    Context context;

    public RecyclerAdapterComments(Context context, List<ModelComments> data)
    {
        this.mdata = data;
        this.minflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = minflater.inflate(R.layout.comment_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ModelComments currenObj = mdata.get(position);
        holder.setData(currenObj,position);

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvComment;
        ImageView imgCommentPerson;
        int position;
        ModelComments current_obj;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvComment = (TextView) itemView.findViewById(R.id.tv_comment_set);
            imgCommentPerson = (ImageView) itemView.findViewById(R.id.iv_comment_person);

        }

        public void setData(ModelComments current, int postion)
        {
            this.tvComment.setText(current.getComment());
            // this.imgProfile.setImageResource(current.getImageID());
            this.position = postion;
            this.current_obj = current;
            Glide.with(context).load(current.getImgID()).transform(new CircleTransform(context)).into(this.imgCommentPerson);
        }
    }
}
