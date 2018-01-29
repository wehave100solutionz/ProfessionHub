package solutionz.professionhub.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import solutionz.professionhub.R;
import solutionz.professionhub.RoundedImageView.CircleTransform;
import solutionz.professionhub.adapter.worker_list_adapter.RecyclerAdapterComments;
import solutionz.professionhub.model.ModelComments;

/**
 * Created by asad on 1/9/2018.
 */

public class ProfileDetails extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ImageView imgProfileDetail;
    Button btnHireMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_details);

        setUpRecyclerView();

        imgProfileDetail = (ImageView) findViewById(R.id.profile_image_detail);
        btnHireMe = (Button) findViewById(R.id.btn_hireme);
        btnHireMe.setOnClickListener(this);
        Glide.with(this).load(R.drawable.img5).transform(new CircleTransform(this)).into(this.imgProfileDetail);
    }


    private void setUpRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview_comments);
        RecyclerAdapterComments adapter = new RecyclerAdapterComments(this, ModelComments.getData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator()); // Even if we dont use it then also our items shows default animation. #Check Docs
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_hireme:

                Intent intent = new Intent(ProfileDetails.this,MapsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
